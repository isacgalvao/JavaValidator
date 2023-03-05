package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsISO8601;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsISO8601Validator implements AnnotationValidatorInterface<IsISO8601> {

    /**
     * get <a href=
     * "https://www.myintervals.com/blog/2009/05/20/iso-8601-date-validation-that-doesnt-suck/">from</a>
     */
    private final Pattern iso8601 = Pattern.compile(
            "^([\\+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-3])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T\\s]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24:?00)([\\.,]\\d+(?!:))?)?(\\17[0-5]\\d([\\.,]\\d+)?)?([zZ]|([\\+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?$");

    // same as above, except with a strict "T" separator between date and time
    private final Pattern iso8601StrictSeparator = Pattern.compile(
            "^([\\+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-3])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24:?00)([\\.,]\\d+(?!:))?)?(\\17[0-5]\\d([\\.,]\\d+)?)?([zZ]|([\\+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?$");

    @Override
    public boolean validate(FieldHelper helper, IsISO8601 annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor))
            return false;

        if (valor instanceof String) {
            String str = (String) valor;
            final boolean check = annotation.strict() ? iso8601StrictSeparator.matcher(str).matches()
                    : iso8601.matcher(str).matches();
            if (check && annotation.strict())
                return isValidDate(str);
            return check;
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsISO8601 annotation) {
        String message = String.format("'%s' deve ser uma string de data ISO 8601 válida", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }

    private boolean isValidDate(String str) {
        // str must have passed the ISO8601 check
        // this check is meant to catch invalid dates
        // like 2009-02-31
        // first check for ordinal dates
        final String ordinalMatch = Pattern.compile("^(\\d{4})-?(\\d{3})([ T]{1}\\.*|$)").matcher(str).group();
        if (!ordinalMatch.isBlank()) {
            final int oYear = Integer.parseInt(String.valueOf(ordinalMatch.charAt(1)));
            final int oDay = Integer.parseInt(String.valueOf(ordinalMatch.charAt(2)));

            // if is lep year
            if ((oYear % 4 == 0 && oYear % 100 != 0) || oYear % 400 == 0)
                return oDay <= 366;
            return oDay <= 365;
        }

        final int[] match = match(str);
        final int year = match[1];
        final int month = match[2];
        final int day = match[3];

        final String monthString = month ? "0%s".formatted(month).substring(0, -2) : month;
    }

    private int[] match(String str) {
        final Matcher matcher = Pattern.compile("(\\d{4})-?(\\d{0,2})-?(\\d*)").matcher(str);
        int[] temp = new int[matcher.groupCount()];

        for (int i = 0; i < matcher.groupCount(); i++)
            temp[i] = Integer.parseInt(matcher.group());

        return temp;
    }
}
