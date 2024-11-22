package com.edev.luabridge.Entities.APIEntity;

import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import com.edev.luabridge.Entities.UserEntity.UserEntity;
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
    private StatusEnum status;
    @ManyToOne
    @JoinTable(name = "user_entity_id")
    private UserEntity user;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LuaScriptEntity> rotas;
    public ApiEntity(){

    }

}
