package br.com.zup.loteria.config.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroRequestParamHandler {
		
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	
	@ExceptionHandler(ConstraintViolationException.class) 
	public List<Erro> handle(ConstraintViolationException exception) {
		
		List<Erro> listaErros = new ArrayList<>();
		
		Set<ConstraintViolation<?>> fieldErrors = exception.getConstraintViolations();
		
		fieldErrors.forEach(e -> {
			
			String mensagem = e.getMessage();
			String campo = e.getPropertyPath().toString();
			Erro erroFormulario = new Erro(campo, mensagem);
			listaErros.add(erroFormulario);
		});
		
		
		
		return listaErros;
	}
}
