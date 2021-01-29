package br.com.zup.loteria.controller.response.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.loteria.model.Aposta;

public class ApostaDto {
	
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataDaAposta;
	private String numerosGerados;
	private String emailUsuario;
	
	public ApostaDto(Aposta aposta) {
		this.id = aposta.getId();
		this.dataDaAposta = aposta.getDataDaAposta();
		this.numerosGerados = aposta.getNumerosGerados();
		this.emailUsuario = aposta.getUsuario().getEmail();
	}

	public Long getId() {
		return id;
	}
	
	public LocalDateTime getDataDaAposta() {
		return dataDaAposta;
	}

	public String getNumerosGerados() {
		return numerosGerados;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public static List<ApostaDto> converter(List<Aposta> apostas) {
		return apostas.stream().map(ApostaDto::new).collect(Collectors.toList());
				
	}
	
	
}
