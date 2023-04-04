package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsNumberString;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Checks if the string is numeric.
 */
public class IsNumberStringValidator implements AnnotationValidatorInterface<IsNumberString> {

    private final Pattern numericNoSymbols = Pattern.compile("^[0-9]+$");

    @Override
    public boolean validate(FieldHelper helper, IsNumberString annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            if (annotation.noSymbols()) return this.numericNoSymbols.matcher(str).matches();

            return Pattern.compile("^[+-]?([0-9]*[%s])?[0-9]+$".formatted(annotation.locale().getValue())).matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsNumberString annotation) {
        String message = String.format("'%s' precisa ser uma string numérica", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
