package com.malu.confeitaria.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "encomenda")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Encomenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_encomenda")
	private Long id_encomenda;
	
	@ManyToOne
	@JoinColumn(name = "id_produto", nullable=false)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable=false)
	private Cliente cliente;
	
	@Column(name = "quantidade")
	private int quantidade;
	
	@Column(name = "data_entrega")
	private String data_entrega;
	
	@Column(name = "info_adicional")
	private String info_adicional;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	public enum Status {
		NAO_INICIADO("Não iniciado"), 
		EM_ANDAMENTO("Em andamento"), 
		CONCLUIDO("Concluído");
		
		private final String descricao;

	    Status(String descricao) {
	        this.descricao = descricao;
	    }

	    public String getDescricao() {
	        return descricao;
	    }
	}
	
	public Encomenda(Cliente cliente, Produto produto, int quantidade, Date data_entrega, String info_adicional, Status status) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
		
		this.cliente = cliente;
		this.produto = produto;
		this.quantidade = quantidade;
		this.data_entrega = formato.format(data_entrega);
		this.info_adicional = info_adicional;
		this.status = status;
	}

}
