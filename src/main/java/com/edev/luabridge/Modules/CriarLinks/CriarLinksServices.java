package com.edev.luabridge.Modules.CriarLinks;

import java.util.Map;

public interface CriarLinksServices{
    String criarLinkVerificacao(String reciver);
    boolean ContasParaVerificar(String email, String token);
}
