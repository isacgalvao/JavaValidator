package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsIBAN;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsIBANValidator implements AnnotationValidatorInterface<IsIBAN> {

    /**
     * List of country codes with
     * corresponding IBAN regular expression
     * <p>
     * Reference: <a href=
     * "https://en.wikipedia.org/wiki/International_Bank_Account_Number">wiki</a>
     */
    private final Map<String, Pattern> ibanRegexThroughCountryCode;

    {
        ibanRegexThroughCountryCode = new HashMap<>();
        ibanRegexThroughCountryCode.put("AD", Pattern.compile("^(AD[0-9]{2})\\d{8}[A-Z0-9]{12}$"));
        ibanRegexThroughCountryCode.put("AE", Pattern.compile("^(AE[0-9]{2})\\d{3}\\d{16}$"));
        ibanRegexThroughCountryCode.put("AL", Pattern.compile("^(AL[0-9]{2})\\d{8}[A-Z0-9]{16}$"));
        ibanRegexThroughCountryCode.put("AT", Pattern.compile("^(AT[0-9]{2})\\d{16}$"));
        ibanRegexThroughCountryCode.put("AZ", Pattern.compile("^(AZ[0-9]{2})[A-Z0-9]{4}\\d{20}$"));
        ibanRegexThroughCountryCode.put("BA", Pattern.compile("^(BA[0-9]{2})\\d{16}$"));
        ibanRegexThroughCountryCode.put("BE", Pattern.compile("^(BE[0-9]{2})\\d{12}$"));
        ibanRegexThroughCountryCode.put("BG", Pattern.compile("^(BG[0-9]{2})[A-Z]{4}\\d{6}[A-Z0-9]{8}$"));
        ibanRegexThroughCountryCode.put("BH", Pattern.compile("^(BH[0-9]{2})[A-Z]{4}[A-Z0-9]{14}$"));
        ibanRegexThroughCountryCode.put("BR", Pattern.compile("^(BR[0-9]{2})\\d{23}[A-Z]{1}[A-Z0-9]{1}$"));
        ibanRegexThroughCountryCode.put("BY", Pattern.compile("^(BY[0-9]{2})[A-Z0-9]{4}\\d{20}$"));
        ibanRegexThroughCountryCode.put("CH", Pattern.compile("^(CH[0-9]{2})\\d{5}[A-Z0-9]{12}$"));
        ibanRegexThroughCountryCode.put("CR", Pattern.compile("^(CR[0-9]{2})\\d{18}$"));
        ibanRegexThroughCountryCode.put("CY", Pattern.compile("^(CY[0-9]{2})\\d{8}[A-Z0-9]{16}$"));
        ibanRegexThroughCountryCode.put("CZ", Pattern.compile("^(CZ[0-9]{2})\\d{20}$"));
        ibanRegexThroughCountryCode.put("DE", Pattern.compile("^(DE[0-9]{2})\\d{18}$"));
        ibanRegexThroughCountryCode.put("DK", Pattern.compile("^(DK[0-9]{2})\\d{14}$"));
        ibanRegexThroughCountryCode.put("DO", Pattern.compile("^(DO[0-9]{2})[A-Z]{4}\\d{20}$"));
        ibanRegexThroughCountryCode.put("EE", Pattern.compile("^(EE[0-9]{2})\\d{16}$"));
        ibanRegexThroughCountryCode.put("EG", Pattern.compile("^(EG[0-9]{2})\\d{25}$"));
        ibanRegexThroughCountryCode.put("ES", Pattern.compile("^(ES[0-9]{2})\\d{20}$"));
        ibanRegexThroughCountryCode.put("FI", Pattern.compile("^(FI[0-9]{2})\\d{14}$"));
        ibanRegexThroughCountryCode.put("FO", Pattern.compile("^(FO[0-9]{2})\\d{14}$"));
        ibanRegexThroughCountryCode.put("FR", Pattern.compile("^(FR[0-9]{2})\\d{10}[A-Z0-9]{11}\\d{2}$"));
        ibanRegexThroughCountryCode.put("GB", Pattern.compile("^(GB[0-9]{2})[A-Z]{4}\\d{14}$"));
        ibanRegexThroughCountryCode.put("GE", Pattern.compile("^(GE[0-9]{2})[A-Z0-9]{2}\\d{16}$"));
        ibanRegexThroughCountryCode.put("GI", Pattern.compile("^(GI[0-9]{2})[A-Z]{4}[A-Z0-9]{15}$"));
        ibanRegexThroughCountryCode.put("GL", Pattern.compile("^(GL[0-9]{2})\\d{14}$"));
        ibanRegexThroughCountryCode.put("GR", Pattern.compile("^(GR[0-9]{2})\\d{7}[A-Z0-9]{16}$"));
        ibanRegexThroughCountryCode.put("GT", Pattern.compile("^(GT[0-9]{2})[A-Z0-9]{4}[A-Z0-9]{20}$"));
        ibanRegexThroughCountryCode.put("HR", Pattern.compile("^(HR[0-9]{2})\\d{17}$"));
        ibanRegexThroughCountryCode.put("HU", Pattern.compile("^(HU[0-9]{2})\\d{24}$"));
        ibanRegexThroughCountryCode.put("IE", Pattern.compile("^(IE[0-9]{2})[A-Z0-9]{4}\\d{14}$"));
        ibanRegexThroughCountryCode.put("IL", Pattern.compile("^(IL[0-9]{2})\\d{19}$"));
        ibanRegexThroughCountryCode.put("IQ", Pattern.compile("^(IQ[0-9]{2})[A-Z]{4}\\d{15}$"));
        ibanRegexThroughCountryCode.put("IR", Pattern.compile("^(IR[0-9]{2})0\\d{2}0\\d{18}$"));
        ibanRegexThroughCountryCode.put("IS", Pattern.compile("^(IS[0-9]{2})\\d{22}$"));
        ibanRegexThroughCountryCode.put("IT", Pattern.compile("^(IT[0-9]{2})[A-Z]{1}\\d{10}[A-Z0-9]{12}$"));
        ibanRegexThroughCountryCode.put("JO", Pattern.compile("^(JO[0-9]{2})[A-Z]{4}\\d{22}$"));
        ibanRegexThroughCountryCode.put("KW", Pattern.compile("^(KW[0-9]{2})[A-Z]{4}[A-Z0-9]{22}$"));
        ibanRegexThroughCountryCode.put("KZ", Pattern.compile("^(KZ[0-9]{2})\\d{3}[A-Z0-9]{13}$"));
        ibanRegexThroughCountryCode.put("LB", Pattern.compile("^(LB[0-9]{2})\\d{4}[A-Z0-9]{20}$"));
        ibanRegexThroughCountryCode.put("LC", Pattern.compile("^(LC[0-9]{2})[A-Z]{4}[A-Z0-9]{24}$"));
        ibanRegexThroughCountryCode.put("LI", Pattern.compile("^(LI[0-9]{2})\\d{5}[A-Z0-9]{12}$"));
        ibanRegexThroughCountryCode.put("LT", Pattern.compile("^(LT[0-9]{2})\\d{16}$"));
        ibanRegexThroughCountryCode.put("LU", Pattern.compile("^(LU[0-9]{2})\\d{3}[A-Z0-9]{13}$"));
        ibanRegexThroughCountryCode.put("LV", Pattern.compile("^(LV[0-9]{2})[A-Z]{4}[A-Z0-9]{13}$"));
        ibanRegexThroughCountryCode.put("MC", Pattern.compile("^(MC[0-9]{2})\\d{10}[A-Z0-9]{11}\\d{2}$"));
        ibanRegexThroughCountryCode.put("MD", Pattern.compile("^(MD[0-9]{2})[A-Z0-9]{20}$"));
        ibanRegexThroughCountryCode.put("ME", Pattern.compile("^(ME[0-9]{2})\\d{18}$"));
        ibanRegexThroughCountryCode.put("MK", Pattern.compile("^(MK[0-9]{2})\\d{3}[A-Z0-9]{10}\\d{2}$"));
        ibanRegexThroughCountryCode.put("MR", Pattern.compile("^(MR[0-9]{2})\\d{23}$"));
        ibanRegexThroughCountryCode.put("MT", Pattern.compile("^(MT[0-9]{2})[A-Z]{4}\\d{5}[A-Z0-9]{18}$"));
        ibanRegexThroughCountryCode.put("MU", Pattern.compile("^(MU[0-9]{2})[A-Z]{4}\\d{19}[A-Z]{3}$"));
        ibanRegexThroughCountryCode.put("MZ", Pattern.compile("^(MZ[0-9]{2})\\d{21}$"));
        ibanRegexThroughCountryCode.put("NL", Pattern.compile("^(NL[0-9]{2})[A-Z]{4}\\d{10}$"));
        ibanRegexThroughCountryCode.put("NO", Pattern.compile("^(NO[0-9]{2})\\d{11}$"));
        ibanRegexThroughCountryCode.put("PK", Pattern.compile("^(PK[0-9]{2})[A-Z0-9]{4}\\d{16}$"));
        ibanRegexThroughCountryCode.put("PL", Pattern.compile("^(PL[0-9]{2})\\d{24}$"));
        ibanRegexThroughCountryCode.put("PS", Pattern.compile("^(PS[0-9]{2})[A-Z0-9]{4}\\d{21}$"));
        ibanRegexThroughCountryCode.put("PT", Pattern.compile("^(PT[0-9]{2})\\d{21}$"));
        ibanRegexThroughCountryCode.put("QA", Pattern.compile("^(QA[0-9]{2})[A-Z]{4}[A-Z0-9]{21}$"));
        ibanRegexThroughCountryCode.put("RO", Pattern.compile("^(RO[0-9]{2})[A-Z]{4}[A-Z0-9]{16}$"));
        ibanRegexThroughCountryCode.put("RS", Pattern.compile("^(RS[0-9]{2})\\d{18}$"));
        ibanRegexThroughCountryCode.put("SA", Pattern.compile("^(SA[0-9]{2})\\d{2}[A-Z0-9]{18}$"));
        ibanRegexThroughCountryCode.put("SC", Pattern.compile("^(SC[0-9]{2})[A-Z]{4}\\d{20}[A-Z]{3}$"));
        ibanRegexThroughCountryCode.put("SE", Pattern.compile("^(SE[0-9]{2})\\d{20}$"));
        ibanRegexThroughCountryCode.put("SI", Pattern.compile("^(SI[0-9]{2})\\d{15}$"));
        ibanRegexThroughCountryCode.put("SK", Pattern.compile("^(SK[0-9]{2})\\d{20}$"));
        ibanRegexThroughCountryCode.put("SM", Pattern.compile("^(SM[0-9]{2})[A-Z]{1}\\d{10}[A-Z0-9]{12}$"));
        ibanRegexThroughCountryCode.put("SV", Pattern.compile("^(SV[0-9]{2})[A-Z0-9]{4}\\d{20}$"));
        ibanRegexThroughCountryCode.put("TL", Pattern.compile("^(TL[0-9]{2})\\d{19}$"));
        ibanRegexThroughCountryCode.put("TN", Pattern.compile("^(TN[0-9]{2})\\d{20}$"));
        ibanRegexThroughCountryCode.put("TR", Pattern.compile("^(TR[0-9]{2})\\d{5}[A-Z0-9]{17}$"));
        ibanRegexThroughCountryCode.put("UA", Pattern.compile("^(UA[0-9]{2})\\d{6}[A-Z0-9]{19}$"));
        ibanRegexThroughCountryCode.put("VA", Pattern.compile("^(VA[0-9]{2})\\d{18}$"));
        ibanRegexThroughCountryCode.put("VG", Pattern.compile("^(VG[0-9]{2})[A-Z0-9]{4}\\d{16}$"));
        ibanRegexThroughCountryCode.put("XK", Pattern.compile("^(XK[0-9]{2})\\d{16}$"));
    }

    @Override
    public boolean validate(FieldHelper helper, IsIBAN annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor))
            return false;

        if (valor instanceof String) {
            String str = (String) valor;
            return hasValidIbanFormat(str) && hasValidIbanChecksum(str);
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsIBAN annotation) {
        String message = String.format("'%s' precisa ser um IBAN (International Bank Account Number)",
                helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }

    /**
     * Check whether string has correct universal IBAN format
     * The IBAN consists of up to 34 alphanumeric characters, as follows:
     * <p>
     * Country Code using ISO 3166-1 alpha-2, two letters
     * check digits, two digits and
     * Basic Bank Account Number (BBAN), up to 30 alphanumeric characters.
     * <p>
     * NOTE: Permitted IBAN characters are: digits [0-9] and the 26 latin alphabetic
     * [A-Z]
     */
    private boolean hasValidIbanFormat(String str) {
        // Strip white spaces and hyphens
        final String strippedStr = Pattern.compile("[\\s\\-]+", Pattern.CASE_INSENSITIVE)
                .matcher(str)
                .replaceAll("")
                .toUpperCase();
        final String isoCountryCode = strippedStr.substring(0, 2).toUpperCase();

        return ibanRegexThroughCountryCode.containsKey(isoCountryCode) &&
                ibanRegexThroughCountryCode.get(isoCountryCode).matcher(strippedStr).matches();
    }

    /**
     * Check whether string has valid IBAN Checksum
     * by performing basic mod-97 operation and
     * the remainder should equal 1
     * -- Start by rearranging the IBAN by moving the four initial characters to the
     * end of the string
     * -- Replace each letter in the string with two digits, A -> 10, B = 11, Z = 35
     * -- Interpret the string as a decimal integer and
     * -- compute the remainder on division by 97 (mod 97)
     * Reference: <a href=
     * "https://en.wikipedia.org/wiki/International_Bank_Account_Number">wiki</a>
     */
    private boolean hasValidIbanChecksum(String str) {
        final String strippedStr = Pattern.compile("[^A-Z0-9]+", Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("");
        final String rearranged = strippedStr.substring(4) + strippedStr.substring(0, 4);
        final String alphaCapsReplacedWithDigits = replaceAll(rearranged);
        final int remainder = Integer.parseInt(match(alphaCapsReplacedWithDigits)
                .stream()
                .reduce("", (acc, value) -> String.valueOf(Integer.parseInt(acc + value) % 97)));
        return remainder == 1;
    }

    private String replaceAll(String str) {
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile("[A-Z]").matcher(str);

        while (m.find())
            matches.add(m.group());

        for (String match : matches)
            str = str.replace(match, String.valueOf(((int) match.charAt(0)) - 55));
        return str;
    }

    private List<String> match(String str) {
        List<String> matches = new ArrayList<>();
        Matcher m = Pattern.compile("\\d{1,7}").matcher(str);

        while (m.find())
            matches.add(m.group());

        return matches;
    }
}
