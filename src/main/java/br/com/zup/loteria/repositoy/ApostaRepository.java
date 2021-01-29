package br.com.zup.loteria.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.loteria.model.Aposta;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {
	
	List<Aposta> findByUsuarioEmail(String email);
}
