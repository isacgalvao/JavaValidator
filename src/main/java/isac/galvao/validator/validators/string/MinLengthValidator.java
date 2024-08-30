package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.MinLength;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MinLengthValidator implements AnnotationValidatorInterface<MinLength> {
    @Override
    public boolean validate(FieldHelper helper, MinLength annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            final List<String> presentationSequences = getMatches(Pattern.compile("(\\uFE0F|\\uFE0E)"), str);
            final List<String> surrogatePairs = getMatches(Pattern.compile("[\\uD800-\\uDBFF][\\uDC00-\\uDFFF]"), str);
            final int len = str.length() - presentationSequences.size() - surrogatePairs.size();

            return len >= annotation.value();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, MinLength annotation) {
        String message = String.format("'%s' precisa ter no mínimo %s caracteres", helper.getAttributeName(), annotation.value());
        return new ValidationError(helper, message, annotation);
    }

    private List<String> getMatches(Pattern p, String str) {
        Matcher m = p.matcher(str);
        List<String> list = new ArrayList<>();
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }
}
