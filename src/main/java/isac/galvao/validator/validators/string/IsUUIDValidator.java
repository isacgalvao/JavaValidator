package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsUUID;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Checks if the string is a UUID (version 3, 4 or 5).
 */
public class IsUUIDValidator implements AnnotationValidatorInterface<IsUUID> {
    @Override
    public boolean validate(FieldHelper helper, IsUUID annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            return Pattern.compile(annotation.version().getValue(), Pattern.CASE_INSENSITIVE).matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsUUID annotation) {
        String message = String.format("'%s' precisa ser um UUID", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
