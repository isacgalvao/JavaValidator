package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsISBN;
import isac.galvao.validator.enums.ISBNVersion;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsISBNValidator implements AnnotationValidatorInterface<IsISBN> {

    private final Pattern possibleIsbn10 = Pattern.compile("^(?:[0-9]{9}X|[0-9]{10})$");
    private final Pattern possibleIsbn13 = Pattern.compile("^(?:[0-9]{13})$");
    private final int[] factor = {1, 3};

    @Override
    public boolean validate(FieldHelper helper, IsISBN annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            if (annotation.version() == ISBNVersion.ANY)
                return isISBN(str, ISBNVersion.V10) || isISBN(str, ISBNVersion.V13);
            return isISBN(str, annotation.version());
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsISBN annotation) {
        String message = String.format("'%s' precisa ser um ISBN", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }

    private boolean isISBN(String isbn, ISBNVersion version) {
        final String sanitizedIsbn = isbn.replaceAll("[\\s-]+", "");

        int checksum = 0;

        if (version == ISBNVersion.V10) {
            if (!possibleIsbn10.matcher(sanitizedIsbn).matches())
                return false;

            for (int i = 0; i < version.getSize() - 1; i++)
                checksum += (i+1) * Character.getNumericValue(sanitizedIsbn.charAt(i));

            if (sanitizedIsbn.charAt(9) == 'X') checksum += 10 * 10;
            else checksum += 10 * Character.getNumericValue(sanitizedIsbn.charAt(9));

            return (checksum % 11) == 0;
        } else if (version == ISBNVersion.V13) {
            if (!possibleIsbn13.matcher(sanitizedIsbn).matches())
                return false;

            for (int i = 0; i < version.getSize() - 1; i++)
                checksum += factor[i % 2] * Character.getNumericValue(sanitizedIsbn.charAt(i));

            return Character.getNumericValue(sanitizedIsbn.charAt(12)) - ((10 - (checksum % 10)) % 10) == 0;
        }

        return false;
    }
}
