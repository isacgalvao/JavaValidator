package isac.galvao.validator.annotationvalidators.common;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.common.IsLatLongDMS;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

public class IsLatLongDMSValidator implements AnnotationValidatorInterface<IsLatLongDMS> {
    @Override
    public boolean validate(FieldHelper helper, IsLatLongDMS annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String)
            return IsLatLongValidator.isLatLng((String) valor, true);

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsLatLongDMS annotation) {
        String message = String.format("'%s' precisa precisar ser uma string latitude,longitude (DMS)", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
