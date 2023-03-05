package isac.galvao.validator.annotationvalidators.array;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.array.ArrayNotEmpty;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Collection;
import java.util.Objects;

public class ArrayNotEmptyValidator implements AnnotationValidatorInterface<ArrayNotEmpty> {

    @Override
    public boolean validate(FieldHelper helper, ArrayNotEmpty annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof Collection) return !((Collection<?>) valor).isEmpty();
        if (valor instanceof Object[]) return ((Object[]) valor).length != 0;
        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, ArrayNotEmpty annotation) {
        String message = String.format("'%s' não pode estar vazio", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
