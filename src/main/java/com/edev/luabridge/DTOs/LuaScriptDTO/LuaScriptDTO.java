package com.edev.luabridge.DTOs.LuaScriptDTO;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LuaScriptDTO(@NotNull String name, String script, @NotNull String route, @NotNull UUID id) {
}
