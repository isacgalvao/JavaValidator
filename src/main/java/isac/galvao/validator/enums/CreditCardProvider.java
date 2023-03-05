package isac.galvao.validator.enums;

import java.util.regex.Pattern;

public enum CreditCardProvider {
    AMEX(Pattern.compile("^3[47][0-9]{13}$")),
    DINERSCLUB(Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{11}$")),
    DISCOVER(Pattern.compile("^6(?:011|5[0-9][0-9])[0-9]{12,15}$")),
    JCB(Pattern.compile("^(?:2131|1800|35\\\\d{3})\\\\d{11}$")),
    MASTERCARD(Pattern.compile("^5[1-5][0-9]{2}|(222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}$")),
    UNIONPAY(Pattern.compile("^(6[27][0-9]{14}|^(81[0-9]{14,17}))$")),
    VISA(Pattern.compile("^(?:4[0-9]{12})(?:[0-9]{3,6})?$")),
    ALL(Pattern.compile("^(?:4[0-9]{12}(?:[0-9]{3,6})?|5[1-5][0-9]{14}|(222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}|6(?:011|5[0-9][0-9])[0-9]{12,15}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\\\d{3})\\\\d{11}|6[27][0-9]{14}|^(81[0-9]{14,17}))$"));

    final private Pattern regex;
    CreditCardProvider(Pattern compile) {
        this.regex = compile;
    }

    public Pattern getRegex() {
        return this.regex;
    }
}
