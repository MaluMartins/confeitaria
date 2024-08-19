package com.malu.confeitaria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malu.confeitaria.models.Usuario;
import com.malu.confeitaria.web.dto.UsuarioCreateDto;
import com.malu.confeitaria.web.dto.UsuarioResponseDto;
import com.malu.confeitaria.web.dto.UsuarioSenhaDto;
import com.malu.confeitaria.web.dto.mapper.UsuarioMapper;
import com.malu.confeitaria.web.services.UsuarioService;

import org.springframework.security.authentication.AuthenticationManager;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto) {
		Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
		Usuario user = usuarioService.buscarPorId(id);
		
		return ResponseEntity.ok(UsuarioMapper.toDto(user));
	}
	
	@GetMapping
	public ResponseEntity <List<UsuarioResponseDto>> getAll() {
		List<Usuario> users = usuarioService.buscarTodos();
		
		return ResponseEntity.ok(UsuarioMapper.toListDto(users));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto senhaDto) {
		Usuario user = usuarioService.editarSenha(id, senhaDto.getSenhaAtual(), senhaDto.getNovaSenha(), senhaDto.getConfirmaSenha());
		
		return ResponseEntity.noContent().build();
	}
	
}
