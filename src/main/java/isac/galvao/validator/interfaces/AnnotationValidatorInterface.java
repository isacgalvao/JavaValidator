package isac.galvao.validator.interfaces;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.util.FieldHelper;

import java.lang.annotation.Annotation;

/**
 * Function validators precisam implementar esta interface.
 */
public interface AnnotationValidatorInterface<T extends Annotation> {
    /**
     * Método que será chamado para realizar a validação.
     */
    boolean validate(FieldHelper helper, T annotation);

    /**
     * Retorna a mensagem padrão caso ocorra algum erro de validação.
     */
    ValidationError buildMessage(FieldHelper helper, T annotation);
}
