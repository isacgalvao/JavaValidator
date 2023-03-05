package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsEAN;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * The most commonly used EAN standard is
 * the thirteen-digit EAN-13, while the
 * less commonly used 8-digit EAN-8 barcode was
 * introduced for use on small packages.<p>
 * Also, EAN/UCC-14 is used for Grouping of individual
 * trade items above unit level(Intermediate, Carton or Pallet).<p>
 * For more info about EAN-14 checkout: <a href="https://www.gtin.info/itf-14-barcodes/">ref</a><p>
 * EAN consists of:
 * GS1 prefix, manufacturer code, product code and check digit<p>
 * Reference: <a href="https://en.wikipedia.org/wiki/International_Article_Number">wiki</a><p>
 * Reference: <a href="https://www.gtin.info/">gtin</a>
 */
public class IsEANValidator implements AnnotationValidatorInterface<IsEAN> {
    /**
     * Define EAN Lengths; 8 for EAN-8; 13 for EAN-13; 14 for EAN-14
     * and Regular Expression for valid EANs (EAN-8, EAN-13, EAN-14),
     * with exact numeric matching of 8 or 13 or 14 digits [0-9]
     */
    private final int LENGTH_EAN_8 = 8;
    private final int LENGTH_EAN_14 = 14;
    private final Pattern validEanRegex = Pattern.compile("^(\\d{8}|\\d{13}|\\d{14})$");

    /**
     * Check if string is valid EAN:
     * Matches EAN-8/EAN-13/EAN-14 regex
     * Has valid check digit.
     */
    @Override
    public boolean validate(FieldHelper helper, IsEAN annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            final int actualCheckDigit = Integer.parseInt(str.substring(str.length()-1));
            return validEanRegex.matcher(str).matches() && actualCheckDigit == calculateCheckDigit(str);
        }
        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsEAN annotation) {
        String message = String.format("'%s' precisa ser um EAN (European Article Number)", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }

    /**
     * Get position weight given:
     * EAN length and digit index/position
     */
    private int getPositionWeightThroughLengthAndIndex(int length, int index) {
        if (length == LENGTH_EAN_8 || length == LENGTH_EAN_14)
            return (index % 2 == 0) ? 3 : 1;
        return (index % 2 == 0) ? 1 : 3;
    }

    /**
     * Calculate EAN Check Digit.
     * <p>
     * Reference: <a href="https://en.wikipedia.org/wiki/International_Article_Number#Calculation_of_checksum_digit">wiki</a>
     */
    private int calculateCheckDigit(String ean) {
        final String[] temp1 = ean
                .substring(0, ean.length() - 1)
                .split("");

        final List<Integer> temp2 = new ArrayList<>();
        for (int i = 0; i < temp1.length; i++)
            temp2.add(Integer.parseInt(temp1[i]) * getPositionWeightThroughLengthAndIndex(ean.length(), i));

        final Integer checksum = temp2.stream().reduce(0, Integer::sum);

        final int remainder = 10 - (checksum % 10);
        return remainder < 10 ? remainder : 0;
    }
}
