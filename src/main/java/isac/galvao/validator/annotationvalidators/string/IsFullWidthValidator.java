package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsFullWidth;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsFullWidthValidator implements AnnotationValidatorInterface<IsFullWidth> {

    private final Pattern fullWidth = Pattern.compile("[^\\u0020-\\u007E\\uFF61-\\uFF9F\\uFFA0-\\uFFDC\\uFFE8-\\uFFEE0-9a-zA-Z]");

    @Override
    public boolean validate(FieldHelper helper, IsFullWidth annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) return this.fullWidth.matcher((String) valor).find();

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsFullWidth annotation) {
        String message = String.format("'%s' precisa conter ter caracteres full-width", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
