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
import com.malu.confeitaria.models.Encomenda;
import com.malu.confeitaria.models.Produto;
import com.malu.confeitaria.repositories.ClienteRepository;
import com.malu.confeitaria.repositories.EncomendaRepository;
import com.malu.confeitaria.repositories.ProdutoRepository;
import com.malu.confeitaria.web.dto.EncomendaCreateDTO;
import com.malu.confeitaria.web.services.EncomendaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("encomendas")
public class EncomendaController {
	
	@Autowired
	private EncomendaRepository encomendaRepository; 
	
	@Autowired
	private EncomendaService encomendaService;
	
	@Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
	
	@PostMapping
	public ResponseEntity<Encomenda> addOrder(@RequestBody @Valid EncomendaCreateDTO encomendaDTO) {
		Optional<Produto> produtoOpt = produtoRepository.findById(encomendaDTO.getId_produto());
        Optional<Cliente> clienteOpt = clienteRepository.findById(encomendaDTO.getId_cliente());

        if (produtoOpt.isEmpty() || clienteOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Produto produto = produtoOpt.get();
        Cliente cliente = clienteOpt.get();

        Encomenda encomenda = new Encomenda();
        encomenda.setProduto(produto);
        encomenda.setCliente(cliente);
        encomenda.setQuantidade(encomendaDTO.getQuantidade());
        encomenda.setData_entrega(encomendaDTO.getData_entrega());
        encomenda.setInfo_adicional(encomendaDTO.getInfo_adicional());
        encomenda.setStatus(Encomenda.Status.valueOf(encomendaDTO.getStatus()));

        encomendaRepository.save(encomenda);
        return ResponseEntity.ok(encomenda);
	}
	
	@GetMapping
	public ResponseEntity<List<Encomenda>> getAllOrders() {
		return ResponseEntity.ok(encomendaRepository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Encomenda> getOneOrder(@PathVariable Long id) {
		Encomenda encomenda = encomendaService.findById(id);
		
		return ResponseEntity.ok(encomenda);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> updateOrder(@PathVariable Long id, @RequestBody @Valid EncomendaCreateDTO encomendaDTO) {
		Optional<Encomenda> encomendaO = encomendaRepository.findById(id);
		if (encomendaO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
		}
		
		Optional<Produto> produtoOpt = produtoRepository.findById(encomendaDTO.getId_produto());
        Optional<Cliente> clienteOpt = clienteRepository.findById(encomendaDTO.getId_cliente());

        if (produtoOpt.isEmpty() || clienteOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Produto produto = produtoOpt.get();
        Cliente cliente = clienteOpt.get();
		
		Encomenda encomenda = encomendaO.get();
        encomenda.setProduto(produto);
        encomenda.setCliente(cliente);
        encomenda.setQuantidade(encomendaDTO.getQuantidade());
        encomenda.setData_entrega(encomendaDTO.getData_entrega());
        encomenda.setInfo_adicional(encomendaDTO.getInfo_adicional());
        encomenda.setStatus(Encomenda.Status.valueOf(encomendaDTO.getStatus()));

        encomendaRepository.save(encomenda);
        return ResponseEntity.ok(encomenda);

		//BeanUtils.copyProperties(createDto, encomenda);
		//return ResponseEntity.status(HttpStatus.OK).body(encomendaRepository.save(encomenda));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") Long id) {
		Optional<Encomenda> encomendaO = encomendaRepository.findById(id);
		
		if (encomendaO.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Encomenda não encontrada.");
		}
		
		encomendaRepository.delete(encomendaO.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Encomenda deletada com sucesso.");
	}
}
