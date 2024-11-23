package com.edev.luabridge.DTOs.UserDTOs.CreateUserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(@Email @NotNull String email, @NotNull String password) {
}
