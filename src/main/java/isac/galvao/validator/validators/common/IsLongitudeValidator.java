package isac.galvao.validator.validators.common;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.common.IsLongitude;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

public class IsLongitudeValidator implements AnnotationValidatorInterface<IsLongitude> {
    @Override
    public boolean validate(FieldHelper helper, IsLongitude annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String || valor instanceof Number)
            return IsLatLongValidator.isLatLng(String.format("0,%s", valor),false);

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsLongitude annotation) {
        String message = String.format("'%s' precisa ser uma string ou number longitude válido", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
