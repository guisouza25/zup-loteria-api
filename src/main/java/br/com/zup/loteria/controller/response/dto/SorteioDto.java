package br.com.zup.loteria.controller.response.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.loteria.helpers.NumerosHelper;
import br.com.zup.loteria.model.Sorteio;
import br.com.zup.loteria.model.StatusSorteio;

public class SorteioDto {
	
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataSorteio;
	
	private StatusSorteio status;
	
	private String numeros;
	
	public SorteioDto(Sorteio sorteio) {
		this.id = sorteio.getId();
		this.dataSorteio = sorteio.getDataSorteio();
		this.status = sorteio.getStatus();
		this.numeros = NumerosHelper.converteEOrdenaNumeros(sorteio.getNumeros());
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataSorteio() {
		return dataSorteio;
	}

	public StatusSorteio getStatus() {
		return status;
	}

	public String getNumeros() {
		return numeros;
	}
	
	
}
