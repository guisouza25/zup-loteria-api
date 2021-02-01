package br.com.zup.loteria.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = QuantidadeNumerosApostaValidator.class)
@Documented
//@Repeatable(List.class)
public @interface QuantidadeNumerosAposta {

    String message() default "Quantidade de numeros deve ser entre 6 e 15";
            
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    //String message();

    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
    	QuantidadeNumerosAposta[] value();
    }
}