package com.malu.confeitaria.web.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EncomendaCreateDTO {
	private Long id_produto;
    private Long id_cliente;
    private int quantidade;
    private String data_entrega;
    private String info_adicional;
    private String status;
}	
