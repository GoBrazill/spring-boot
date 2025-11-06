package com.login.exemplo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDTO {
	
	@NotBlank(message = "O nome do produto não pode ser vazio!")
	private String nome;
	
	@DecimalMin(value = "10.00", message = "O valor deve ser no mínimo 10 reais")
	private double preco;
	
	@Size(min = 1, message = "A quantidade não pode ser menor que 1")
	private int quantidade;

	public ProdutoRequestDTO() {
		
	}
	
	public ProdutoRequestDTO(String nome, double preco, int quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
