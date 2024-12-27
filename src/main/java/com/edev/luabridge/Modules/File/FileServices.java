package com.edev.luabridge.Modules.File;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
// Aqui é onde ocorre a procura pelos arquivos dentro dos diretórios
// Leitura dos Files
public interface FileServices {
    File encontrarArquivos(String name, String method);
    File encontrarArquivosImportados(String name);
    String readFile(File arquivo) throws IOException;
}
