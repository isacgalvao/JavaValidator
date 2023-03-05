package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsByteLength;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class IsByteLengthValidator implements AnnotationValidatorInterface<IsByteLength> {
    @Override
    public boolean validate(FieldHelper helper, IsByteLength annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            final byte[] stringBytes = str.getBytes(StandardCharsets.UTF_8);
            return stringBytes.length >= annotation.min() && stringBytes.length <= annotation.max();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsByteLength annotation) {
        String message = String.format("'%s' precisa ter entre %s e %s bytes de tamanho", helper.getAttributeName(), annotation.min(), annotation.max());
        return new ValidationError(helper, message, annotation);
    }
}
