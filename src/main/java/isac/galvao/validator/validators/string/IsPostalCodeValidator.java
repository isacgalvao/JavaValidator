package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsPostalCode;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is a postal code, in the specified locale.
 */
public class IsPostalCodeValidator implements AnnotationValidatorInterface<IsPostalCode> {
    @Override
    public boolean validate(FieldHelper helper, IsPostalCode annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            if (!annotation.value().isCaseSensitive())
                return Pattern.compile(annotation.value().getValue(), Pattern.CASE_INSENSITIVE).matcher(str).matches();
            return Pattern.compile(annotation.value().getValue()).matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsPostalCode annotation) {
        String message = String.format("'%s' precisa ser um código postal", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
