package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsDateString;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class IsDateStringValidator implements AnnotationValidatorInterface<IsDateString> {
    @Override
    public boolean validate(FieldHelper helper, IsDateString annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;

            String format = annotation.format().getValue();

            if (annotation.withTime()) format += " HH:mm:ss:ms";

            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);

            try {
                dateFormat.parse(str.strip());
            } catch (ParseException e) {
                return false;
            }
            return true;
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsDateString annotation) {
        String format = annotation.withTime() ? "(%s HH:mm:ss:ms)" : "(%s)";
        String message = String.format("'%s' precisa ser uma string de data ISO 8601 " + format + " válida",
                helper.getAttributeName(), annotation.format().getValue());
        return new ValidationError(helper, message, annotation);
    }
}
