package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsSurrogatePair;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

/**
 * Checks if the string contains any surrogate pairs chars.
 */
public class IsSurrogatePairValidator implements AnnotationValidatorInterface<IsSurrogatePair> {

    @Override
    public boolean validate(FieldHelper helper, IsSurrogatePair annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            for (int i = 0; i < str.length(); i++)
                try {
                    if (Character.isSurrogatePair(str.charAt(i), str.charAt(i + 1)))
                        return true;
                } catch (Exception e) {
                    return false;
                }
            return false;
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsSurrogatePair annotation) {
        String message = String.format("'%s' precisa conter surrogate pairs chars", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
