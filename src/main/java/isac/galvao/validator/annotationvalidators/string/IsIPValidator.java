package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsIP;
import isac.galvao.validator.enums.IPVersion;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

public class IsIPValidator implements AnnotationValidatorInterface<IsIP> {

    final String IPv4SegmentFormat = "(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
    final String IPv4AddressFormat = "(%s[.]){3}%s".formatted(IPv4SegmentFormat, IPv4SegmentFormat);
    final Pattern IPv4AddressRegExp = Pattern.compile("^%s$".formatted(IPv4AddressFormat));

    final String IPv6SegmentFormat = "(?:[0-9a-fA-F]{1,4})";
    final Pattern IPv6AddressRegExp = Pattern.compile("^(" +
            "(?:" + IPv6SegmentFormat + ":){7}(?:" + IPv6SegmentFormat + "|:)|" +
            "(?:" + IPv6SegmentFormat + ":){6}(?:" + IPv4AddressFormat + "|:" + IPv6SegmentFormat + "|:)|" +
            "(?:" + IPv6SegmentFormat + ":){5}(?::" + IPv4AddressFormat + "|(:" + IPv6SegmentFormat + "){1,2}|:)|" +
            "(?:" + IPv6SegmentFormat + ":){4}(?:(:" + IPv6SegmentFormat + "){0,1}:" + IPv4AddressFormat + "|(:" + IPv6SegmentFormat + "){1,3}|:)|" +
            "(?:" + IPv6SegmentFormat + ":){3}(?:(:" + IPv6SegmentFormat + "){0,2}:" + IPv4AddressFormat + "|(:" + IPv6SegmentFormat + "){1,4}|:)|" +
            "(?:" + IPv6SegmentFormat + ":){2}(?:(:" + IPv6SegmentFormat + "){0,3}:" + IPv4AddressFormat + "|(:" + IPv6SegmentFormat + "){1,5}|:)|" +
            "(?:" + IPv6SegmentFormat + ":){1}(?:(:" + IPv6SegmentFormat + "){0,4}:" + IPv4AddressFormat + "|(:" + IPv6SegmentFormat + "){1,6}|:)|" +
            "(?::((?::" + IPv6SegmentFormat + "){0,5}:" + IPv4AddressFormat + "|(?::" + IPv6SegmentFormat + "){1,7}|:))" +
            ")(%[0-9a-zA-Z-.:]{1,})?$");

    @Override
    public boolean validate(FieldHelper helper, IsIP annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            return switch (annotation.value()) {
                case V4 -> IPv4AddressRegExp.matcher(str).matches();
                case V6 -> IPv6AddressRegExp.matcher(str).matches();
                default -> IPv4AddressRegExp.matcher(str).matches() || IPv6AddressRegExp.matcher(str).matches();
            };
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsIP annotation) {
        String message = String.format("'%s' precisa ser um endereço IP%s", helper.getAttributeName(),
                annotation.value() == IPVersion.ANY ? "" : annotation.value().toString());
        return new ValidationError(helper, message, annotation);
    }
}
