package com.edev.luabridge.Repositories;

import com.edev.luabridge.Entities.ServicesEntitys.EmailServiceEntity.EmailServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<EmailServiceEntity, UUID> {

    Optional<EmailServiceEntity> findByEmail(String email);
}
