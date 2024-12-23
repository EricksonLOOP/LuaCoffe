package com.edev.luabridge.Modules.FunctionsServices;

import com.edev.luabridge.Models.LuaCoffeLuaReturnModel.LuaReturn;
import com.edev.luabridge.Modules.File.FileServices;
import org.luaj.vm2.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LuaActions {
    private final Globals globals;
    private final FileServices fileServices;

    public LuaActions(Globals globals, FileServices fileServices) {
        this.globals = globals;
        this.fileServices = fileServices;
    }

    public String ReplaceWaitingValues(String script, Map<String, Object> params, String path) throws IOException {
        String verification = verifyingPath(script, path);
        if ( verification != null){

            String scriptWithImportedScripts = importScripting(verification);
            return waitingValueOfParams(scriptWithImportedScripts, params);

        }
        return null;
    }
    public String verifyingPath(String script, String path) {
        Pattern pattern = Pattern.compile("luaCoffe\\.mapping\\s*\\(\\s*\"([^\"]+)\"\\s*\\)");
        Matcher matcher = pattern.matcher(script);
        StringBuilder sb = new StringBuilder();

        if (matcher.find()) {
            String mappingName = matcher.group(1).replace("\"", "");
            if (!mappingName.equals(path)) {
                return null;
            }
            matcher.appendReplacement(sb, "");
        }

        matcher.appendTail(sb);
        script = sb.toString();
        return script;
    }

    public String waitingValueOfParams(String script, Map<String, Object> params) {
        // Definir o padrão para encontrar as chamadas de waiting.valueOfParam()
        Pattern pattern = Pattern.compile("waiting\\.valueOfParam\\((\"?\\w+\"?)\\)");
        Matcher matcher = pattern.matcher(script);
        StringBuilder sb = new StringBuilder();

        // Percorrer todas as ocorrências encontradas
        while (matcher.find()) {
            // Extrair o nome do parâmetro sem aspas
            String paramName = matcher.group(1).replace("\"", "");
            String paramValue = null;

            // Verificar se o mapa de parâmetros contém o nome do parâmetro
            if (params.containsKey(paramName)) {
                Object value = params.get(paramName);

                // Verificar se o valor é uma lista vazia
                if (value instanceof List && ((List<?>) value).isEmpty()) {
                    // Se for uma lista vazia, substitui por {}
                    paramValue = "{}";
                } else if (value instanceof List) {
                    // Se for uma lista não vazia, tratar os itens
                    List<?> list = (List<?>) value;
                    StringBuilder listBuilder = new StringBuilder();
                    listBuilder.append("{");

                    for (int i = 0; i < list.size(); i++) {
                        Object item = list.get(i);

                        // Se o item for uma string, adiciona aspas
                        if (item instanceof String) {
                            listBuilder.append("\"").append(item.toString()).append("\"");
                        } else if (item instanceof Map) {
                            // Se o item for um mapa (objeto), processa as propriedades
                            listBuilder.append(processMap((Map<?, ?>) item));
                        } else {
                            // Caso contrário, converte o item para string diretamente
                            listBuilder.append(item.toString());
                        }

                        if (i < list.size() - 1) {
                            listBuilder.append(", ");
                        }
                    }

                    listBuilder.append("}");
                    paramValue = listBuilder.toString();
                } else if (value instanceof Map) {
                    // Se o valor for um mapa (objeto), processa as propriedades
                    paramValue = processMap((Map<?, ?>) value);
                } else {
                    // Se não for uma lista nem um mapa, tratar como string ou número
                    if (value instanceof String) {
                        paramValue = "\"" + value.toString() + "\"";
                    } else {
                        paramValue = value.toString();
                    }
                }
            }

            // Se o valor não for encontrado, define como "nil"
            if (paramValue == null) {
                paramValue = "nil";
            }

            // Substituir a chamada por seu valor correspondente
            matcher.appendReplacement(sb, paramValue);
        }

        // Adicionar o restante do script
        matcher.appendTail(sb);
        return sb.toString();
    }

    private String processMap(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        int i = 0;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();

            // Se a chave for uma string, adiciona aspas
            if (key instanceof String) {
                sb.append(key).append("= ");
            } else {
                sb.append(key).append("= ");
            }

            // Se o valor for uma string, adiciona aspas
            if (value instanceof String) {
                sb.append("\"").append(value.toString()).append("\"");
            } else if (value instanceof Map) {
                // Se o valor for outro mapa (objeto), processa as propriedades
                sb.append(processMap((Map<?, ?>) value));
            } else {
                // Caso contrário, converte o valor para string diretamente
                sb.append(value.toString());
            }

            // Se não for o último item, adiciona uma vírgula
            if (++i < map.size()) {
                sb.append(", ");
            }
        }

        sb.append("}");
        return sb.toString();
    }


    public String importScripting(String script) throws IOException {
        // Definir o padrão para encontrar os imports com espaços adicionais
        Pattern pattern = Pattern.compile("import\\s*\\(\\s*\"([^\"]+)\"\\s*\\)");
        Matcher matcher = pattern.matcher(script);
        StringBuilder sb = new StringBuilder();

        // Depuração: Verifique o script original
        System.out.println("Script Original:\n" + script);

        boolean foundImport = false;

        while (matcher.find()) {
            String fileName = matcher.group(1);
            System.out.println("Import encontrado: " + fileName);

            File fileImported = fileServices.encontrarArquivosImportados(fileName);

            if (fileImported == null) {
                throw new LuaError("Cannot find " + fileName + " in package.");
            }

            String importedContent = fileServices.readFile(fileImported);
            System.out.println("Conteúdo importado:\n" + importedContent);

            String importedCode = "function()\n" + importedContent + "\nend";
            matcher.appendReplacement(sb, importedCode);
            foundImport = true;
        }

        matcher.appendTail(sb);


        if (foundImport) {
            return importScripting(sb.toString());
        }

        return sb.toString();
    }





//    public String waitingValueOfScript(String script, List<Map<String, Object>> params, String path ){
//        Pattern pattern = Pattern.compile("waiting\\.valueOfScript\\((\"?\\w+\"?)\\)");
//        Matcher matcher = pattern.matcher(script);
//        StringBuilder sb = new StringBuilder();
//        while (matcher.find()){
//            String scriptName = matcher.group(1).replace("\"", "");
//            String scriptValue = executeLuaCode(scriptName, params, path);
//            if ("Script não encontrado!".equals(scriptValue)) {
//                return "Script não encontrado! " + scriptName;
//            } else if (scriptValue.contains("Erro na execução do script Lua: ")) {
//                return "Erro na execução do script: " + scriptName;
//            }
//
//            // Se o valor for uma string, precisamos garantir que fique entre aspas
//            if (scriptValue instanceof String) {
//                scriptValue = "\"" + scriptValue.toString() + "\"";
//            } else {
//                scriptValue = scriptValue.toString(); // Se for número, basta converter
//            }
//
//            matcher.appendReplacement(sb, scriptValue);
//        }
//        matcher.appendTail(sb);
//        return sb.toString();
//    }

//    public String executeLuaCode(String script, List<Map<String, Object>> params, String path){
//        try{
//            String complete = ReplaceWaitingValues(script, params, path);
//            LuaValue chunk = globals.load(complete);
//            LuaValue response = chunk.call();
//            System.out.println(complete);
//            return response.toString();
//        } catch (LuaError e) {
//            return "Erro na execução do script Lua: " + e.getMessage();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


}
