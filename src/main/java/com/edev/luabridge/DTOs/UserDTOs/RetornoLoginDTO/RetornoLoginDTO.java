package com.edev.luabridge.DTOs.UserDTOs.RetornoLoginDTO;

import com.edev.luabridge.DTOs.ApiDTOs.ApiRetornoDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.UUID;
@Builder
public record RetornoLoginDTO (@NotNull UUID id, @NotNull String name, @NotNull String email, @NotNull List<ApiRetornoDTO> apis, @NotNull String token) {
}
