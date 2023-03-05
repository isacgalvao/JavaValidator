package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsBase32;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsBase32Validator implements AnnotationValidatorInterface<IsBase32> {
    @Override
    public boolean validate(FieldHelper helper, IsBase32 annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            String base32 = "^[A-Z2-7]+=*$";
            String crockfordBase32 = "^[A-HJKMNP-TV-Z0-9]+$";

            if (annotation.crockford())
                return Pattern.compile(crockfordBase32).matcher(str).matches();

            return (str.length() % 8 == 0) && (Pattern.compile(base32).matcher(str).matches());
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsBase32 annotation) {
        String message = String.format("'%s' deve ser codificado em base32", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
