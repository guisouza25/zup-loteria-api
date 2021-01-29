package br.com.zup.loteria.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.zup.loteria.model.Usuario;
import br.com.zup.loteria.repositoy.UsuarioRepository;

public class EmailExistenteValidator implements ConstraintValidator<EmailExistente, String> {

    //private String message;
	
	@Autowired
	UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    	
    	Optional<Usuario> usuario = usuarioRepository.findByEmail(object);
    			
    	if(usuario.isPresent()) {
    		return false;
    	} else {
    		return true;
    	}   
    }
}