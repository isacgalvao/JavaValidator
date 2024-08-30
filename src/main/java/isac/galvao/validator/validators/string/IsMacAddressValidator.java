package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsMacAddress;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is a MAC address.
 */
public class IsMacAddressValidator implements AnnotationValidatorInterface<IsMacAddress> {

    private final Pattern macAddress48 = Pattern.compile("^(?:[0-9a-fA-F]{2}([-:\\s]))([0-9a-fA-F]{2}\\1){4}([0-9a-fA-F]{2})$");

    private final Pattern macAddress48NoSeparators = Pattern.compile("^([0-9a-fA-F]){12}$");

    private final Pattern macAddress48WithDots = Pattern.compile("^([0-9a-fA-F]{4}\\.){2}([0-9a-fA-F]{4})$");

    private final Pattern macAddress64 = Pattern.compile("^(?:[0-9a-fA-F]{2}([-:\\s]))([0-9a-fA-F]{2}\\1){6}([0-9a-fA-F]{2})$");

    private final Pattern macAddress64NoSeparators = Pattern.compile("^([0-9a-fA-F]){16}$");

    private final Pattern macAddress64WithDots = Pattern.compile("^([0-9a-fA-F]{4}\\.){3}([0-9a-fA-F]{4})$");

    @Override
    public boolean validate(FieldHelper helper, IsMacAddress annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            return isMacAddress(str, annotation.eui(), annotation.no_separators());
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsMacAddress annotation) {
        String message = String.format("'%s' precisa ser um endereço MAC", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }

    private boolean isMacAddress(String str, String eui, boolean noSeparators) {
        if (noSeparators) {
            if (eui.equals("48"))
                return this.macAddress48NoSeparators.matcher(str).matches();

            if (eui.equals("64"))
                return this.macAddress64NoSeparators.matcher(str).matches();
        }

        if (eui.equals("48"))
            return this.macAddress48.matcher(str).matches() || this.macAddress48WithDots.matcher(str).matches();

        if (eui.equals("64"))
            return this.macAddress64.matcher(str).matches() || this.macAddress64WithDots.matcher(str).matches();

        return isMacAddress(str, "48", noSeparators) || isMacAddress(str, "64", noSeparators);
    }
}
