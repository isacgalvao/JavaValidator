package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsPort;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

/**
 * Check if the string is a valid port number.
 */
public class IsPortValidator implements AnnotationValidatorInterface<IsPort> {
    @Override
    public boolean validate(FieldHelper helper, IsPort annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            try {
                final int port = Integer.parseInt(str);
                return port >= 0 && port <= 65535;
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsPort annotation) {
        String message = String.format("'%s' precisa ser uma porta válida", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
