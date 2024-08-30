package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsISSN;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Checks if the string is an ISSN.
 */
public class IsISSNValidator implements AnnotationValidatorInterface<IsISSN> {

    private final String issn = "^\\d{4}-?\\d{3}[\\dX]$";

    @Override
    public boolean validate(FieldHelper helper, IsISSN annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String str) {

            final String testIssn = annotation.require_hyphen() ? issn.replace("?", "") : issn;
            final Pattern test = annotation.case_sensitive() ? Pattern.compile(testIssn) : Pattern.compile(testIssn, Pattern.CASE_INSENSITIVE);

            if (!test.matcher(str).matches()) return false;

            final String digits = str.replace("-", "").toUpperCase();
            int checksum = 0;
            for (int i = 0; i < digits.length(); i++) {
                final char digit = digits.charAt(i);
                checksum += (digit == 'X' ? 10 : Character.getNumericValue(digit)) * (8 - i);
            }
            return checksum % 11 == 0;
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsISSN annotation) {
        String message = String.format("'%s' precisa ser um ISSN", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
