package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsOctal;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is a valid octal number.
 */
public class IsOctalValidator implements AnnotationValidatorInterface<IsOctal> {

    private final Pattern octal = Pattern.compile("^(0o)?[0-7]+$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(FieldHelper helper, IsOctal annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            return this.octal.matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsOctal annotation) {
        String message = String.format("'%s' precisa ser um numero octal válido", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
