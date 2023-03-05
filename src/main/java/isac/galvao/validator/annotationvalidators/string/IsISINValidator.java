package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsISIN;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class IsISINValidator implements AnnotationValidatorInterface<IsISIN> {

    private final Pattern isin = Pattern.compile("^[A-Z]{2}[0-9A-Z]{9}[0-9]$");

    @Override
    public boolean validate(FieldHelper helper, IsISIN annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            if (!isin.matcher(str).matches()) return false;

            boolean doubl = true;
            int sum = 0;
            // convert values
            for (int i = str.length() - 2; i >= 0; i--) {
                if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                    final int value = str.charAt(i) - 55;
                    final int lo = value % 10;
                    final int hi = value / 10;

                    // letters have two digits, so handle the low order
                    // and high order digits separately.
                    for (int digit : List.of(lo, hi)) {
                        if (doubl)
                            if (digit >= 5)
                                sum += 1 + ((digit - 5) * 2);
                            else
                                sum += digit * 2;
                        else
                            sum += digit;
                        doubl = !doubl;
                    }
                } else {
                    final int digit = str.charAt(i) - '0';
                    if (doubl)
                        if (digit >= 5)
                            sum += 1 + ((digit - 5) * 2);
                        else
                            sum += digit * 2;
                    else
                        sum += digit;
                    doubl = !doubl;
                }
            }

            final int check = (((sum + 9) / 10) * 10) - sum;

            return str.charAt(str.length() - 1) == (check + '0');
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsISIN annotation) {
        String message = String.format("'%s' precisa ser um ISIN (identificador de ações/títulos)", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
