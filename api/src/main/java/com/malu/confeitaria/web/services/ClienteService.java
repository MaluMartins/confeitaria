package com.malu.confeitaria.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malu.confeitaria.exceptions.EntityNotFoundException;
import com.malu.confeitaria.models.Cliente;
import com.malu.confeitaria.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id not found: " + id));
	}
}
