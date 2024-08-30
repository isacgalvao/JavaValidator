package isac.galvao.validator.validators.common;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.common.Length;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class LengthValidator implements AnnotationValidatorInterface<Length> {
    @Override
    public boolean validate(FieldHelper helper, Length l) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String)
            return ((String) valor).length() <= l.max() && ((String) valor).length() >= l.min();

        if (valor instanceof Collection)
            return ((Collection<?>) valor).size() <= l.max() && ((Collection<?>) valor).size() >= l.min();

        if (valor instanceof Object[])
            return ((Object[]) valor).length <= l.max() && ((Object[]) valor).length >= l.min();

        if (valor instanceof Map)
            return ((Map<?, ?>) valor).size() <= l.max() && ((Map<?, ?>) valor).size() >= l.min();
        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, Length l) {
        Object valor = helper.getValue();
        String message = null;
        if (valor instanceof String)
            message = String.format("'%s' precisa ter entre %s e %s caracteres", helper.getAttributeName(), l.min(), l.max());

        if (valor instanceof Collection || valor instanceof Object[])
            message = String.format("'%s' precisa ter entre %s e %s elementos", helper.getAttributeName(), l.min(), l.max());

        return new ValidationError(helper, message, l);
    }

}
