package com.malu.confeitaria.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malu.confeitaria.models.Produto;
import com.malu.confeitaria.repositories.ProdutoRepository;
import com.malu.confeitaria.web.dto.ProdutoCreateDto;
import com.malu.confeitaria.web.services.ProdutoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@PostMapping
	public ResponseEntity<Produto> addProduct(@RequestBody @Valid ProdutoCreateDto createDto) {
		Produto newProduct = new Produto(createDto.nome(), createDto.descricao(), createDto.valor(), createDto.imagem());
		
		this.produtoRepository.save(newProduct);
		
		return ResponseEntity.ok(newProduct);
	}
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAllProducts() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Produto> getOneProduct(@PathVariable Long id) {
		Produto produto = produtoService.findById(id);
		
		return ResponseEntity.ok(produto);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody @Valid ProdutoCreateDto createDto) {
		Optional<Produto> productO = produtoRepository.findById(id);
		if (productO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
		}
		Produto produto = productO.get();
		BeanUtils.copyProperties(createDto, produto);
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id) {
		Optional<Produto> productO = produtoRepository.findById(id);
		
		if (productO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
		}
		
		produtoRepository.delete(productO.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
	}
}
