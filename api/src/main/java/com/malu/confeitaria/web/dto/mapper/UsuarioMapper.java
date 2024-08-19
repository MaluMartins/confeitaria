package com.malu.confeitaria.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.malu.confeitaria.models.Usuario;
import com.malu.confeitaria.web.dto.UsuarioCreateDto;
import com.malu.confeitaria.web.dto.UsuarioResponseDto;

public class UsuarioMapper {
private static ModelMapper mapper = new ModelMapper();
	
	public static Usuario toUsuario(UsuarioCreateDto createDto) {
		return new ModelMapper().map(createDto, Usuario.class);
	}
	
	public static UsuarioResponseDto toDto(Usuario usuario) {
		return mapper.map(usuario, UsuarioResponseDto.class);
	}
	
	public static List<UsuarioResponseDto> toListDto(List<Usuario> usuarios) {
		return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
	}
}
