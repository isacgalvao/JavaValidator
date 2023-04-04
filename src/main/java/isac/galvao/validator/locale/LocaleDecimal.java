package isac.galvao.validator.locale;

public enum LocaleDecimal {
    ar("٫"),
    ar_AE("٫"),
    ar_BH("٫"),
    ar_DZ("٫"),
    ar_EG("."),
    ar_IQ("٫"),
    ar_JO("٫"),
    ar_KW("٫"),
    ar_LB("."),
    ar_LY("."),
    ar_MA("٫"),
    ar_QA("٫"),
    ar_QM("٫"),
    ar_SA("٫"),
    ar_SD("٫"),
    ar_SY("٫"),
    ar_TN("٫"),
    ar_YE("٫"),
    bg_BG(","),
    bn_BD("."),
    bn_IN("."),
    cs_CZ(","),
    da_DK(","),
    de_DE(","),
    el_GR(","),
    en_AU("."),
    en_GB("."),
    en_HK("."),
    en_IN("."),
    en_NZ("."),
    en_US("."),
    en_ZA("."),
    en_ZM(","),
    es_ES(","),
    fa_AF("٫"),
    fa_IR("٫"),
    fr_CA(","),
    fr_FR(","),
    hi_IN(","),
    hu_HU(","),
    id_ID(","),
    it_IT(","),
    ku_IQ(","),
    nb_NO(","),
    nl_NL(","),
    nn_NO(","),
    pl_PL(","),
    pl_Pl(","),
    pt_BR(","),
    pt_PT(","),
    ru_RU(","),
    si_LK(","),
    sl_SI(","),
    sr_RS(","),
    sr_RS_latin(","),
    sv_SE(","),
    tr_TR(","),
    uk_UA(","),
    vi_VN(",");

    private final String value;

    LocaleDecimal(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
