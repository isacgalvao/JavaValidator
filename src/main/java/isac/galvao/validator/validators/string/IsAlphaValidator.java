package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsAlpha;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;
import isac.galvao.validator.util.RegexUtil;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsAlphaValidator implements AnnotationValidatorInterface<IsAlpha> {
    @Override
    public boolean validate(FieldHelper helper, IsAlpha annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            if (!annotation.ignore().isEmpty())
                if (RegexUtil.isRegex(annotation.ignore()))
                    str = str.replaceAll(annotation.ignore(), "");
                else
                    str = str.replaceAll(Pattern.quote(annotation.ignore().replaceAll(Pattern.quote("-[\\]{}()*+?.,\\\\^$|#\\\\s"), "\\$&")), "");
            return annotation.locale().getPattern().matcher(str).matches();
        }
        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsAlpha annotation) {
        String message = String.format("'%s' deve conter apenas letras (a-zA-Z)", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
