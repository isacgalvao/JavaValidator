package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsBase58;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsBase58Validator implements AnnotationValidatorInterface<IsBase58> {
    @Override
    public boolean validate(FieldHelper helper, IsBase58 annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String)
            // Accepted chars - 123456789ABCDEFGH JKLMN PQRSTUVWXYZabcdefghijk mnopqrstuvwxyz
            return Pattern.compile("^[A-HJ-NP-Za-km-z1-9]*$").matcher((String) valor).matches();

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsBase58 annotation) {
        String message = "'%s' deve ser codificado em base58".formatted(helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
