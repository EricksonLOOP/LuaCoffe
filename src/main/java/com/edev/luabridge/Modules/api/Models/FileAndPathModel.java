package com.edev.luabridge.Modules.api.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class FileAndPathModel {
    String path;
    String file;
}
