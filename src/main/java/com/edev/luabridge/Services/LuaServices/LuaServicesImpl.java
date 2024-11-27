package com.edev.luabridge.Services.LuaServices;

import com.edev.luabridge.DTOs.RequestDTO.RequestDTO;
import com.edev.luabridge.DTOs.ScriptDTO.ScriptDTO;
import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import com.edev.luabridge.Modules.LuaLibs.Libs.Libs;
import com.edev.luabridge.Repositories.ApiRepository;
import com.edev.luabridge.Repositories.LuaRepository;
import com.edev.luabridge.Modules.LuaLibs.LuaDB.DataBaseManager;
import com.edev.luabridge.Services.FunctionsServices.LuaActions;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LuaServicesImpl implements LuaServices{
    final private Globals globals;

    @Autowired
    final private LuaRepository luaRepository;
    @Autowired
    final private ApiRepository apiRepository;
    @Autowired
    final private LuaActions luaActions;
    private DataBaseManager dataBaseManager = new DataBaseManager();
    public LuaServicesImpl(Globals globals, LuaRepository luaRepository, ApiRepository apiRepository, LuaActions luaActions) {
        this.globals = globals;
        this.luaRepository = luaRepository;
        this.apiRepository = apiRepository;
        this.luaActions = luaActions;
    }

    @Override
    public ResponseEntity<?> runScript(RequestDTO requestDTO) {
        try{

            Optional<LuaScriptEntity> optionalLuaScriptEntity = luaRepository.findByRoute(requestDTO.route());
            if (optionalLuaScriptEntity.isEmpty()){
                return ResponseEntity.badRequest().body("Script não encontrado!");
            }
            String script = optionalLuaScriptEntity.get().getScript();
            String scriptName = optionalLuaScriptEntity.get().getRoute();
            String complete = luaActions.ReplaceWaitingValues(script, requestDTO.params());
            LuaTable luacoffe = new LuaTable();
            luacoffe.set("libs", new Libs().call());
            globals.set("luacoffe", luacoffe);
            LuaValue chunk = globals.load(complete, scriptName);
            LuaValue response = chunk.call();
            return ResponseEntity.ok().body(response.toString());
        } catch (LuaError e) {
            // Handle Lua-specific exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na execução do script Lua: " + e.getMessage());
        }catch(NullPointerException e){
            return ResponseEntity.badRequest().body("O arquivo não tem um script para ser executado");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResponseEntity<?> adicionarScript(LuaScriptEntity luaScriptEntity) {
        try{
            Optional<LuaScriptEntity> optionalLuaScriptEntity = luaRepository.findByRoute(luaScriptEntity.getRoute());
            if (optionalLuaScriptEntity.isPresent()){
                return ResponseEntity.badRequest().body("Script com mesmo nome ja existente");
            }
            LuaScriptEntity novoscript = LuaScriptEntity.builder()
                    .route(luaScriptEntity.getRoute())
                    .method(luaScriptEntity.getMethod())
                    .script(luaScriptEntity.getScript())
                    .build();
            return ResponseEntity.ok().body("Script Criado com sucesso! Script: "+ luaRepository.save(novoscript));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> deletarTudo() {
        try{
            List<LuaScriptEntity> optionalList = luaRepository.findAll();
            apiRepository.deleteAll();
            luaRepository.deleteAll();
            return ResponseEntity.ok("Tudo Deletado meu Chapa!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<?> listaScripts() {
        try{
            List<LuaScriptEntity> listaScripts = luaRepository.findAll();
            return ResponseEntity.ok().body(listaScripts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao recuperar lista!");
        }
    }

    @Override
    public ResponseEntity<?> atualizarScript(UUID id, ScriptDTO scriptDTO) {
        try{
            Optional<LuaScriptEntity> optionalLuaScriptEntity = luaRepository.findById(id);
            if (optionalLuaScriptEntity.isEmpty()){
                return ResponseEntity.badRequest().body("bdre");
            }
            LuaScriptEntity luaScriptEntity = optionalLuaScriptEntity.get();
            luaScriptEntity.setScript(scriptDTO.script());
            return ResponseEntity.ok().body(luaRepository.save(luaScriptEntity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
