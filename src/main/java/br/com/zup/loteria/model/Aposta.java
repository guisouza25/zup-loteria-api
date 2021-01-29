package br.com.zup.loteria.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Aposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dataDaAposta = LocalDateTime.now();
	private String numerosGerados;

	@ManyToOne
	private Usuario usuario;

	
	public Aposta() {
		
	}
	
	public Aposta(String numerosGerados, Usuario usuario) {
		this.numerosGerados = numerosGerados;
		this.usuario = usuario;
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

	public Usuario getUsuario() {
		return usuario;
	}

	
	public static String gerarNumerosAleatorios(Integer quantidade) {

		List<Integer> numeros = new ArrayList<Integer>();
		
		for (int i = 0; i < quantidade; i++) {
			Integer numeroAleatorio = new Random().nextInt(61);
			
			while(numeros.contains(numeroAleatorio)) {
				numeroAleatorio = new Random().nextInt(61);
			}
			
			numeros.add(numeroAleatorio);
		}

		return numeros
				.stream()
				.map(Object::toString)
				.collect(Collectors.joining(", "));
		
	}
}
