package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsHash;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is a hash of type algorithm. <p>
 * Algorithm is one of ['md4', 'md5', 'sha1', 'sha256', 'sha384', 'sha512', 'ripemd128', 'ripemd160', 'tiger128',
 * 'tiger160', 'tiger192', 'crc32', 'crc32b']
 */
public class IsHashValidator implements AnnotationValidatorInterface<IsHash> {

    @Override
    public boolean validate(FieldHelper helper, IsHash annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            final Pattern hash = Pattern.compile("^[a-fA-F0-9]{%s}$".formatted(annotation.value().getHashLength()));
            return hash.matcher((String) valor).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsHash annotation) {
        String message = String.format("'%s' precisa ser um hash %s", helper.getAttributeName(), annotation.value().toString());
        return new ValidationError(helper, message, annotation);
    }
}
