package com.edev.luabridge.DTOs.ApiDTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;
@Builder
public record ApiRetornoDTO(@NotNull UUID id, @NotNull String name, @NotNull String token) {
}
