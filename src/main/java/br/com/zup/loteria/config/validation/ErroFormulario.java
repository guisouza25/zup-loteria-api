package br.com.zup.loteria.config.validation;

public class ErroFormulario {
	
	private String campo;
	private String erro;
	
	
	public ErroFormulario(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}


	public String getCampo() {
		return campo;
	}


	public String getErro() {
		return erro;
	}
}
