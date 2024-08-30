package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsDecimal;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class IsDecimalValidator implements AnnotationValidatorInterface<IsDecimal> {

    private final List<String> blacklist = List.of("", "-", "+");

    private Pattern decimalPattern(IsDecimal ann) {
        String locale = ann.locale().getValue();
        String decimalDigits = ann.decimalDigits();
        String forceDecimal = ann.forceDecimal() ? "" : "?";
        String regex = "^[-+]?([0-9]+)?(\\%s[0-9]{%s})%s$".formatted(locale, decimalDigits, forceDecimal);
        return Pattern.compile(regex);
    }

    @Override
    public boolean validate(FieldHelper helper, IsDecimal annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            return !blacklist.contains(str.replace(" ", "")) && decimalPattern(annotation).matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsDecimal annotation) {
        String message = String.format("'%s' não é um número decimal válido.", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
