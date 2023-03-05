package isac.galvao.validator.annotationvalidators.date;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.date.MinDate;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

public class MinDateValidator implements AnnotationValidatorInterface<MinDate> {
    @Override
    public boolean validate(FieldHelper helper, MinDate annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof Date) {
            LocalDate userDate = ((Date) valor).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate maxDate = LocalDate.of(annotation.year(), annotation.month(), annotation.day());

            return maxDate.isBefore(userDate) || maxDate.isEqual(userDate);
        }


        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, MinDate annotation) {
        String message = String.format("A data mínima permitida para '%s' é %s (%s)", helper.getAttributeName(), dateParser(annotation), annotation.format().getValue());
        return new ValidationError(helper, message, annotation);
    }

    private String dateParser(MinDate ann) {
        return String.join(ann.format().getSeparator(), Stream.of(ann.day(), ann.month(), ann.year()).map(e -> e < 10 ? "0" + e : String.valueOf(e)).toList());
    }
}
