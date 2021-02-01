package br.com.zup.loteria.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.loteria.config.validation.Erro;
import br.com.zup.loteria.controller.response.dto.SorteioDto;
import br.com.zup.loteria.helpers.NumerosHelper;
import br.com.zup.loteria.model.Sorteio;
import br.com.zup.loteria.model.StatusSorteio;
import br.com.zup.loteria.repositoy.SorteioRepository;

@RestController
@RequestMapping("/sorteio")
public class SorteioController {
	
	@Autowired
	SorteioRepository sorteioRepository;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarSorteio(@PathVariable(value = "id") Long id) {
		
		Optional<Sorteio> sorteio = sorteioRepository.findById(id);
		
		if(sorteio.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new SorteioDto(sorteio.get()));
		} else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new Erro(null, "Sorteio não encontrado"));
		}
	}
	
	@PostMapping("/gerar")
	@Transactional
	public ResponseEntity<SorteioDto> gerarProximoSorteio(
			@RequestParam @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime data,
			UriComponentsBuilder uriBuilder
			) {
		
		Sorteio sorteio = new Sorteio(data);
		sorteioRepository.save(sorteio);
		
		URI uri = uriBuilder.path("/sorteio/{id}").buildAndExpand(sorteio.getId()).toUri();
		
		return ResponseEntity
				.created(uri)
				.body(new SorteioDto(sorteio));
	}
	
	@PutMapping("/sortear/{id}")
	@Transactional
	public ResponseEntity<?> sortear(
			@PathVariable(value = "id") Long id) {
		
		Optional<Sorteio> sorteio = sorteioRepository.findById(id);
		
		if(!sorteio.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new Erro(null, "Sorteio inexistente"));
			
		} else if (sorteio.get().getStatus().equals(StatusSorteio.ENCERRADO)) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new Erro(null, "Sorteio encerrado"));
		}
		
		sorteio.get().setNumeros(NumerosHelper.gerarSorteio());
		sorteio.get().setStatus(StatusSorteio.ENCERRADO);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new SorteioDto(sorteio.get()));
	}
	
}
