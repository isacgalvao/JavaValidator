package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsBooleanString;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IsBooleanStringValidator implements AnnotationValidatorInterface<IsBooleanString> {
    @Override
    public boolean validate(FieldHelper helper, IsBooleanString annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            final String str = (String) valor;
            final List<String> strictBooleans = new ArrayList<>(Arrays.asList("true", "false", "1", "0"));
            final List<String> looseBooleans = new ArrayList<>(strictBooleans);
            looseBooleans.addAll(Arrays.asList("yes", "no"));

            if (annotation.loose())
                return looseBooleans.contains(str.toLowerCase());

            return strictBooleans.contains(str.toLowerCase());
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsBooleanString annotation) {
        String message = "'%s' deve ser uma string booleana".formatted(helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
