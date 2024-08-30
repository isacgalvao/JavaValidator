package isac.galvao.validator.validators.array;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.array.ArrayUnique;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.*;

public class ArrayUniqueValidator implements AnnotationValidatorInterface<ArrayUnique> {
    @Override
    public boolean validate(FieldHelper helper, ArrayUnique annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof Object[]) {
            Set<?> s = new HashSet<>(List.of((Object[]) valor));
            return s.size() == ((Object[]) valor).length;
        }

        if (valor instanceof Collection) {
            Set<?> s = new HashSet<>((Collection<?>) valor);
            return s.size() == (((Collection<?>) valor).size());
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, ArrayUnique annotation) {
        String message = String.format("'%s' não pode conter valores duplicados", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
