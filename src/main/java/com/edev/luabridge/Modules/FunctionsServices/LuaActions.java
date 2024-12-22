package com.edev.luabridge.Modules.FunctionsServices;

import com.edev.luabridge.Models.LuaCoffeLuaReturnModel.LuaReturn;
import com.edev.luabridge.Modules.File.FileServices;
import org.luaj.vm2.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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

    public String ReplaceWaitingValues(String script, List<Map<String, Object>> params, String path) throws IOException {
        String verification = verifyingPath(script, path);
        if ( verification != null){

            String scriptWithImportedScripts = importScripting(verification);
            String scriptWithFunctionValuesReplaced = waitingValueOfParams(scriptWithImportedScripts, params);

            return waitingValueOfScript(scriptWithFunctionValuesReplaced, params, path);

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


    public String waitingValueOfParams(String script, List<Map<String, Object>> params) {
        Pattern pattern = Pattern.compile("waiting\\.valueOfParam\\((\"?\\w+\"?)\\)");
        Matcher matcher = pattern.matcher(script);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String paramName = matcher.group(1).replace("\"", "");
            String paramValue = null;


            for (Map<String, Object> param : params) {
                if (param.containsKey(paramName)) {
                    Object value = param.get(paramName);
                    if (value instanceof String) {
                        paramValue = "\"" + value.toString() + "\"";
                    } else {
                        paramValue = value.toString();
                    }

                    break;
                }
            }
            if (paramValue == null) {
                paramValue = "nil";
            }


            matcher.appendReplacement(sb, paramValue);
        }

        matcher.appendTail(sb);
        return sb.toString();
    }
    public String importScripting(String script) throws IOException {
        // Definir o padrão para encontrar os imports com espaços adicionais
        Pattern pattern = Pattern.compile("import\\s*\\(\\s*\"([^\"]+)\"\\s*\\)");
        Matcher matcher = pattern.matcher(script);
        StringBuilder sb = new StringBuilder();

        // Depuração: Verifique o script original
        System.out.println("Script Original:\n" + script);

        while (matcher.find()) {
            // Extrair o nome do arquivo importado
            String fileName = matcher.group(1);
            System.out.println("Import encontrado: " + fileName);

            // Procurar o arquivo correspondente
            File fileImported = fileServices.encontrarArquivosImportados(fileName);

            if (fileImported == null) {
                throw new LuaError("Cannot find " + fileName + " in package.");
            }

            // Ler o conteúdo do arquivo importado
            String importedContent = fileServices.readFile(fileImported);
            System.out.println("Conteúdo importado:\n" + importedContent);

            // Adicionar o conteúdo importado ao script
            String importedCode = "function()\n" + importedContent + "\nend";
            matcher.appendReplacement(sb, importedCode);
        }

        matcher.appendTail(sb);

        return sb.toString();
    }




    public String waitingValueOfScript(String script, List<Map<String, Object>> params, String path ){
        Pattern pattern = Pattern.compile("waiting\\.valueOfScript\\((\"?\\w+\"?)\\)");
        Matcher matcher = pattern.matcher(script);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()){
            String scriptName = matcher.group(1).replace("\"", "");
            String scriptValue = executeLuaCode(scriptName, params, path);
            if ("Script não encontrado!".equals(scriptValue)) {
                return "Script não encontrado! " + scriptName;
            } else if (scriptValue.contains("Erro na execução do script Lua: ")) {
                return "Erro na execução do script: " + scriptName;
            }

            // Se o valor for uma string, precisamos garantir que fique entre aspas
            if (scriptValue instanceof String) {
                scriptValue = "\"" + scriptValue.toString() + "\"";
            } else {
                scriptValue = scriptValue.toString(); // Se for número, basta converter
            }

            matcher.appendReplacement(sb, scriptValue);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public String executeLuaCode(String script, List<Map<String, Object>> params, String path){
        try{
            String complete = ReplaceWaitingValues(script, params, path);
            LuaValue chunk = globals.load(complete);
            LuaValue response = chunk.call();
            System.out.println(complete);
            return response.toString();
        } catch (LuaError e) {
            return "Erro na execução do script Lua: " + e.getMessage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
