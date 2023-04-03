package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsMobilePhone;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Checks if the string is a mobile phone number (locale is either an array of locales (e.g ['sk-SK', 'sr-RS'])
 * OR one of ['am_AM', 'ar_AE', 'ar_BH', 'ar_DZ', 'ar_EG', 'ar_EH', 'ar_IQ', 'ar_JO', 'ar_KW', 'ar_LB', 'ar_LY', 'ar_MA',
 * 'ar_OM', 'ar_PS', 'ar_SA', 'ar_SY', 'ar_TN', 'ar_YE', 'az_AZ', 'be_BY', 'bg_BG', 'bn_BD', 'bs_BA', 'ca_AD', 'cs_CZ',
 * 'da_DK', 'de_AT', 'de_CH', 'de_DE', 'de_LU', 'dv_MV', 'dz_BT', 'el_CY', 'el_GR', 'en_AG', 'en_AI', 'en_AU', 'en_BM',
 * 'en_BS', 'en_BW', 'en_CA', 'en_GB', 'en_GG', 'en_GH', 'en_GY', 'en_HK', 'en_IE', 'en_IN', 'en_JM', 'en_KE', 'en_KI',
 * 'en_KN', 'en_LS', 'en_MO', 'en_MT', 'en_MU', 'en_NA', 'en_NG', 'en_NZ', 'en_PG', 'en_PH', 'en_PK', 'en_RW', 'en_SG',
 * 'en_SL', 'en_SS', 'en_TZ', 'en_UG', 'en_US', 'en_ZA', 'en_ZM', 'en_ZW', 'es_AR', 'es_BO', 'es_CL', 'es_CO', 'es_CR',
 * 'es_CU', 'es_DO', 'es_EC', 'es_ES', 'es_HN', 'es_MX', 'es_NI', 'es_PA', 'es_PE', 'es_PY', 'es_SV', 'es_UY', 'es_VE',
 * 'et_EE', 'fa_AF', 'fa_IR', 'fi_FI', 'fj_FJ', 'fo_FO', 'fr_BE', 'fr_BF', 'fr_BJ', 'fr_CA', 'fr_CD', 'fr_CF', 'fr_CH',
 * 'fr_CM', 'fr_FR', 'fr_GF', 'fr_GP', 'fr_MQ', 'fr_PF', 'fr_RE', 'fr_WF', 'ga_IE', 'he_IL', 'hu_HU', 'id_ID', 'ir_IR',
 * 'it_CH', 'it_IT', 'it_SM', 'ja_JP', 'ka_GE', 'kk_KZ', 'kl_GL', 'ko_KR', 'ky_KG', 'lt_LT', 'lv_LV', 'mg_MG', 'mn_MN',
 * 'ms_MY', 'my_MM', 'mz_MZ', 'nb_NO', 'ne_NP', 'nl_AW', 'nl_BE', 'nl_NL', 'nn_NO', 'pl_PL', 'pt_AO', 'pt_BR', 'pt_PT',
 * 'ro_MD', 'ro_RO', 'ru_RU', 'si_LK', 'sk_SK', 'sl_SI', 'so_SO', 'sq_AL', 'sr_RS', 'sv_SE', 'tg_TJ', 'th_TH', 'tk_TM',
 * 'tr_TR', 'uk_UA', 'uz_UZ', 'vi_VN', 'zh_CN', 'zh_HK', 'zh_MO', 'zh_TW']
 */
public class IsMobilePhoneValidator implements AnnotationValidatorInterface<IsMobilePhone> {

    private final Map<String, String> phones;

    {
        this.phones = new HashMap<>();
        phones.put("am_AM", "^(\\+?374|0)((10|[9|7][0-9])\\d{6}$|[2-4]\\d{7}$)");
        phones.put("ar_AE", "^((\\+?971)|0)?5[024568]\\d{7}$");
        phones.put("ar_BH", "^(\\+?973)?(3|6)\\d{7}$");
        phones.put("ar_DZ", "^(\\+?213|0)(5|6|7)\\d{8}$");
        phones.put("ar_LB", "^(\\+?961)?((3|81)\\d{6}|7\\d{7})$");
        phones.put("ar_EG", "^((\\+?20)|0)?1[0125]\\d{8}$");
        phones.put("ar_IQ", "^(\\+?964|0)?7[0-9]\\d{8}$");
        phones.put("ar_JO", "^(\\+?962|0)?7[789]\\d{7}$");
        phones.put("ar_KW", "^(\\+?965)([569]\\d{7}|41\\d{6})$");
        phones.put("ar_LY", "^((\\+?218)|0)?(9[1-6]\\d{7}|[1-8]\\d{7,9})$");
        phones.put("ar_MA", "^(?:(?:\\+|00)212|0)[5-7]\\d{8}$");
        phones.put("ar_OM", "^((\\+|00)968)?(9[1-9])\\d{6}$");
        phones.put("ar_PS", "^(\\+?970|0)5[6|9](\\d{7})$");
        phones.put("ar_SA", "^(!?(\\+?966)|0)?5\\d{8}$");
        phones.put("ar_SY", "^(!?(\\+?963)|0)?9\\d{8}$");
        phones.put("ar_TN", "^(\\+?216)?[2459]\\d{7}$");
        phones.put("az_AZ", "^(\\+994|0)(10|5[015]|7[07]|99)\\d{7}$");
        phones.put("bs_BA", "^((((\\+|00)3876)|06))((([0-3]|[5-6])\\d{6})|(4\\d{7}))$");
        phones.put("be_BY", "^(\\+?375)?(24|25|29|33|44)\\d{7}$");
        phones.put("bg_BG", "^(\\+?359|0)?8[789]\\d{7}$");
        phones.put("bn_BD", "^(\\+?880|0)1[13456789][0-9]{8}$");
        phones.put("ca_AD", "^(\\+376)?[346]\\d{5}$");
        phones.put("cs_CZ", "^(\\+?420)? ?[1-9][0-9]{2} ?[0-9]{3} ?[0-9]{3}$");
        phones.put("da_DK", "^(\\+?45)?\\s?\\d{2}\\s?\\d{2}\\s?\\d{2}\\s?\\d{2}$");
        phones.put("de_DE", "^((\\+49|0)1)(5[0-25-9]\\d|6([23]|0\\d?)|7([0-57-9]|6\\d))\\d{7,9}$");
        phones.put("de_AT", "^(\\+43|0)\\d{1,4}\\d{3,12}$");
        phones.put("de_CH", "^(\\+41|0)([1-9])\\d{1,9}$");
        phones.put("de_LU", "^(\\+352)?((6\\d1)\\d{6})$");
        phones.put("dv_MV", "^(\\+?960)?(7[2-9]|9[1-9])\\d{5}$");
        phones.put("el_GR", "^(\\+?30|0)?6(8[5-9]|9(?![26])[0-9])\\d{7}$");
        phones.put("el_CY", "^(\\+?357?)?(9(9|6)\\d{6})$");
        phones.put("en_AI", "^(\\+?1|0)264(?:2(35|92)|4(?:6[1-2]|76|97)|5(?:3[6-9]|8[1-4])|7(?:2(4|9)|72))\\d{4}$");
        phones.put("en_AU", "^(\\+?61|0)4\\d{8}$");
        phones.put("en_AG", "^(?:\\+1|1)268(?:464|7(?:1[3-9]|[28]\\d|3[0246]|64|7[0-689]))\\d{4}$");
        phones.put("en_BM", "^(\\+?1)?441(((3|7)\\d{6}$)|(5[0-3][0-9]\\d{4}$)|(59\\d{5}$))");
        phones.put("en_BS", "^(\\+?1[-\\s]?|0)?\\(?242\\)?[-\\s]?\\d{3}[-\\s]?\\d{4}$");
        phones.put("en_GB", "^(\\+?44|0)7\\d{9}$");
        phones.put("en_GG", "^(\\+?44|0)1481\\d{6}$");
        phones.put("en_GH", "^(\\+233|0)(20|50|24|54|27|57|26|56|23|28|55|59)\\d{7}$");
        phones.put("en_GY", "^(\\+592|0)6\\d{6}$");
        phones.put("en_HK", "^(\\+?852[-\\s]?)?[456789]\\d{3}[-\\s]?\\d{4}$");
        phones.put("en_MO", "^(\\+?853[-\\s]?)?[6]\\d{3}[-\\s]?\\d{4}$");
        phones.put("en_IE", "^(\\+?353|0)8[356789]\\d{7}$");
        phones.put("en_IN", "^(\\+?91|0)?[6789]\\d{9}$");
        phones.put("en_JM", "^(\\+?876)?\\d{7}$");
        phones.put("en_KE", "^(\\+?254|0)(7|1)\\d{8}$");
        phones.put("fr_CF", "^(\\+?236| ?)(70|75|77|72|21|22)\\d{6}$");
        phones.put("en_SS", "^(\\+?211|0)(9[1257])\\d{7}$");
        phones.put("en_KI", "^((\\+686|686)?)?( )?((6|7)(2|3|8)[0-9]{6})$");
        phones.put("en_KN", "^(?:\\+1|1)869(?:46\\d|48[89]|55[6-8]|66\\d|76[02-7])\\d{4}$");
        phones.put("en_LS", "^(\\+?266)(22|28|57|58|59|27|52)\\d{6}$");
        phones.put("en_MT", "^(\\+?356|0)?(99|79|77|21|27|22|25)[0-9]{6}$");
        phones.put("en_MU", "^(\\+?230|0)?\\d{8}$");
        phones.put("en_NA", "^(\\+?264|0)(6|8)\\d{7}$");
        phones.put("en_NG", "^(\\+?234|0)?[789]\\d{9}$");
        phones.put("en_NZ", "^(\\+?64|0)[28]\\d{7,9}$");
        phones.put("en_PG", "^(\\+?675|0)?(7\\d|8[18])\\d{6}$");
        phones.put("en_PK", "^((00|\\+)?92|0)3[0-6]\\d{8}$");
        phones.put("en_PH", "^(09|\\+639)\\d{9}$");
        phones.put("en_RW", "^(\\+?250|0)?[7]\\d{8}$");
        phones.put("en_SG", "^(\\+65)?[3689]\\d{7}$");
        phones.put("en_SL", "^(\\+?232|0)\\d{8}$");
        phones.put("en_TZ", "^(\\+?255|0)?[67]\\d{8}$");
        phones.put("en_UG", "^(\\+?256|0)?[7]\\d{8}$");
        phones.put("en_US", "^((\\+1|1)?( |-)?)?(\\([2-9][0-9]{2}\\)|[2-9][0-9]{2})( |-)?([2-9][0-9]{2}( |-)?[0-9]{4})$");
        phones.put("en_ZA", "^(\\+?27|0)\\d{9}$");
        phones.put("en_ZM", "^(\\+?26)?09[567]\\d{7}$");
        phones.put("en_ZW", "^(\\+263)[0-9]{9}$");
        phones.put("en_BW", "^(\\+?267)?(7[1-8]{1})\\d{6}$");
        phones.put("es_AR", "^\\+?549(11|[2368]\\d)\\d{8}$");
        phones.put("es_BO", "^(\\+?591)?(6|7)\\d{7}$");
        phones.put("es_CO", "^(\\+?57)?3(0(0|1|2|4|5)|1\\d|2[0-4]|5(0|1))\\d{7}$");
        phones.put("es_CL", "^(\\+?56|0)[2-9]\\d{1}\\d{7}$");
        phones.put("es_CR", "^(\\+506)?[2-8]\\d{7}$");
        phones.put("es_CU", "^(\\+53|0053)?5\\d{7}$");
        phones.put("es_DO", "^(\\+?1)?8[024]9\\d{7}$");
        phones.put("es_HN", "^(\\+?504)?[9|8|3|2]\\d{7}$");
        phones.put("es_EC", "^(\\+?593|0)([2-7]|9[2-9])\\d{7}$");
        phones.put("es_ES", "^(\\+?34)?[6|7]\\d{8}$");
        phones.put("es_PE", "^(\\+?51)?9\\d{8}$");
        phones.put("es_MX", "^(\\+?52)?(1|01)?\\d{10,11}$");
        phones.put("es_NI", "^(\\+?505)\\d{7,8}$");
        phones.put("es_PA", "^(\\+?507)\\d{7,8}$");
        phones.put("es_PY", "^(\\+?595|0)9[9876]\\d{7}$");
        phones.put("es_SV", "^(\\+?503)?[67]\\d{7}$");
        phones.put("es_UY", "^(\\+598|0)9[1-9][\\d]{6}$");
        phones.put("es_VE", "^(\\+?58)?(2|4)\\d{9}$");
        phones.put("et_EE", "^(\\+?372)?\\s?(5|8[1-4])\\s?([0-9]\\s?){6,7}$");
        phones.put("fa_IR", "^(\\+?98[\\-\\s]?|0)9[0-39]\\d[\\-\\s]?\\d{3}[\\-\\s]?\\d{4}$");
        phones.put("fi_FI", "^(\\+?358|0)\\s?(4[0-6]|50)\\s?(\\d\\s?){4,8}$");
        phones.put("fj_FJ", "^(\\+?679)?\\s?\\d{3}\\s?\\d{4}$");
        phones.put("fo_FO", "^(\\+?298)?\\s?\\d{2}\\s?\\d{2}\\s?\\d{2}$");
        phones.put("fr_BF", "^(\\+226|0)[67]\\d{7}$");
        phones.put("fr_BJ", "^(\\+229)\\d{8}$");
        phones.put("fr_CD", "^(\\+?243|0)?(8|9)\\d{8}$");
        phones.put("fr_CM", "^(\\+?237)6[0-9]{8}$");
        phones.put("fr_FR", "^(\\+?33|0)[67]\\d{8}$");
        phones.put("fr_GF", "^(\\+?594|0|00594)[67]\\d{8}$");
        phones.put("fr_GP", "^(\\+?590|0|00590)[67]\\d{8}$");
        phones.put("fr_MQ", "^(\\+?596|0|00596)[67]\\d{8}$");
        phones.put("fr_PF", "^(\\+?689)?8[789]\\d{6}$");
        phones.put("fr_RE", "^(\\+?262|0|00262)[67]\\d{8}$");
        phones.put("fr_WF", "^(\\+681)?\\d{6}$");
        phones.put("he_IL", "^(\\+972|0)([23489]|5[012345689]|77)[1-9]\\d{6}$");
        phones.put("hu_HU", "^(\\+?36|06)(20|30|31|50|70)\\d{7}$");
        phones.put("id_ID", "^(\\+?62|0)8(1[123456789]|2[1238]|3[1238]|5[12356789]|7[78]|9[56789]|8[123456789])([\\s?|\\d]{5,11})$");
        phones.put("ir_IR", "^(\\+98|0)?9\\d{9}$");
        phones.put("it_IT", "^(\\+?39)?\\s?3\\d{2} ?\\d{6,7}$");
        phones.put("it_SM", "^((\\+378)|(0549)|(\\+390549)|(\\+3780549))?6\\d{5,9}$");
        phones.put("ja_JP", "^(\\+81[ \\-]?(\\(0\\))?|0)[6789]0[ \\-]?\\d{4}[ \\-]?\\d{4}$");
        phones.put("ka_GE", "^(\\+?995)?(79\\d{7}|5\\d{8})$");
        phones.put("kk_KZ", "^(\\+?7|8)?7\\d{9}$");
        phones.put("kl_GL", "^(\\+?299)?\\s?\\d{2}\\s?\\d{2}\\s?\\d{2}$");
        phones.put("ko_KR", "^((\\+?82)[ \\-]?)?0?1([0|1|6|7|8|9]{1})[ \\-]?\\d{3,4}[ \\-]?\\d{4}$");
        phones.put("ky_KG", "^(\\+?7\\s?\\+?7|0)\\s?\\d{2}\\s?\\d{3}\\s?\\d{4}$");
        phones.put("lt_LT", "^(\\+370|8)\\d{8}$");
        phones.put("lv_LV", "^(\\+?371)2\\d{7}$");
        phones.put("mg_MG", "^((\\+?261|0)(2|3)\\d)?\\d{7}$");
        phones.put("mn_MN", "^(\\+|00|011)?976(77|81|88|91|94|95|96|99)\\d{6}$");
        phones.put("my_MM", "^(\\+?959|09|9)(2[5-7]|3[1-2]|4[0-5]|6[6-9]|7[5-9]|9[6-9])[0-9]{7}$");
        phones.put("ms_MY", "^(\\+?60|0)1(([0145](-|\\s)?\\d{7,8})|([236-9](-|\\s)?\\d{7}))$");
        phones.put("mz_MZ", "^(\\+?258)?8[234567]\\d{7}$");
        phones.put("nb_NO", "^(\\+?47)?[49]\\d{7}$");
        phones.put("ne_NP", "^(\\+?977)?9[78]\\d{8}$");
        phones.put("nl_BE", "^(\\+?32|0)4\\d{8}$");
        phones.put("nl_NL", "^(((\\+|00)?31\\(0\\))|((\\+|00)?31)|0)6{1}\\d{8}$");
        phones.put("nl_AW", "^(\\+)?297(56|59|64|73|74|99)\\d{5}$");
        phones.put("nn_NO", "^(\\+?47)?[49]\\d{7}$");
        phones.put("pl_PL", "^(\\+?48)? ?[5-8]\\d ?\\d{3} ?\\d{2} ?\\d{2}$");
        phones.put("pt_BR", "^((\\+?55\\ ?[1-9]{2}\\ ?)|(\\+?55\\ ?\\([1-9]{2}\\)\\ ?)|(0[1-9]{2}\\ ?)|(\\([1-9]{2}\\)\\ ?)|([1-9]{2}\\ ?))((\\d{4}\\-?\\d{4})|(9[1-9]{1}\\d{3}\\-?\\d{4}))$");
        phones.put("pt_PT", "^(\\+?351)?9[1236]\\d{7}$");
        phones.put("pt_AO", "^(\\+244)\\d{9}$");
        phones.put("ro_MD", "^(\\+?373|0)((6(0|1|2|6|7|8|9))|(7(6|7|8|9)))\\d{6}$");
        phones.put("ro_RO", "^(\\+?40|0)\\s?7\\d{2}(\\/|\\s|\\.|-)?\\d{3}(\\s|\\.|-)?\\d{3}$");
        phones.put("ru_RU", "^(\\+?7|8)?9\\d{9}$");
        phones.put("si_LK", "^(?:0|94|\\+94)?(7(0|1|2|4|5|6|7|8)( |-)?)\\d{7}$");
        phones.put("sl_SI", "^(\\+386\\s?|0)(\\d{1}\\s?\\d{3}\\s?\\d{2}\\s?\\d{2}|\\d{2}\\s?\\d{3}\\s?\\d{3})$");
        phones.put("sk_SK", "^(\\+?421)? ?[1-9][0-9]{2} ?[0-9]{3} ?[0-9]{3}$");
        phones.put("so_SO", "^(\\+?252|0)((6[0-9])\\d{7}|(7[1-9])\\d{7})$");
        phones.put("sq_AL", "^(\\+355|0)6[789]\\d{6}$");
        phones.put("sr_RS", "^(\\+3816|06)[- \\d]{5,9}$");
        phones.put("sv_SE", "^(\\+?46|0)[\\s\\-]?7[\\s\\-]?[02369]([\\s\\-]?\\d){7}$");
        phones.put("tg_TJ", "^(\\+?992)?[5][5]\\d{7}$");
        phones.put("th_TH", "^(\\+66|66|0)\\d{9}$");
        phones.put("tr_TR", "^(\\+?90|0)?5\\d{9}$");
        phones.put("tk_TM", "^(\\+993|993|8)\\d{8}$");
        phones.put("uk_UA", "^(\\+?38|8)?0\\d{9}$");
        phones.put("uz_UZ", "^(\\+?998)?(6[125-79]|7[1-69]|88|9\\d)\\d{7}$");
        phones.put("vi_VN", "^((\\+?84)|0)((3([2-9]))|(5([25689]))|(7([0|6-9]))|(8([1-9]))|(9([0-9])))([0-9]{7})$");
        phones.put("zh_CN", "^((\\+|00)86)?(1[3-9]|9[28])\\d{9}$");
        phones.put("zh_TW", "^(\\+?886\\-?|0)?9\\d{8}$");
        phones.put("dz_BT", "^(\\+?975|0)?(17|16|77|02)\\d{6}$");
        phones.put("ar_YE", "^(((\\+|00)9677|0?7)[0137]\\d{7}|((\\+|00)967|0)[1-7]\\d{6})$");
        phones.put("ar_EH", "^(\\+?212|0)[\\s\\-]?(5288|5289)[\\s\\-]?\\d{5}$");
        phones.put("fa_AF", "^(\\+93|0)?(2{1}[0-8]{1}|[3-5]{1}[0-4]{1})(\\d{7})$");

        // aliases
        phones.put("en_CA", phones.get("en_US"));
        phones.put("fr_CA", phones.get("en_CA"));
        phones.put("fr_BE", phones.get("nl_BE"));
        phones.put("zh_HK", phones.get("en_HK"));
        phones.put("zh_MO", phones.get("en_MO"));
        phones.put("ga_IE", phones.get("en_IE"));
        phones.put("fr_CH", phones.get("de_CH"));
        phones.put("it_CH", phones.get("fr_CH"));
    }

    @Override
    public boolean validate(FieldHelper helper, IsMobilePhone annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;

            if (annotation.strictMode() && !str.startsWith("+"))
                return false;

            // if array is empty then all locale will be checked
            if (annotation.locales().length == 0) {
                return this.phones.entrySet().stream().anyMatch(e -> Pattern.compile(e.getValue()).matcher(str).matches());
            }

            return Arrays.stream(annotation.locales()).anyMatch(e -> Pattern.compile(this.phones.get(e.name())).matcher(str).matches());
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsMobilePhone annotation) {
        String message = String.format("'%s' precisa ser um número de telefone", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
