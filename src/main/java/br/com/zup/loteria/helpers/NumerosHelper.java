package br.com.zup.loteria.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class NumerosHelper {
	
	public static Set<Integer> gerarAposta(Integer quantidadeNumeros) {

		Set<Integer> numeros = new HashSet<Integer>();
		
		for (int i = 0; i < quantidadeNumeros; i++) {
			Integer numeroAleatorio = new Random().nextInt(61);
			
			while(numeros.contains(numeroAleatorio)) {
				numeroAleatorio = new Random().nextInt(61);
			}
			numeros.add(numeroAleatorio);
		}
		return numeros;		
	}
	
	public static Set<Integer> gerarSorteio() {

		Set<Integer> numeros = new HashSet<Integer>();
		
		for (int i = 0; i < 6; i++) {
			Integer numeroAleatorio = new Random().nextInt(61);
			
			while(numeros.contains(numeroAleatorio)) {
				numeroAleatorio = new Random().nextInt(61);
			}
			numeros.add(numeroAleatorio);
		}
		return numeros;	
	}
	
	public static String converteEOrdenaNumeros(Set<Integer> numeros) {
		
		List<Integer> sortedList = new ArrayList<>(numeros);
		Collections.sort(sortedList);
		
		String numerosOrdenados = sortedList
				.stream()
				.map(Object::toString)
				.collect(Collectors.joining(","));
		
		return numerosOrdenados;
	}

}
