package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsBIC;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

import static isac.galvao.validator.annotationvalidators.string.IsISO31661Alpha2Validator.validISO31661Alpha2CountriesCodes;

public class IsBICValidator implements AnnotationValidatorInterface<IsBIC> {

    /**
     * <a href="https://en.wikipedia.org/wiki/ISO_9362">wiki</a>
     */
    private final Pattern regex = Pattern.compile("^[A-Za-z]{6}[A-Za-z0-9]{2}([A-Za-z0-9]{3})?$");

    @Override
    public boolean validate(FieldHelper helper, IsBIC annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) helper.getValue();
            final String countryCode = str.substring(4,6).toUpperCase();

            if (!validISO31661Alpha2CountriesCodes.contains(countryCode) && !countryCode.equals("XK"))
                return false;

            return this.regex.matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsBIC annotation) {
        String message = String.format("'%s' precisa ser um código BIC ou SWIFT", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
