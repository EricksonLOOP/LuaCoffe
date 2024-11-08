package com.edev.luabridge.Repositories;

import com.edev.luabridge.Entities.APIEntity.ApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApiRepository extends JpaRepository<ApiEntity, UUID> {
    Optional<ApiEntity> findByApiToken(String token);
    Optional<ApiEntity> findByName(String name);
}
