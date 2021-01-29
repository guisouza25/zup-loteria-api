package br.com.zup.loteria.controller.request.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.loteria.model.Usuario;
import br.com.zup.loteria.validators.EmailExistente;

public class UsuarioForm {
	
	@Email(message = "Informe um email válido")
	@NotEmpty(message = "Campo email não pode ser em branco")
	@NotNull(message = "Campo email não pode ser nulo")
	@EmailExistente(message = "Usuário existente para o email informado")
	private String email;

	
	public String getEmail() {
		return email;
	}


	public Usuario converter() {
		return new Usuario(this.email);
	}
	
	
}
