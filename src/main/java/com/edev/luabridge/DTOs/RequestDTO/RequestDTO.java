package com.edev.luabridge.DTOs.RequestDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.luaj.vm2.ast.Str;

import java.util.List;
import java.util.Map;

public record RequestDTO(@NotNull String route, @NotNull String apiToken, @NotNull String method, List<Map<String, Object>> params) {
}
