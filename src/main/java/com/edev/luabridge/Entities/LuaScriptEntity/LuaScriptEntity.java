package com.edev.luabridge.Entities.LuaScriptEntity;

import com.edev.luabridge.Entities.APIEntity.ApiEntity;
import com.edev.luabridge.Models.RouteTypeModel.RouteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@Builder
public class LuaScriptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String route;
    private RouteType method;
    private String script;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "api_entity_id" )
    private ApiEntity apiEntity;
    public LuaScriptEntity(){

    }
}
