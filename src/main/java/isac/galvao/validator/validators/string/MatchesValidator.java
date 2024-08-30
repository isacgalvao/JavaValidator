package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.Matches;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class MatchesValidator implements AnnotationValidatorInterface<Matches> {
    @Override
    public boolean validate(FieldHelper helper, Matches annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            if (!isRegex(annotation.value())) throw new RuntimeException("The Regex passed as value on annotation is invalid");

            if (annotation.flags().length > 0)
                //noinspection MagicConstant
                return Pattern.compile(annotation.value(), combineFlags(annotation.flags())).matcher(str).matches();

            return Pattern.compile(annotation.value()).matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, Matches annotation) {
        String message = String.format("'%s' precisa seguir o padrão definido", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }

    private boolean isRegex(String str) {
        try {
            Pattern.compile(str);
            return true;
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    private int combineFlags(int[] flags) {
        int combined = flags[0];
        for (int i = 1; i < flags.length; i++) {
            combined |= flags[i];
        }
        return combined;
    }
}
