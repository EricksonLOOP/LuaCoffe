package com.edev.luabridge.Modules.File;

import java.io.File;
import java.io.IOException;

// Aqui é onde ocorre a procura pelos arquivos dentro dos diretórios
// Leitura dos Files
public interface FileServices {
    File findFile(String name, String method);
    File findImportedFiles(String name);
    String readFile(File arquivo) throws IOException;
}
