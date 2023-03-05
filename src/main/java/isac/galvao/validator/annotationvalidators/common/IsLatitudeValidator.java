package isac.galvao.validator.annotationvalidators.common;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.common.IsLatitude;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

public class IsLatitudeValidator implements AnnotationValidatorInterface<IsLatitude> {
    @Override
    public boolean validate(FieldHelper helper, IsLatitude annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String || valor instanceof  Number)
            return IsLatLongValidator.isLatLng(String.format("%s,0", valor), false);

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsLatitude annotation) {
        String message = String.format("'%s' precisa ser uma string ou number latitude válido", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
