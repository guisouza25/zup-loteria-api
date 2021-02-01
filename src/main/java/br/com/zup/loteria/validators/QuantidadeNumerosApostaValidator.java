package br.com.zup.loteria.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuantidadeNumerosApostaValidator implements ConstraintValidator<QuantidadeNumerosAposta, String> {

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
    	
    	Integer numero = Integer.parseInt(object);
    			
    	if(numero < 6 || numero > 15) {
    		return false;
    	} else {
    		return true;
    	}   
    }
}