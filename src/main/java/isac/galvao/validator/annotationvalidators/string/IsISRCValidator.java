package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsISRC;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is a ISRC.
 * <a href="https://isrc.ifpi.org/en/isrc-standard/structure">see more</a>
 */
public class IsISRCValidator implements AnnotationValidatorInterface<IsISRC> {

    private final Pattern isrc = Pattern.compile("^[A-Z]{2}[0-9A-Z]{3}\\d{2}\\d{5}$");
    @Override
    public boolean validate(FieldHelper helper, IsISRC annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            return this.isrc.matcher((String) valor).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsISRC annotation) {
        String message = String.format("'%s' precisa ser um ISRC", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
