package isac.galvao.validator.validators.common;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.common.IsNotIn;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Arrays;
import java.util.Objects;

public class IsNotInValidator implements AnnotationValidatorInterface<IsNotIn> {
    @Override
    public boolean validate(FieldHelper helper, IsNotIn annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String)
            return !Arrays.asList(annotation.value()).contains((String) valor);
        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsNotIn annotation) {
        String message = String.format("'%s' não pode ser um dos seguintes valores: %s", helper.getAttributeName(), Arrays.toString(annotation.value()));
        return new ValidationError(helper, message, annotation);
    }
}
