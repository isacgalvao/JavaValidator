package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsMagnetURI;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is a magnet uri format.
 */
public class IsMagnetURIValidator implements AnnotationValidatorInterface<IsMagnetURI> {

    private final Pattern magnetURIComponent = Pattern.compile("(?:^magnet:\\?|[^?&]&)xt(?:\\.1)?=urn:(?:(?:aich|bitprint|btih|ed2k|ed2khash|kzhash|md5|sha1|tree:tiger):[a-z0-9]{32}(?:[a-z0-9]{8})?|btmh:1220[a-z0-9]{64})(?:$|&)", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(FieldHelper helper, IsMagnetURI annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;

            if (!str.contains("magnet:?")) return false;

            return this.magnetURIComponent.matcher(str).find();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsMagnetURI annotation) {
        String message = String.format("'%s' precisa ser um Magnet URI", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
