package isac.galvao.validator.util;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexUtil {
    public static boolean isRegex(String exp) {
        try {
            Pattern.compile(exp);
            return true;
        } catch (PatternSyntaxException e) {
            return false;
        }
    }
}
