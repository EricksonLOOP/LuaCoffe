package com.edev.luabridge.Modules.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServicesImpl implements FileServices, FilenameFilter {
    @Value("${file.path}")
    private String filepath;
    @Value("${file.package.path}")
    private String filepathImported;


    @Override
    public File encontrarArquivos(String name, String method) {
        String currentDirectory = Paths.get("").toAbsolutePath().toString();
        File directory = new File(currentDirectory + filepath + method);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().equals(name+".lua")) {
                        return file;
                    }
                }
            }
        }

        return null;
    }


    @Override
    public File encontrarArquivosImportados(String name) {
        String currentDirectory = Paths.get("").toAbsolutePath().toString();
        File directory = new File(currentDirectory + filepathImported);
        if (directory.exists() && directory.isDirectory()) {

            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {

                    if (file.getName().equals(name+".lua")) {
                        return file;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public String readFile(File arquivo) throws IOException {
        return Files.readString(arquivo.toPath());
    }

    @Override
    public boolean accept(File dir, String name) {
        return false;
    }
}
