package com.edev.luabridge.Entities.UserEntity;

import com.edev.luabridge.Entities.APIEntity.ApiEntity;
import com.edev.luabridge.Models.Roles.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String cargo;
    private Boolean emailVerified;
    private Roles roles;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApiEntity> apis;
}
