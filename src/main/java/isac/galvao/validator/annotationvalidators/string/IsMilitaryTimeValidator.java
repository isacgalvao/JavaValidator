package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsMilitaryTime;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Checks if the string represents a time without a given timezone in the format HH:MM (military)
 * If the given value does not match the pattern HH:MM, then it returns false.
 */
public class IsMilitaryTimeValidator implements AnnotationValidatorInterface<IsMilitaryTime> {

    private final Pattern militaryTimeRegex = Pattern.compile("^([01]\\d|2[0-3]):?([0-5]\\d)$");

    @Override
    public boolean validate(FieldHelper helper, IsMilitaryTime annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            return this.militaryTimeRegex.matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsMilitaryTime annotation) {
        String message = String.format("'%s' precisa ser uma representação válida do tempo militar no formato HH:MM", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
