package com.malu.confeitaria.web.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.malu.confeitaria.exceptions.EntityNotFoundException;
import com.malu.confeitaria.exceptions.UsernameUniqueViolationException;
import com.malu.confeitaria.models.Usuario;
import com.malu.confeitaria.repositories.UsuarioRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		try {
			usuario.setSenha(passwordEncoder.encode(usuario.getPassword()));
			return usuarioRepository.save(usuario);
		} catch (org.springframework.dao.DataIntegrityViolationException ex) {
			throw new UsernameUniqueViolationException(String.format("Username %s já cadastrado", usuario.getUsername()));
		}
	}
	
	@Transactional
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Usuário id = %s não encontrado.", id))
			);
	}
	
	@Transactional
	public Usuario editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
		if (!novaSenha.equals(confirmaSenha)) {
			throw new RuntimeException("As senhas devem ser iguais.");
		}
		
		Usuario user = buscarPorId(id);
		if (!user.getSenha().equals(senhaAtual)) {
			throw new RuntimeException("A senha digitada não confere com a senha cadastrada.");
		}
		
		user.setSenha(novaSenha);
		return user;
	}
	
	@Transactional
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}

}
