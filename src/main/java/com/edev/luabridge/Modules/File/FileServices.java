package com.edev.luabridge.Modules.File;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface FileServices {
    File encontrarArquivos(String name, String method);
    File encontrarArquivosImportados(String name);

    String readFile(File arquivo) throws IOException;
}
