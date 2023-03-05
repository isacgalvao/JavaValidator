package isac.galvao.validator.annotationvalidators.array;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.array.ArrayMaxSize;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Collection;
import java.util.Objects;

public class ArrayMaxSizeValidator implements AnnotationValidatorInterface<ArrayMaxSize> {
    @Override
    public boolean validate(FieldHelper helper, ArrayMaxSize a) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof Collection) return ((Collection<?>) valor).size() <= a.value();
        if (valor instanceof Object[]) return ((Object[]) valor).length <= a.value();
        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, ArrayMaxSize a) {
        String message = String.format("'%s' precisa ter no máximo %s elementos", helper.getAttributeName(), a.value());
        return new ValidationError(helper, message, a);
    }
}
