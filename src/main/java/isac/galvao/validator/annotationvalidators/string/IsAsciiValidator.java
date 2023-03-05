package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsAscii;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsAsciiValidator implements AnnotationValidatorInterface<IsAscii> {
    @Override
    public boolean validate(FieldHelper helper, IsAscii annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            String asciiRegex = "^[\\x00-\\x7F]+$";
            return Pattern.compile(asciiRegex).matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsAscii annotation) {
        String message = String.format("'%s' precisa conter apenas caracteres ASCII", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
