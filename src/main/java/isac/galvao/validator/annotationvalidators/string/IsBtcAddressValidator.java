package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsBtcAddress;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsBtcAddressValidator implements AnnotationValidatorInterface<IsBtcAddress> {

    private final Pattern bech32 = Pattern.compile("^(bc1)[a-z0-9]{25,39}$");
    private final Pattern base58 = Pattern.compile("^(1|3)[A-HJ-NP-Za-km-z1-9]{25,39}$");

    @Override
    public boolean validate(FieldHelper helper, IsBtcAddress annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            return this.bech32.matcher(str).matches() || this.base58.matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsBtcAddress annotation) {
        String message = String.format("'%s' precisa ser um endereço BTC", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
