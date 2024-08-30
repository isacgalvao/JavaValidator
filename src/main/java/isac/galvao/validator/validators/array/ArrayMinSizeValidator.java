package isac.galvao.validator.validators.array;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.array.ArrayMinSize;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Collection;
import java.util.Objects;

public class ArrayMinSizeValidator implements AnnotationValidatorInterface<ArrayMinSize> {
    @Override
    public boolean validate(FieldHelper helper, ArrayMinSize a) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof Collection) return ((Collection<?>) valor).size() >= a.value();
        if (valor instanceof Object[]) return ((Object[]) valor).length >= a.value();
        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, ArrayMinSize a) {
        String message = String.format("'%s' precisa ter no mínimo %s elementos", helper.getAttributeName(), a.value());
        return new ValidationError(helper, message, a);
    }
}
