package br.com.zup.loteria.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sorteio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime dataSorteio;
	
	@Enumerated(EnumType.STRING)
	private StatusSorteio status = StatusSorteio.ABERTO;
	
	@ElementCollection
	@Column(name = "numeros", nullable = false)
	private Set<Integer> numeros = new HashSet<Integer>();
	
	
	public Sorteio() {
	}
	
	public Sorteio(LocalDateTime dataSorteio) {
		this.dataSorteio = dataSorteio;
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
	
	public void setStatus(StatusSorteio status) {
		this.status = status;
	}

	public Set<Integer> getNumeros() {
		return numeros;
	}
	
	public void setNumeros(Set<Integer> numeros) {
		this.numeros = numeros;
	}
	
	
}
