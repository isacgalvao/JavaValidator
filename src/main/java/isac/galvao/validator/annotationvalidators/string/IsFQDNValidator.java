package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsFQDN;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public class IsFQDNValidator implements AnnotationValidatorInterface<IsFQDN> {
    @Override
    public boolean validate(FieldHelper helper, IsFQDN annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            /* Remove the optional trailing dot before checking validity */
            if (annotation.allowTrailingDot() && str.charAt(str.length() - 1) == '.') {
                str = str.substring(0, str.length() - 1);
            }

            /* Remove the optional wildcard before checking validity */
            if (annotation.allowWildcard() && str.indexOf("*.") == 0) {
                str = str.substring(2);
            }

            final String[] parts = str.split("\\.");

            // preventing ArrayIndexOutOfBoundsException
            if (parts.length == 0) return false;

            final String tld = parts[parts.length-1];

            if (annotation.requireTLD()) {
                // disallow fqdns without tld
                if (parts.length < 2) return false;

                Pattern pattern = Pattern.compile("^([a-z\\u00A1-\\u00A8\\u00AA-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]{2,}|xn[a-z0-9-]{2,})$", Pattern.CASE_INSENSITIVE);
                if (annotation.allowNumericTLD() && pattern.matcher(tld).matches()) return false;

                if (tld.matches("\\s")) return false;
            }

            // reject numeric TLDs
            if (!annotation.allowNumericTLD() && tld.matches("^\\d+$")) return false;

            return Arrays.stream(parts).allMatch((part) -> {
                if (part.length() > 63 && !annotation.ignoreMaxLength()) return false;

                if (!part.matches("^[a-z_\\u00a1-\\uffff0-9-]+$")) return false;

                // disallow full-width chars
                if (Pattern.compile("[\\uff01-\\uff5e]").matcher(part).find()) return false;

                // disallow parts starting or ending with hyphen
                if (part.matches("^-|-$")) return false;

                if (!annotation.allowUnderscores() && part.matches("_")) return false;

                return true;
            });
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsFQDN annotation) {
        String message = String.format("'%s' precisa ser um domínio válido", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
