package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsUppercase;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

/**
 * Checks if the string is uppercase.
 */
public class IsUppercaseValidator implements AnnotationValidatorInterface<IsUppercase> {
    @Override
    public boolean validate(FieldHelper helper, IsUppercase annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            return str.equals(str.toUpperCase());
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsUppercase annotation) {
        String message = String.format("'%s' precisa ser maiúsculo", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
