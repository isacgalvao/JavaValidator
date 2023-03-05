package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsHexadecimal;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsHexadecimalValidator implements AnnotationValidatorInterface<IsHexadecimal> {

    private final Pattern hexadecimal = Pattern.compile("^(0x|0h)?[0-9A-F]+$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(FieldHelper helper, IsHexadecimal annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) return hexadecimal.matcher((String) valor).matches();

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsHexadecimal annotation) {
        String message = String.format("'%s' precisa ser um valor hexadecimal", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
