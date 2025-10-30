package com.login.exemplo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.exemplo.entity.Produto;
import com.login.exemplo.repositories.ProdutoRepository;

@RestController
public class ProdutoController {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping(value = "mostrar/produto/{id}")
	public Optional<Produto> mostrarProdutoPorId(@PathVariable int id) {
		
		return produtoRepository.findById(id);
	}
	
	@GetMapping(value = "mostrar/produtos")
	public List<Produto> mostrarProdutos() {
		
		return produtoRepository.findAll();
	}
	
	@PostMapping(value = "produto/cadastro")
	public ResponseEntity<?> cadastraProduto(@RequestBody Produto produto) {
		Produto prod = new Produto(produto.getNome(), produto.getPreco(), produto.getQuantidade());
		produtoRepository.save(prod);
		
		return ResponseEntity.ok("Produto cadastrado!" + produto.toString());
	}
	
	@PutMapping(value = "alterar/produto/quantidade/{id}")
	public ResponseEntity<?> alterarQuantidade(@PathVariable int id, @RequestBody Produto produto) {
		Optional<Produto> produtoExiste = produtoRepository.findById(id);
		if (produtoExiste.isPresent()) {
			Produto prod = produtoExiste.get();
			produto.setQuantidade(produto.getQuantidade());
			produtoRepository.save(produto);
			return ResponseEntity.ok(prod);
		} else {
			return ResponseEntity.notFound().build();
			
		}		
	}
	
	
	@DeleteMapping(value = "deletar/produto/{id}")
	public ResponseEntity<?> deletarProduto(@PathVariable int id) {
		if(produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);			
			return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");			
		}
		
	}
}
