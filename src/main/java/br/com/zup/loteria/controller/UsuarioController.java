package br.com.zup.loteria.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.loteria.controller.request.dto.UsuarioForm;
import br.com.zup.loteria.model.Aposta;
import br.com.zup.loteria.model.Usuario;
import br.com.zup.loteria.repositoy.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Aposta> cadastrarUsuario(@RequestBody @Valid UsuarioForm usuarioForm ) {
		
		Usuario usuario = usuarioForm.converter();
		usuarioRepository.save(usuario);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.build();
	}
	
}
