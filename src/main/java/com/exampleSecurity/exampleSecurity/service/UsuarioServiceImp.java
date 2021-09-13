package com.exampleSecurity.exampleSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.exampleSecurity.exampleSecurity.entity.Usuario;
import com.exampleSecurity.exampleSecurity.repository.UsuarioRepository;



@Service
public class UsuarioServiceImp implements UsuarioSrviceAPI {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public Page<Usuario> gettAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	
	
}
