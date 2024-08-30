package isac.galvao.validator.validators.date;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.date.MaxDate;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

public class MaxDateValidator implements AnnotationValidatorInterface<MaxDate> {
    @Override
    public boolean validate(FieldHelper helper, MaxDate annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof Date) {
            LocalDate userDate = ((Date) valor).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate maxDate = LocalDate.of(annotation.year(), annotation.month(), annotation.day());

            return maxDate.isAfter(userDate) || maxDate.isEqual(userDate);
        }


        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, MaxDate annotation) {
        String message = String.format("A data máxima permitida para '%s' é %s (%s)", helper.getAttributeName(), dateParser(annotation), annotation.format().getValue());
        return new ValidationError(helper, message, annotation);
    }

    private String dateParser(MaxDate ann) {
        return String.join(ann.format().getSeparator(), Stream.of(ann.day(), ann.month(), ann.year()).map(e -> e < 10 ? "0" + e : String.valueOf(e)).toList());
    }
}
