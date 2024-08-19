package com.malu.confeitaria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malu.confeitaria.models.Usuario;
import com.malu.confeitaria.repositories.UsuarioRepository;
import com.malu.confeitaria.web.dto.AuthenticationDTO;
import com.malu.confeitaria.web.dto.LoginResponseDTO;
import com.malu.confeitaria.web.dto.RegisterDto;
import com.malu.confeitaria.web.dto.UsuarioCreateDto;
import com.malu.confeitaria.web.services.TokenService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
		try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            
            var token = tokenService.generateToken((Usuario) auth.getPrincipal());
            
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Usuário ou senha inválidos.");
        }
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> register(@RequestBody @Valid RegisterDto data) {
		if(this.usuarioRepository.findByUsername(data.username()) != null) {
			return ResponseEntity.badRequest().body("Nome de usuário já está sendo usado.");
		}
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
		Usuario newUser = new Usuario(data.username(), encryptedPassword, data.role());
		
		this.usuarioRepository.save(newUser);
		
		return ResponseEntity.ok().build();
	}
}
