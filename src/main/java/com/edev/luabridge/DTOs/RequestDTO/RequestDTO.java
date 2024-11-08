package com.edev.luabridge.DTOs.RequestDTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

public record RequestDTO(String name, List<Map<String, Object>> params) {
}
