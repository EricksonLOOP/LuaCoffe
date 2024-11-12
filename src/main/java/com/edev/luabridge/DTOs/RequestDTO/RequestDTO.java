package com.edev.luabridge.DTOs.RequestDTO;

import jakarta.validation.constraints.NotNull;


import java.util.List;
import java.util.Map;

public record RequestDTO(@NotNull String route, @NotNull String apiToken,  String method, List<Map<String, Object>> params) {
}
