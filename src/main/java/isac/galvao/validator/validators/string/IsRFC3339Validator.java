package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsRFC3339;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is a valid RFC 3339 date.
 * Based on <a href="https://tools.ietf.org/html/rfc3339#section-5.6">RFC3339</a>
 */
public class IsRFC3339Validator implements AnnotationValidatorInterface<IsRFC3339> {

    private final String dateFullYear = "[0-9]{4}";
    private final String dateMonth = "(0[1-9]|1[0-2])";
    private final String dateMDay = "([12]\\d|0[1-9]|3[01])";

    private final String timeHour = "([01][0-9]|2[0-3])";
    private final String timeMinute = "[0-5][0-9]";
    private final String timeSecond = "([0-5][0-9]|60)";

    private final String timeSecFrac = "(\\.[0-9]+)?";
    private final String timeNumOffset = "[-+]%s:%s".formatted(timeHour, timeMinute);
    private final String timeOffset = "([zZ]|%s)".formatted(timeNumOffset);

    private final String partialTime = "%s:%s:%s%s".formatted(timeHour, timeMinute, timeSecond, timeSecFrac);

    private final String fullDate = "%s-%s-%s".formatted(dateFullYear, dateMonth, dateMDay);
    private final String fullTime = "%s%s".formatted(partialTime, timeOffset);

    private final Pattern rfc3339 = Pattern.compile("^%s[ tT]%s$".formatted(fullDate, fullTime));

    @Override
    public boolean validate(FieldHelper helper, IsRFC3339 annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            return this.rfc3339.matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsRFC3339 annotation) {
        String message = String.format("'%s' precisa ser uma data RFC 3339", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
