package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsDataURI;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class IsDataURIValidator implements AnnotationValidatorInterface<IsDataURI> {

    private final Pattern validMediaType = Pattern.compile("^[a-z]+\\/[a-z0-9\\-\\+\\._]+$", Pattern.CASE_INSENSITIVE);
    private final Pattern validAttribute = Pattern.compile("^[a-z\\-]+=[a-z0-9\\-]+$", Pattern.CASE_INSENSITIVE);
    private final Pattern validData = Pattern.compile("^[a-z0-9!\\$&'\\(\\)\\*\\+,;=\\-\\._~:@\\/\\?%\\s]*$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(FieldHelper helper, IsDataURI annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;

            List<String> data = new ArrayList<>(List.of(str.split(",")));
            if (data.size() < 2) return false;

            final List<String> attributes = new ArrayList<>(List.of(data.remove(0).strip().split(";")));
            final String schemeAndMediaType = attributes.remove(0);

            if (!schemeAndMediaType.startsWith("data:")) return false;

            String mediaType = schemeAndMediaType.substring(5);
            if (!mediaType.equals("") && !validMediaType.matcher(mediaType).matches()) return false;

            for (int i = 0; i < attributes.size(); i++) {
                if (
                        !(i == attributes.size() - 1 && attributes.get(i).equalsIgnoreCase("base64")) &&
                                !validAttribute.matcher(attributes.get(i)).matches()
                ) return false;
            }

            for (String datum : data)
                if (!validData.matcher(datum).matches())
                    return false;

            return true;
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsDataURI annotation) {
        String message = String.format("'%s' precisa estar no formato data uri", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
