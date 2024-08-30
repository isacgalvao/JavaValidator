package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsEthereumAddress;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsEthereumAddressValidator implements AnnotationValidatorInterface<IsEthereumAddress> {

    private final Pattern eth = Pattern.compile("^(0x)[0-9a-f]{40}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(FieldHelper helper, IsEthereumAddress annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String)
            return this.eth.matcher((String) valor).matches();

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsEthereumAddress annotation) {
        String message = String.format("'%s' precisa ser um endereço Ethereum", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
