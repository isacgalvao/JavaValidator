package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.Contains;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

public class ContainsValidator implements AnnotationValidatorInterface<Contains> {
    @Override
    public boolean validate(FieldHelper helper, Contains annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            if (annotation.ignoreCase())
                return countMatches(((String) valor).toLowerCase(), annotation.value().toLowerCase()) >= annotation.minOccurrences();
            return countMatches((String) valor, annotation.value()) >= annotation.minOccurrences();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, Contains annotation) {
        String message = String.format("'%s' precisa conter '%s' (string)", helper.getAttributeName(), annotation.value());
        return new ValidationError(helper, message, annotation);
    }

    private int countMatches(String str, String elem) {
        int count = 0;
        if(!str.isEmpty() && !elem.isEmpty()) {
            for (int i = 0; (i = str.indexOf(elem, i)) != -1; i += elem.length()) {
                count++;
            }
        }
        return count;
    }
}
