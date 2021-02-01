package br.com.zup.loteria.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.loteria.model.Sorteio;
import br.com.zup.loteria.model.StatusSorteio;

public interface SorteioRepository extends JpaRepository<Sorteio, Long> {
	
	Optional<Sorteio> findByStatus(StatusSorteio status);
}
