package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsJWT;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Checks if the string is valid JWT token.
 */
public class IsJWTValidator implements AnnotationValidatorInterface<IsJWT> {
    @Override
    public boolean validate(FieldHelper helper, IsJWT annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            final String[] dotSplit = str.split("\\.");
            final int len = dotSplit.length;

            if (len > 5 || len < 2) return false;

            return Arrays.stream(dotSplit).allMatch(this::isBase64);
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsJWT annotation) {
        String message = String.format("'%s' precisa ser uma string jwt", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }

    // TODO substituir pela classe IsBase64Validator
    private boolean isBase64(String str) {
        final String urlSafeBase64 = "^[A-Z0-9_\\-]*$";
        return Pattern.compile(urlSafeBase64, Pattern.CASE_INSENSITIVE).matcher(str).matches();
    }
}
