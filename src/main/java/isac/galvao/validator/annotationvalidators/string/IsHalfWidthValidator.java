package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsHalfWidth;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsHalfWidthValidator implements AnnotationValidatorInterface<IsHalfWidth> {

    private final Pattern halfWidth = Pattern.compile("[\\u0020-\\u007E\\uFF61-\\uFF9F\\uFFA0-\\uFFDC\\uFFE8-\\uFFEE0-9a-zA-Z]");

    @Override
    public boolean validate(FieldHelper helper, IsHalfWidth annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) return this.halfWidth.matcher((String) valor).find();

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsHalfWidth annotation) {
        String message = String.format("'%s' precisa conter caracteres half-width", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
