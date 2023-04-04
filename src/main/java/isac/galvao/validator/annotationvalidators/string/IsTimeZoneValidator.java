package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsTimeZone;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;

/**
 * Checks if the string represents a valid IANA timezone
 * If the given value is not a valid IANA timezone, then it returns false.
 */
public class IsTimeZoneValidator implements AnnotationValidatorInterface<IsTimeZone> {
    @Override
    public boolean validate(FieldHelper helper, IsTimeZone annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            if (annotation.ignoreCase())
                return Set.of(TimeZone.getAvailableIDs()).stream().anyMatch(e -> e.equalsIgnoreCase(str));
            return Set.of(TimeZone.getAvailableIDs()).contains(str);
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsTimeZone annotation) {
        String message = String.format("'%s' pecisa ser um time-zone válido", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
