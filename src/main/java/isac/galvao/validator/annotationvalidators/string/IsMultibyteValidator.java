package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsMultibyte;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Checks if the string contains one or more multibyte chars.
 */
public class IsMultibyteValidator implements AnnotationValidatorInterface<IsMultibyte> {

    private final Pattern multibyte = Pattern.compile("[^\\x00-\\x7F]");
    @Override
    public boolean validate(FieldHelper helper, IsMultibyte annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            return this.multibyte.matcher(str).find();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsMultibyte annotation) {
        String message = String.format("'%s' precisa contar um ou mais multibyte caracteres", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
