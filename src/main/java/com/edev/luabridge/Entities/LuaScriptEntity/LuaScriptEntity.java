package com.edev.luabridge.Entities.LuaScriptEntity;

import com.edev.luabridge.Models.RouteTypeModel.RouteType;
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
public class LuaScriptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private RouteType route;
    private String script;
    public LuaScriptEntity(){

    }
}
