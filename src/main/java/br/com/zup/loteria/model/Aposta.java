package br.com.zup.loteria.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Aposta implements Serializable  {

	private static final long serialVersionUID = 8064027695516453633L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime dataAposta = LocalDateTime.now();
	
	@ElementCollection
	@Column(name = "numeros", nullable = false)
	private Set<Integer> numeros = new HashSet<Integer>();

	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Sorteio sorteio;

	
	public Aposta() {
	}
	
	public Aposta(Set<Integer> numeros, Usuario usuario, Sorteio sorteio) {
		this.numeros = numeros;
		this.usuario = usuario;
		this.sorteio = sorteio;
	}

	
	public Long getId() {
		return id;
	}

	public LocalDateTime getDataAposta() {
		return dataAposta;
	}

	public Set<Integer> getNumeros() {
		return numeros;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public Sorteio getSorteio() {
		return sorteio;
	}
	
	
}
