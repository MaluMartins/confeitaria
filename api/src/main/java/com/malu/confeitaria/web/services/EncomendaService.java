package com.malu.confeitaria.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malu.confeitaria.exceptions.EntityNotFoundException;
import com.malu.confeitaria.models.Encomenda;
import com.malu.confeitaria.repositories.EncomendaRepository;

@Service
public class EncomendaService {
	
	@Autowired
	EncomendaRepository encomendaRepository;
	
	public Encomenda findById(Long id) {
		return encomendaRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Id not found: " + id));
	}
}
