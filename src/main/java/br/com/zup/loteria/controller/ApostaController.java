package br.com.zup.loteria.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.loteria.controller.response.dto.ApostaDto;
import br.com.zup.loteria.model.Aposta;
import br.com.zup.loteria.model.Usuario;
import br.com.zup.loteria.repositoy.ApostaRepository;
import br.com.zup.loteria.repositoy.UsuarioRepository;

@RestController
@RequestMapping("/aposta")
public class ApostaController {
	
	@Autowired
	ApostaRepository apostaRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<ApostaDto> realizarAposta(
			@RequestParam(required = true) String email, 
			@RequestParam(required = true) Integer quantidadeNumeros) {
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		
		if(usuario.isPresent()) {

			Aposta aposta = new Aposta(
					Aposta.gerarNumerosAleatorios(quantidadeNumeros), 
					usuario.get());
			
			apostaRepository.save(aposta);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApostaDto(aposta));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				
		}	
	}
	
	@GetMapping
	@Transactional
	public ResponseEntity<List<ApostaDto>> listarApostaPorUsuario(
			@RequestParam(required = true) String email) {
		
		List<Aposta> apostas = apostaRepository.findByUsuarioEmail(email);
		
		if(apostas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ApostaDto.converter(apostas));
	}
	
	
	
	
}
