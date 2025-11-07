package com.login.exemplo.dto;

import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.login.exemplo.entity.Produto;


@JsonPropertyOrder({"nome", "preco", "quantidade", "totalFormatado"})
public class ProdutoResponseDTO {
	
	private String nome;
	private double preco;
	private int quantidade;
	private double subtotal;
	private String totalFormatado;
	
	public ProdutoResponseDTO(Produto prod, double subtotal) {
		this.nome = prod.getNome();
		this.preco = prod.getPreco();
		this.quantidade = prod.getQuantidade();

		DecimalFormat df = new DecimalFormat("R$ #,##0.00");
		this.totalFormatado = df.format(this.subtotal);
	}
	
	public String getNome() {
		return nome;
	}
	public double getPreco() {
		return preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	
	public String getSubtotal() {
		return totalFormatado;
	}
}
