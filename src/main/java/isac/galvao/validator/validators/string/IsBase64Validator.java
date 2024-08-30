package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsBase64;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsBase64Validator implements AnnotationValidatorInterface<IsBase64> {
    @Override
    public boolean validate(FieldHelper helper, IsBase64 annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            final String str = (String) valor;
            final int len = ((String) valor).length();
            final String notBase64 = "[^A-Z0-9+/=]";
            final String urlSafeBase64 = "^[A-Z0-9_\\-]*$";

            if (annotation.urlSafe())
                return Pattern.compile(urlSafeBase64, Pattern.CASE_INSENSITIVE).matcher(str).matches();

            if (len % 4 != 0 || Pattern.compile(notBase64, Pattern.CASE_INSENSITIVE).matcher(str).matches())
                return false;

            final int firstPaddingChar = str.indexOf("=");
            return firstPaddingChar == -1 || firstPaddingChar == len - 1 || (firstPaddingChar == len - 2 && str.charAt(len - 1) == '=');
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsBase64 annotation) {
        String message = "'%s' deve ser codificado em base64".formatted(helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
