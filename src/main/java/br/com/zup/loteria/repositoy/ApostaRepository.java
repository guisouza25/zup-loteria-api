package br.com.zup.loteria.repositoy;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.zup.loteria.model.Aposta;

public interface ApostaRepository extends JpaRepository<Aposta, Long> {
	
	@Query("SELECT a FROM Aposta a WHERE a.usuario.email = :email ORDER BY a.dataAposta DESC")
	List<Aposta> findByUsuarioEmail(@Param("email") String email);
}
