package br.com.zup.loteria.controller.response.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.loteria.helpers.NumerosHelper;
import br.com.zup.loteria.model.Aposta;

public class ApostaDto {
	
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataDaAposta;
	private String numeros;
	private String emailUsuario;
	private Long sorteio;
	
	public ApostaDto(Aposta aposta) {
		this.id = aposta.getId();
		this.dataDaAposta = aposta.getDataAposta();
		this.numeros = NumerosHelper.converteEOrdenaNumeros(aposta.getNumeros());
		this.emailUsuario = aposta.getUsuario().getEmail();
		this.sorteio = aposta.getSorteio().getId();
	}

	public Long getId() {
		return id;
	}
	
	public LocalDateTime getDataDaAposta() {
		return dataDaAposta;
	}

	public String getNumeros() {
		return numeros;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}
	
	public Long getSorteio() {
		return sorteio;
	}

	public static List<ApostaDto> converter(List<Aposta> apostas) {
		return apostas.stream().map(ApostaDto::new).collect(Collectors.toList());
				
	}
	
	
}
