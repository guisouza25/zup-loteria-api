package br.com.zup.loteria.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.loteria.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);
}
