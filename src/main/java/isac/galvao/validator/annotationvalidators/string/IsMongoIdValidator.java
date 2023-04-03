package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsMongoId;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Checks if the string is a valid hex-encoded representation of a MongoDB ObjectId.
 */
public class IsMongoIdValidator implements AnnotationValidatorInterface<IsMongoId> {

    // TODO usar isHexadecimal
    private final Pattern hexadecimal = Pattern.compile("^(0x|0h)?[0-9A-F]+$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(FieldHelper helper, IsMongoId annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            return this.hexadecimal.matcher(str).matches() && str.length() == 24;
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsMongoId annotation) {
        String message = String.format("'%s' precisa ser um mongodb id", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
