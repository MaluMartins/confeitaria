package com.malu.confeitaria.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malu.confeitaria.exceptions.EntityNotFoundException;
import com.malu.confeitaria.models.Produto;
import com.malu.confeitaria.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository productRepository;
	
	public Produto findById(Long id) {
		return productRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id not found: " + id));
		
	}
}
