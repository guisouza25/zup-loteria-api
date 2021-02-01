package br.com.zup.loteria.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.loteria.config.validation.Erro;
import br.com.zup.loteria.controller.response.dto.ApostaDto;
import br.com.zup.loteria.helpers.NumerosHelper;
import br.com.zup.loteria.model.Aposta;
import br.com.zup.loteria.model.Sorteio;
import br.com.zup.loteria.model.StatusSorteio;
import br.com.zup.loteria.model.Usuario;
import br.com.zup.loteria.repositoy.ApostaRepository;
import br.com.zup.loteria.repositoy.SorteioRepository;
import br.com.zup.loteria.repositoy.UsuarioRepository;
import br.com.zup.loteria.validators.QuantidadeNumerosAposta;

@Validated
@RestController
@RequestMapping("/aposta")
public class ApostaController {
	
	@Autowired
	ApostaRepository apostaRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	SorteioRepository sorteioRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> realizarAposta(
			@RequestParam(required = true) String email,
			@RequestParam(required = true, name = "sorteio") Long sorteioId,
			@RequestParam(required = true) @QuantidadeNumerosAposta String quantidadeNumeros
			) {
		
		Integer quantidade = Integer.parseInt(quantidadeNumeros);
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		Optional<Sorteio> sorteio = sorteioRepository.findById(sorteioId);
		
		if(!usuario.isPresent() || !sorteio.isPresent()){
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new Erro("email, sorteio", "Email ou sorteio inexistentes para os dados informados"));
		
		} 
		
		if(sorteio.get().getStatus().equals(StatusSorteio.ENCERRADO)) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new Erro("sorteio", "Sorteio encerrado"));
		}
			
		Aposta aposta = new Aposta(NumerosHelper.gerarAposta(quantidade), usuario.get(), sorteio.get());
		apostaRepository.save(aposta);
			
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ApostaDto(aposta));		
		
	}
	
	@GetMapping
	public ResponseEntity<List<ApostaDto>> listarApostaPorUsuario(
			@RequestParam(required = true) String email
			) {
		
		List<Aposta> apostas = apostaRepository.findByUsuarioEmail(email);
		
		if(apostas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ApostaDto.converter(apostas));
	}
	
	
	
	
}
