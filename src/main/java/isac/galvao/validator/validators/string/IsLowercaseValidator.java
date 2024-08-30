package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsLowercase;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

/**
 * Checks if the string is lowercase.
 */
public class IsLowercaseValidator implements AnnotationValidatorInterface<IsLowercase> {
    @Override
    public boolean validate(FieldHelper helper, IsLowercase annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            return str.equals(str.toLowerCase());
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsLowercase annotation) {
        String message = String.format("'%s' precisa ser uma string em lowercase", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
