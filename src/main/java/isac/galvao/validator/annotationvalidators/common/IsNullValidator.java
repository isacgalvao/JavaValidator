package isac.galvao.validator.annotationvalidators.common;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.common.IsNull;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

public class IsNullValidator implements AnnotationValidatorInterface<IsNull> {
    @Override
    public boolean validate(FieldHelper helper, IsNull annotation) {
        return Objects.isNull(helper.getValue());
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsNull annotation) {
        String message = String.format("'%s' precisa ser nulo", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
