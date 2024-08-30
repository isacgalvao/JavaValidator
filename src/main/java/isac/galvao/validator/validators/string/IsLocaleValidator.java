package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsLocale;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is a locale.
 */
public class IsLocaleValidator implements AnnotationValidatorInterface<IsLocale> {
    /*
      = 3ALPHA              ; selected ISO 639 codes
        *2("-" 3ALPHA)      ; permanently reserved
     */
    final String extlang = "([A-Za-z]{3}(-[A-Za-z]{3}){0,2})";

    /*
      = 2*3ALPHA            ; shortest ISO 639 code
        ["-" extlang]       ; sometimes followed by
                            ; extended language subtags
      / 4ALPHA              ; or reserved for future use
      / 5*8ALPHA            ; or registered language subtag
     */
    final String language = "(([a-zA-Z]{2,3}(-%s)?)|([a-zA-Z]{5,8}))".formatted(extlang);

    /*
      = 4ALPHA              ; ISO 15924 code
     */
    final String script = "([A-Za-z]{4})";

    /*
      = 2ALPHA              ; ISO 3166-1 code
      / 3DIGIT              ; UN M.49 code
     */
    final String region = "([A-Za-z]{2}|\\d{3})";

    /*
      = 5*8alphanum         ; registered variants
      / (DIGIT 3alphanum)
     */
    final String variant = "([A-Za-z0-9]{5,8}|(\\d[A-Z-a-z0-9]{3}))";

    /*
      = DIGIT               ; 0 - 9
      / %x41-57             ; A - W
      / %x59-5A             ; Y - Z
      / %x61-77             ; a - w
      / %x79-7A             ; y - z
     */
    final String singleton = "(\\d|[A-W]|[Y-Z]|[a-w]|[y-z])";

    /*
      = singleton 1*("-" (2*8alphanum))
                            ; Single alphanumerics
                            ; "x" reserved for private use
     */
    final String extension = "(%s(-[A-Za-z0-9]{2,8})+)".formatted(singleton);

    /*
      = "x" 1*("-" (1*8alphanum))
     */
    final String privateuse = "(x(-[A-Za-z0-9]{1,8})+)";

    /*
        irregular tags do not match the 'langtag' production and would not
        otherwise be considered 'well-formed'. These tags are all valid, but
        most are deprecated in favor of more modern subtags or subtag combination
     */
    final String irregular = "((en-GB-oed)|(i-ami)|(i-bnn)|(i-default)|(i-enochian)|" +
            "(i-hak)|(i-klingon)|(i-lux)|(i-mingo)|(i-navajo)|(i-pwn)|(i-tao)|" +
            "(i-tay)|(i-tsu)|(sgn-BE-FR)|(sgn-BE-NL)|(sgn-CH-DE))";

    /*
        regular tags match the 'langtag' production, but their subtags are not
        extended language or variant subtags: their meaning is defined by
        their registration and all of these are deprecated in favor of a more
        modern subtag or sequence of subtags
     */
    final String regular = "((art-lojban)|(cel-gaulish)|(no-bok)|(no-nyn)|(zh-guoyu)|(zh-hakka)|(zh-min)|(zh-min-nan)|(zh-xiang))";

    /*
      = irregular           ; non-redundant tags registered
      / regular             ; during the RFC 3066 era
     */
    final String grandfathered = "(%s|%s)".formatted(irregular, regular);

    /*
      RFC 5646 defines delimitation of subtags via a hyphen:
          "Subtag" refers to a specific section of a tag, delimited by a
          hyphen, such as the subtags 'zh', 'Hant', and 'CN' in the tag "zh-
          Hant-CN".  Examples of subtags in this document are enclosed in
          single quotes ('Hant')
      However, we need to add "_" to maintain the existing behaviour.
     */
    final String delimiter = "(-|_)";

    /*
      = language
        ["-" script]
        ["-" region]
        *("-" variant)
        *("-" extension)
        ["-" privateuse]
     */
    final String langtag = "%s(%s%s)?(%s%s)?(%s%s)*(%s%s)*(%s%s)?".formatted(language, delimiter, script, delimiter, region, delimiter, variant, delimiter, extension, delimiter, privateuse);

    /*
      Regex implementation based on BCP RFC 5646
      Tags for Identifying Languages
      https://www.rfc-editor.org/rfc/rfc5646.html
     */
    final Pattern languageTagRegex = Pattern.compile("(^%s$)|(^%s$)|(^%s$)".formatted(privateuse, grandfathered, langtag));


    @Override
    public boolean validate(FieldHelper helper, IsLocale annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            return languageTagRegex.matcher((String) valor).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsLocale annotation) {
        String message = String.format("'%s' precisa ser um locale", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
