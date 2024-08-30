package isac.galvao.validator.validators.common;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.common.IsNotEmpty;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class IsNotEmptyValidator implements AnnotationValidatorInterface<IsNotEmpty> {
    @Override
    public boolean validate(FieldHelper helper, IsNotEmpty annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String)
            return !((String) valor).isBlank();

        if (valor instanceof Collection)
            return !((Collection<?>) valor).isEmpty();

        if (valor instanceof Object[])
            return ((Object[]) valor).length != 0;

        if (valor instanceof Map)
            return !((Map<?, ?>) valor).isEmpty();

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsNotEmpty annotation) {
        String message = String.format("'%s' não pode estar vazio", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
