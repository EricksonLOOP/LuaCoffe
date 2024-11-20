package com.edev.luabridge.DTOs.UserDTOs.LoginUserDTO;

import jakarta.validation.constraints.NotNull;

public record LoginUserDTO(@NotNull String email, @NotNull String password) {
}
