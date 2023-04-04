package isac.galvao.validator.locale;

public enum LocalePostalCode {
    AD("^AD\\d{3}$", true),
    AT("^\\d{4}$", true),
    AU("^\\d{4}$", true),
    AZ("^AZ\\d{4}$", true),
    BA("^([7-8]\\d{4}$)", true),
    BE("^\\d{4}$", true),
    BG("^\\d{4}$", true),
    BR("^\\d{5}-\\d{3}$", true),
    BY("^2[1-4]\\d{4}$", true),
    CA("^[ABCEGHJKLMNPRSTVXY]\\d[ABCEGHJ-NPRSTV-Z][\\s\\-]?\\d[ABCEGHJ-NPRSTV-Z]\\d$", false),
    CH("^\\d{4}$", true),
    CN("^(0[1-7]|1[012356]|2[0-7]|3[0-6]|4[0-7]|5[1-7]|6[1-7]|7[1-5]|8[1345]|9[09])\\d{4}$", true),
    CZ("^\\d{3}\\s?\\d{2}$", true),
    DE("^\\d{5}$", true),
    DK("^\\d{4}$", true),
    DO("^\\d{5}$", true),
    DZ("^\\d{5}$", true),
    EE("^\\d{5}$", true),
    ES("^(5[0-2]{1}|[0-4]{1}\\d{1})\\d{3}$", true),
    FI("^\\d{5}$", true),
    FR("^\\d{2}\\s?\\d{3}$", true),
    GB("^(gir\\s?0aa|[a-z]{1,2}\\d[\\da-z]?\\s?(\\d[a-z]{2})?)$", false),
    GR("^\\d{3}\\s?\\d{2}$", true),
    HR("^([1-5]\\d{4}$)", true),
    HT("^HT\\d{4}$", true),
    HU("^\\d{4}$", true),
    ID("^\\d{5}$", true),
    IE("^(?!.*(?:o))[A-Za-z]\\d[\\dw]\\s\\w{4}$", false),
    IL("^(\\d{5}|\\d{7})$", true),
    IN("^((?!10|29|35|54|55|65|66|86|87|88|89)[1-9][0-9]{5})$", true),
    IR("^(?!(\\d)\\1{3})[13-9]{4}[1346-9][013-9]{5}$", true),
    IS("^\\d{3}$", true),
    IT("^\\d{5}$", true),
    JP("^\\d{3}\\-\\d{4}$", true),
    KE("^\\d{5}$", true),
    KR("^(\\d{5}|\\d{6})$", true),
    LI("^(948[5-9]|949[0-7])$", true),
    LT("^LT\\-\\d{5}$", true),
    LU("^\\d{4}$", true),
    LV("^LV\\-\\d{4}$", true),
    LK("^\\d{5}$", true),
    MG("^\\d{3}$", true),
    MX("^\\d{5}$", true),
    MT("^[A-Za-z]{3}\\s{0,1}\\d{4}$", true),
    MY("^\\d{5}$", true),
    NL("^\\d{4}\\s?[a-z]{2}$", false),
    NO("^\\d{4}$", true),
    NP("^(10|21|22|32|33|34|44|45|56|57)\\d{3}$|^(977)$", false),
    NZ("^\\d{4}$", true),
    PL("^\\d{2}\\-\\d{3}$", true),
    PR("^00[679]\\d{2}([ -]\\d{4})?$", true),
    PT("^\\d{4}\\-\\d{3}?$", true),
    RO("^\\d{6}$", true),
    RU("^\\d{6}$", true),
    SA("^\\d{5}$", true),
    SE("^[1-9]\\d{2}\\s?\\d{2}$", true),
    SG("^\\d{6}$", true),
    SI("^\\d{4}$", true),
    SK("^\\d{3}\\s?\\d{2}$", true),
    TH("^\\d{5}$", true),
    TN("^\\d{4}$", true),
    TW("^\\d{3}(\\d{2})?$", true),
    UA("^\\d{5}$", true),
    US("^\\d{5}(-\\d{4})?$", true),
    ZA("^\\d{4}$", true),
    ZM("^\\d{5}$", true);

    private final String value;
    private final boolean caseSensitive;

    LocalePostalCode(final String value, final boolean caseSensitive) {
        this.value = value;
        this.caseSensitive = caseSensitive;
    }

    public String getValue() {
        return value;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }
}
