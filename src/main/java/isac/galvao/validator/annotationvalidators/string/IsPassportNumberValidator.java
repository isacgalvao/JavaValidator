package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsPassportNumber;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is a valid passport number relative to a specific country code.
 */
public class IsPassportNumberValidator implements AnnotationValidatorInterface<IsPassportNumber> {
    @Override
    public boolean validate(FieldHelper helper, IsPassportNumber annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            /* Remove All Whitespaces, Convert to UPPERCASE */
            final String normalizedStr = str.replaceAll("\\s", "").toUpperCase();

            return Pattern.compile(annotation.value().getValue()).matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsPassportNumber annotation) {
        String message = String.format("'%s' precisa ser um número de passaporte válido", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
