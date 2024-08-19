package com.malu.confeitaria.web.dto;

import com.malu.confeitaria.models.Usuario.Role;

public record RegisterDto(String username, String senha, Role role) {

}
