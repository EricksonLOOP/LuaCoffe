package com.edev.luabridge.Configuration.Security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class PredefinedTokenAuthentication extends AbstractAuthenticationToken {
    public PredefinedTokenAuthentication() {
        super(Collections.emptyList()); // Sem permissões específicas
        setAuthenticated(true); // Marca como autenticado
    }
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
