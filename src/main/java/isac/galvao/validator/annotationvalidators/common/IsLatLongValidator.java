package isac.galvao.validator.annotationvalidators.common;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.common.IsLatLong;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsLatLongValidator implements AnnotationValidatorInterface<IsLatLong> {
    public static boolean isLatLng(String valor, boolean checkDMS) {
        Pattern lat = Pattern.compile("^\\(?[+-]?(90(\\.0+)?|[1-8]?\\d(\\.\\d+)?)$");
        Pattern lng = Pattern.compile("^\\s?[+-]?(180(\\.0+)?|1[0-7]\\d(\\.\\d+)?|\\d{1,2}(\\.\\d+)?)\\)?$");

        Pattern latDMS = Pattern.compile("^(([1-8]?\\d)\\D+([1-5]?\\d|60)\\D+([1-5]?\\d|60)(\\.\\d+)?|90\\D+0\\D+0)\\D+[NSns]?$", Pattern.CASE_INSENSITIVE);
        Pattern lngDMS = Pattern.compile("^\\s*([1-7]?\\d{1,2}\\D+([1-5]?\\d|60)\\D+([1-5]?\\d|60)(\\.\\d+)?|180\\D+0\\D+0)\\D+[EWew]?$", Pattern.CASE_INSENSITIVE);

        if (!valor.contains(",")) return false;
        String[] pair = valor.split(",");
        if ((pair[0].startsWith("(") && !pair[1].endsWith(")")) || (pair[1].endsWith(")") && !pair[0].startsWith("(")))
            return false;

        if (checkDMS)
            return latDMS.matcher(pair[0]).matches() && lngDMS.matcher(pair[1]).matches();

        return lat.matcher(pair[0]).matches() && lng.matcher(pair[1]).matches();
    }

    @Override
    public boolean validate(FieldHelper helper, IsLatLong annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String)
            return isLatLng((String) valor, false);

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsLatLong annotation) {
        String message = String.format("'%s' precisar ser uma string latitude,longitude", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
