package com.edev.luabridge.DTOs.ApiDTOs;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CriarApiDTO(@NotNull String name, @NotNull UUID id) {
}
