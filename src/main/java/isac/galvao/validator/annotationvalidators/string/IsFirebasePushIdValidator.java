package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsFirebasePushId;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsFirebasePushIdValidator implements AnnotationValidatorInterface<IsFirebasePushId> {

    private final Pattern webSafeRegex = Pattern.compile("^[a-zA-Z0-9_-]*$");

    @Override
    public boolean validate(FieldHelper helper, IsFirebasePushId annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String)
            return ((String) valor).length() == 20 && webSafeRegex.matcher((String) valor).matches();

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsFirebasePushId annotation) {
        String message = String.format("'%s' precisa ser um Firebase Push Id", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
