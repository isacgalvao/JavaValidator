package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsStrongPassword;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Checks if the string is a strong password.
 */
public class IsStrongPasswordValidator implements AnnotationValidatorInterface<IsStrongPassword> {

    private final Pattern upperCaseRegex = Pattern.compile("^[A-Z]$");
    private final Pattern lowerCaseRegex = Pattern.compile("^[a-z]$");
    private final Pattern numberRegex = Pattern.compile("^[0-9]$");
    private final Pattern symbolRegex = Pattern.compile("^[-#!$@£%^&*()_+|~=`{}\\[\\]:\";'<>?,.\\/ ]$");

    @Override
    public boolean validate(FieldHelper helper, IsStrongPassword annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            final PasswordAnalysis analysis = analyzePassword(str);
            return analysis.getLength() >= annotation.minLength()
                    && analysis.getLowercaseCount() >= annotation.minLowercase()
                    && analysis.getUppercaseCount() >= annotation.minUppercase()
                    && analysis.getNumberCount() >= annotation.minNumbers()
                    && analysis.getSymbolCount() >= annotation.minSymbols();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsStrongPassword annotation) {
        String message = String.format("'%s' não é uma senha forte", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }

    /* Counts number of occurrences of each char in a string */
    private Map<String, Integer> countChars(String str) {
        Map<String, Integer> result = new HashMap<>();
        for (String s : str.split("")) {
            result.putIfAbsent(s, Objects.isNull(result.get(s)) ? 1 : result.get(s) + 1);
        }
        return result;
    }

    /* Return information about a password */
    private PasswordAnalysis analyzePassword(String password) {
        final Map<String, Integer> charMap = this.countChars(password);
        final PasswordAnalysis analysis = new PasswordAnalysis(password.length(), charMap.size(), 0, 0, 0, 0);
        charMap.keySet().forEach((e) -> {
            if (this.upperCaseRegex.matcher(e).matches())
                analysis.setUppercaseCount(charMap.get(e));

            else if (this.lowerCaseRegex.matcher(e).matches())
                analysis.setLowercaseCount(charMap.get(e));

            else if (this.numberRegex.matcher(e).matches())
                analysis.setNumberCount(charMap.get(e));

            else if (this.symbolRegex.matcher(e).matches())
                analysis.setSymbolCount(charMap.get(e));
        });
        return analysis;
    }

    private static class PasswordAnalysis {
        private int length;
        private int uniqueChars;
        private int uppercaseCount;
        private int lowercaseCount;
        private int numberCount;
        private int symbolCount;

        protected PasswordAnalysis(int length, int uniqueChars, int uppercaseCount, int lowercaseCount, int numberCount, int symbolCount) {
            this.length = length;
            this.uniqueChars = uniqueChars;
            this.uppercaseCount = uppercaseCount;
            this.lowercaseCount = lowercaseCount;
            this.numberCount = numberCount;
            this.symbolCount = symbolCount;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getUniqueChars() {
            return uniqueChars;
        }

        public void setUniqueChars(int uniqueChars) {
            this.uniqueChars = uniqueChars;
        }

        public int getUppercaseCount() {
            return uppercaseCount;
        }

        public void setUppercaseCount(int uppercaseCount) {
            this.uppercaseCount = uppercaseCount;
        }

        public int getLowercaseCount() {
            return lowercaseCount;
        }

        public void setLowercaseCount(int lowercaseCount) {
            this.lowercaseCount = lowercaseCount;
        }

        public int getNumberCount() {
            return numberCount;
        }

        public void setNumberCount(int numberCount) {
            this.numberCount = numberCount;
        }

        public int getSymbolCount() {
            return symbolCount;
        }

        public void setSymbolCount(int symbolCount) {
            this.symbolCount = symbolCount;
        }
    }
}
