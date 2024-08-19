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

import com.malu.confeitaria.models.Cliente;
import com.malu.confeitaria.repositories.ClienteRepository;
import com.malu.confeitaria.web.dto.ClienteCreateDTO;
import com.malu.confeitaria.web.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("clientes")
public class ClienteController {
	@Autowired
	private ClienteRepository clienteRepository; 
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Cliente> addClient(@RequestBody @Valid ClienteCreateDTO createDto) {
		Cliente newClient = new Cliente(createDto.nome(), createDto.email(), createDto.telefone(), createDto.cidade(),
				createDto.bairro(), createDto.logradouro(), createDto.numero_casa());
		
		this.clienteRepository.save(newClient);
		
		return ResponseEntity.ok(newClient);
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClients() {
		return ResponseEntity.ok(clienteRepository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Cliente> getOneClient(@PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);
		
		return ResponseEntity.ok(cliente);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> updateClient(@PathVariable Long id, @RequestBody @Valid ClienteCreateDTO createDto) {
		Optional<Cliente> clienteO = clienteRepository.findById(id);
		if (clienteO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
		}
		Cliente cliente = clienteO.get();
		BeanUtils.copyProperties(createDto, cliente);
		return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") Long id) {
		Optional<Cliente> clienteO = clienteRepository.findById(id);
		
		if (clienteO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
		}
		
		clienteRepository.delete(clienteO.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso.");
	}
}
