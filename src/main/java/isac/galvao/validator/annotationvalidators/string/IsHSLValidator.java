package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsHSL;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is an HSL (hue, saturation, lightness, optional alpha) color based on CSS Colors Level 4 specification.<p>
 * Comma-separated format supported. Space-separated format supported except a few edge cases (ex: hsl(200grad+.1%62%/1)).<p>
 */
public class IsHSLValidator implements AnnotationValidatorInterface<IsHSL> {

    private final Pattern hslComma = Pattern.compile("^hsla?\\(((\\+|\\-)?([0-9]+(\\.[0-9]+)?(e(\\+|\\-)?[0-9]+)?|\\.[0-9]+(e(\\+|\\-)?[0-9]+)?))(deg|grad|rad|turn)?(,(\\+|\\-)?([0-9]+(\\.[0-9]+)?(e(\\+|\\-)?[0-9]+)?|\\.[0-9]+(e(\\+|\\-)?[0-9]+)?)%){2}(,((\\+|\\-)?([0-9]+(\\.[0-9]+)?(e(\\+|\\-)?[0-9]+)?|\\.[0-9]+(e(\\+|\\-)?[0-9]+)?)%?))?\\)$",Pattern.CASE_INSENSITIVE);
    private final Pattern hslSpace = Pattern.compile("^hsla?\\(((\\+|\\-)?([0-9]+(\\.[0-9]+)?(e(\\+|\\-)?[0-9]+)?|\\.[0-9]+(e(\\+|\\-)?[0-9]+)?))(deg|grad|rad|turn)?(\\s(\\+|\\-)?([0-9]+(\\.[0-9]+)?(e(\\+|\\-)?[0-9]+)?|\\.[0-9]+(e(\\+|\\-)?[0-9]+)?)%){2}\\s?(\\/\\s((\\+|\\-)?([0-9]+(\\.[0-9]+)?(e(\\+|\\-)?[0-9]+)?|\\.[0-9]+(e(\\+|\\-)?[0-9]+)?)%?)\\s?)?\\)$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(FieldHelper helper, IsHSL annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;

            // Strip duplicate spaces before calling the validation regex
            final String temp = str.replaceAll("\\s+", " ");
            final String strippedStr = Pattern.compile("\\s?(hsla?\\(|\\)|,)\\s?", Pattern.CASE_INSENSITIVE).matcher(temp).replaceAll("$1");

            if (strippedStr.contains(",")) return hslComma.matcher(strippedStr).matches();

            return hslSpace.matcher(strippedStr).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsHSL annotation) {
        String message = String.format("'%s precisa ser uma cor HSL'", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
