package com.malu.confeitaria.web.dto;

public record ClienteCreateDTO(String nome, String email, String telefone, String cidade, 
		String bairro, String logradouro, String numero_casa) {

}
