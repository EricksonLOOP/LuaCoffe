package com.edev.luabridge.Repositories;

import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LuaRepository extends JpaRepository<LuaScriptEntity, UUID> {
    Optional<LuaScriptEntity> findByRoute(String route);
}
