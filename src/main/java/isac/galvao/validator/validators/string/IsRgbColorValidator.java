package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsRgbColor;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is a rgb or rgba color.
 * `includePercentValues` defaults to true. If you don't want to allow to set rgb or rgba values with percents, like rgb(5%,5%,5%), or rgba(90%,90%,90%,.3), then set it to false.
 */
public class IsRgbColorValidator implements AnnotationValidatorInterface<IsRgbColor> {

    private final Pattern rgbColor = Pattern.compile("^rgb\\((([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]),){2}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\)$");
    private final Pattern rgbaColor = Pattern.compile("^rgba\\((([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]),){3}(0?\\.\\d|1(\\.0)?|0(\\.0)?)\\)$");
    private final Pattern rgbColorPercent = Pattern.compile("^rgb\\((([0-9]%|[1-9][0-9]%|100%),){2}([0-9]%|[1-9][0-9]%|100%)\\)$");
    private final Pattern rgbaColorPercent = Pattern.compile("^rgba\\((([0-9]%|[1-9][0-9]%|100%),){3}(0?\\.\\d|1(\\.0)?|0(\\.0)?)\\)$");

    @Override
    public boolean validate(FieldHelper helper, IsRgbColor annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            if (!annotation.includePercentValues())
                return this.rgbColor.matcher(str).matches() || this.rgbaColor.matcher(str).matches();

            return this.rgbColor.matcher(str).matches() || this.rgbaColor.matcher(str).matches() || this.rgbColorPercent.matcher(str).matches() || this.rgbaColorPercent.matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsRgbColor annotation) {
        String message = String.format("'%s' precisa ser uma cor RGB", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
