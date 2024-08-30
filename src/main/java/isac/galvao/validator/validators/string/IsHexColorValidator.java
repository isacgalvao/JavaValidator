package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsHexColor;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsHexColorValidator implements AnnotationValidatorInterface<IsHexColor> {

    private final Pattern hexcolor = Pattern.compile("^#?([0-9A-F]{3}|[0-9A-F]{4}|[0-9A-F]{6}|[0-9A-F]{8})$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(FieldHelper helper, IsHexColor annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) return this.hexcolor.matcher((String) valor).matches();

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsHexColor annotation) {
        String message = String.format("'%s' precisa ser uma cor hexadecimal", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
