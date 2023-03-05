package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsCreditCard;
import isac.galvao.validator.enums.CreditCardProvider;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;

public class IsCreditCardValidator implements AnnotationValidatorInterface<IsCreditCard> {

    @Override
    public boolean validate(FieldHelper helper, IsCreditCard annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            final String sanitized = str.replaceAll("[- ]+", "");
            if (!annotation.value().getRegex().matcher(sanitized).matches()) return false;
            return isLuhnNumber(sanitized);
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsCreditCard annotation) {
        String message = String.format("'%s' precisa ser um número de cartão de crédito %s válido", helper.getAttributeName(), annotation.value().toString());

        if (annotation.value() == CreditCardProvider.ALL)
            message = String.format("'%s' precisa ser um número de cartão de crédito válido", helper.getAttributeName());

        return new ValidationError(helper, message, annotation);
    }

    private boolean isLuhnNumber(String str) {
        int nDigits = str.length();
        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {
            int d = str.charAt(i) - '0';
            if (isSecond)
                d = d * 2;
            nSum += d / 10;
            nSum += d % 10;
            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }
}
