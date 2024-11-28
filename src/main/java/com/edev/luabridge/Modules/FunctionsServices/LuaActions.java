package com.edev.luabridge.Modules.FunctionsServices;

import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import com.edev.luabridge.Models.RouteTypeModel.RouteType;
import com.edev.luabridge.Repositories.LuaRepository;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class LuaActions {
    private final Globals globals;

    @Autowired
    private final LuaRepository luaRepository;

    public LuaActions(Globals globals, LuaRepository luaRepository) {
        this.globals = globals;
        this.luaRepository = luaRepository;
    }

    public String ReplaceWaitingValues(String script, List<Map<String, Object>> params){
       String scriptWithFunctionValuesReplaced = waitingValueOfScript(script, params);
       String scriptWithParamsValuesReplaced = waitingValueOfParams(scriptWithFunctionValuesReplaced, params);
       return scriptWithParamsValuesReplaced;
    }
    public String waitingValueOfParams(String script, List<Map<String, Object>> params) {
        // Expressão regular para identificar "waiting.valueOfParam(paramName)" com suporte a strings com aspas
        Pattern pattern = Pattern.compile("waiting\\.valueOfParam\\((\"?\\w+\"?)\\)");
        Matcher matcher = pattern.matcher(script);
        StringBuilder sb = new StringBuilder();

        // Loop para substituir os valores de parâmetros no script
        while (matcher.find()) {
            String paramName = matcher.group(1).replace("\"", ""); // Remover aspas do nome do parâmetro
            String paramValue = null;

            // Procura o valor do parâmetro na lista de mapas
            for (Map<String, Object> param : params) {
                if (param.containsKey(paramName)) {
                    Object value = param.get(paramName);

                    // Se o valor for uma string, precisamos garantir que fique entre aspas
                    if (value instanceof String) {
                        paramValue = "\"" + value.toString() + "\"";
                    } else {
                        paramValue = value.toString(); // Se for número, basta converter
                    }

                    break;
                }
            }

            // Se o parâmetro não for encontrado, retorna uma mensagem de erro
            if (paramValue == null) {
                return "Parâmetro não encontrado: " + paramName;
            }

            // Substitui a chamada "waiting.valueOfParam(paramName)" pelo valor real
            matcher.appendReplacement(sb, paramValue);
        }

        // Adiciona o restante do script após as substituições
        matcher.appendTail(sb);
        return sb.toString();
    }
    public DataSource SetDb(List<LuaScriptEntity> scripts){
       Optional<LuaScriptEntity> databseScripts = scripts.stream()
               .filter(script -> script.getRoute().equals("dbConfig") && script.getMethod().equals(RouteType.DATABASE))
               .findFirst();
       return null;
    }



    public String waitingValueOfScript(String script, List<Map<String, Object>> params ){
        Pattern pattern = Pattern.compile("waiting\\.valueOfScript\\((\"?\\w+\"?)\\)");
        Matcher matcher = pattern.matcher(script);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()){
            String scriptName = matcher.group(1).replace("\"", "");
            String scriptValue = executeLuaCode(scriptName, params);
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

    public String executeLuaCode(String name, List<Map<String, Object>> params){
        try{

            Optional<LuaScriptEntity> optionalLuaScriptEntity = luaRepository.findByRoute(name);
            if (optionalLuaScriptEntity.isEmpty()){
                return "Script não encontrado!";
            }
            String script = optionalLuaScriptEntity.get().getScript();
            String scriptName = optionalLuaScriptEntity.get().getRoute();
            String complete = ReplaceWaitingValues(script, params);
            LuaValue chunk = globals.load(complete, scriptName);
            LuaValue response = chunk.call();
            System.out.println(complete);
            return response.toString();
        } catch (LuaError e) {
            // Handle Lua-specific exceptions
            return "Erro na execução do script Lua: " + e.getMessage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
