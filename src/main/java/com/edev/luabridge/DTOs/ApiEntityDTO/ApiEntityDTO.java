package com.edev.luabridge.DTOs.ApiEntityDTO;

import com.edev.luabridge.DTOs.LuaScriptDTO.LuaScriptDTO;
import com.edev.luabridge.Entities.LuaScriptEntity.LuaScriptEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Builder
public record ApiEntityDTO(@NotNull String name, @NotNull UUID id, List<LuaScriptDTO> rotas) {
}
