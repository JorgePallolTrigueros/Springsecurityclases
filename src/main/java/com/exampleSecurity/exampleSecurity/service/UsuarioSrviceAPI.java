package com.exampleSecurity.exampleSecurity.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.exampleSecurity.exampleSecurity.entity.Usuario;



public interface UsuarioSrviceAPI {

	Page<Usuario> gettAll (Pageable pageable);
	
}
