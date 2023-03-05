package isac.galvao.validator.annotationvalidators.common;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.common.IsNotNull;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

public class IsNotNullValidator implements AnnotationValidatorInterface<IsNotNull> {
    @Override
    public boolean validate(FieldHelper helper, IsNotNull annotation) {
        return Objects.nonNull(helper.getValue());
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsNotNull annotation) {
        String message = String.format("'%s' não pode ser nulo", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
