package com.edev.luabridge.DTOs.LoginDTO;

import jakarta.validation.constraints.NotNull;

public record LoginDTO (@NotNull String token){
}
