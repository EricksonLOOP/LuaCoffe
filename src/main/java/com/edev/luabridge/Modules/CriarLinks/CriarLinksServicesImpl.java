package com.edev.luabridge.Modules.CriarLinks;

import com.edev.luabridge.Entities.UserEntity.UserEntity;
import com.edev.luabridge.Models.Roles.Roles;
import com.edev.luabridge.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class CriarLinksServicesImpl implements CriarLinksServices {
    @Autowired
    private final UserRepository userRepository;

    private Map<String, String> contasParaVerificarMap = new HashMap<>();

    public CriarLinksServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean ContasParaVerificar(String email, String token) {
        // Verifica se o token existe no mapa e se o email corresponde
        if (!contasParaVerificarMap.containsKey(token) || !contasParaVerificarMap.get(token).equals(email)) {
            return false;
        }

        // Verifica se o usuário existe no repositório
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);
        if (optionalUserEntity.isEmpty()) {
            return false;
        }

        // Atualiza o status do usuário e salva
        UserEntity user = optionalUserEntity.get();
        user.setEmailVerified(true);
        user.setRoles(Roles.USER);
        userRepository.save(user);

        // Remove o token do mapa após verificação
        contasParaVerificarMap.remove(token);

        return true;
    }

    @Override
    public String criarLinkVerificacao(String reciver) {
        // Gera um token único e o associa ao email
        UUID token = UUID.randomUUID();
        contasParaVerificarMap.put(token.toString(), reciver);

        // Retorna o link de verificação
        return "http://localhost:8080/api/actions/auth/account/verify/" + token + "/" + reciver;
    }
}
