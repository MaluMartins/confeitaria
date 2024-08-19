package com.malu.confeitaria.web.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioSenhaDto {
	
	@NotBlank
	@Size(min = 8, max = 15)
	private String senhaAtual;
	
	@NotBlank
	@Size(min = 8, max = 15)
	private String novaSenha;
	
	@NotBlank
	@Size(min = 8, max = 15)
	private String confirmaSenha;
}
