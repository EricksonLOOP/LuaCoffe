package com.edev.luabridge.Entities.APIEntity;

import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
public class ApiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String apiToken;
    @OneToMany
    private List<LuaScriptEntity> rotas;
    public ApiEntity(){

    }

}
