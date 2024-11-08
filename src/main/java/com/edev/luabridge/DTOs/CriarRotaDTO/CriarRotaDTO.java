package com.edev.luabridge.DTOs.CriarRotaDTO;

import jakarta.validation.constraints.NotNull;

public record CriarRotaDTO(@NotNull String name, @NotNull String route, @NotNull String apiToken) {
}
