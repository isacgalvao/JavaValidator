package isac.galvao.validator.validators.common;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.common.IsIn;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Arrays;
import java.util.Objects;

public class IsInValidator implements AnnotationValidatorInterface<IsIn> {
    @Override
    public boolean validate(FieldHelper helper, IsIn annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String)
            return Arrays.asList(annotation.value()).contains((String) valor);

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsIn annotation) {
        String message = String.format("'%s' precisa ser um dos seguintes valores: %s", helper.getAttributeName(), Arrays.toString(annotation.value()));
        return new ValidationError(helper, message, annotation);
    }
}
