package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.NotContains;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

public class NotContainsValidator implements AnnotationValidatorInterface<NotContains> {
    @Override
    public boolean validate(FieldHelper helper, NotContains annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            if (annotation.ignoreCase())
                return !str.toLowerCase().contains(annotation.value().toLowerCase());
            return !str.contains(annotation.value());
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, NotContains annotation) {
        String message = String.format("'%s' não pode conter o valor '%s'", helper.getAttributeName(), annotation.value());
        return new ValidationError(helper, message, annotation);
    }
}
