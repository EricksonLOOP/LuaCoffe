package com.edev.luabridge.DTOs.ApiDTOs;

import com.edev.luabridge.DTOs.LuaScriptDTO.LuaScriptDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.UUID;
@Builder
public record ApiEntityDTO(@NotNull String name, @NotNull UUID id, List<LuaScriptDTO> rotas) {
}
