package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsAlphanumeric;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;
import isac.galvao.validator.util.RegexUtil;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsAlphanumericValidator implements AnnotationValidatorInterface<IsAlphanumeric> {
    @Override
    public boolean validate(FieldHelper helper, IsAlphanumeric annotation) {
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
    public ValidationError buildMessage(FieldHelper helper, IsAlphanumeric annotation) {
        String message = String.format("'%s' deve conter apenas letras e números", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
