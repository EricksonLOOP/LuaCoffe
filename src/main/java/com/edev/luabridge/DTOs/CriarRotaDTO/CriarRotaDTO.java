package com.edev.luabridge.DTOs.CriarRotaDTO;

import jakarta.validation.constraints.NotNull;

public record CriarRotaDTO(@NotNull String route,  String method, @NotNull String apiToken) {
}
