package isac.galvao.validator.validators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsEmail;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class IsEmailValidator implements AnnotationValidatorInterface<IsEmail> {

    private final Pattern splitNameAddress = Pattern.compile("^([^\\x00-\\x1F\\x7F-\\x9F\\cX]+)<", Pattern.CASE_INSENSITIVE);
    private final Pattern emailUserPart = Pattern.compile("^[a-z\\d!#\\$%&'\\*\\+\\-\\/=\\?\\^_`\\{\\|\\}~]+$", Pattern.CASE_INSENSITIVE);
    private final Pattern gmailUserPart = Pattern.compile("^[a-z\\d]+$");
    private final Pattern quotedEmailUser = Pattern.compile("^([\\s\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f\\x21\\x23-\\x5b\\x5d-\\x7e]|(\\\\[\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]))*$", Pattern.CASE_INSENSITIVE);
    private final Pattern emailUserUtf8Part = Pattern.compile("^[a-z\\d!#\\$%&'\\*\\+\\-\\/=\\?\\^_`\\{\\|\\}~\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]+$", Pattern.CASE_INSENSITIVE);
    private final Pattern quotedEmailUserUtf8 = Pattern.compile("^([\\s\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f\\x21\\x23-\\x5b\\x5d-\\x7e\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]|(\\\\[\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))*$", Pattern.CASE_INSENSITIVE);
    private final int defaultMaxEmailLength = 254;

    @Override
    public boolean validate(FieldHelper helper, IsEmail annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            if (annotation.requireDisplayName() || annotation.allowDisplayName()) {
                final String[] displayEmail = (String[]) splitNameAddress.matcher(str).results().toArray();
                if (displayEmail.length > 0) {
                    String displayName = displayEmail[1];

                    // Remove display name and angle brackets to get email address
                    // Can be done in the regex but will introduce a ReDOS
                    str = str.replace(displayName, "").replaceAll("(^<|>$)", "");

                    // sometimes need to trim the last space to get the display name
                    // because there may be a space between display name and email address
                    // e.g. myname <address@gmail.com>
                    // the display name is `myname` instead of `myname `, so need to trim the last space
                    if (displayName.endsWith(" ")) displayName = displayName.substring(0, displayEmail.length - 1);

                    if (!validateDisplayName(displayName)) return false;
                } else if (annotation.requireDisplayName()) return false;
            }
            if (!annotation.ignoreMaxLength() && str.length() > defaultMaxEmailLength) return false;

            final List<String> parts = new ArrayList<>(List.of(str.split("@")));
            final String domain = parts.get(parts.size() - 1);
            parts.remove(domain);
            final String lowerDomain = domain.toLowerCase();

            final List<String> blackList = new ArrayList<>(List.of(annotation.hostBlackList()));
            final List<String> whiteList = new ArrayList<>(List.of(annotation.hostWhiteList()));

            if (blackList.contains(lowerDomain)) return false;

            if (!whiteList.isEmpty() && !whiteList.contains(lowerDomain)) return false;

            String user = String.join("@", parts);

            if (annotation.domainSpecificValidation() && (lowerDomain.equals("gmail.com") || lowerDomain.equals("googlemail.com"))) {
                user = user.toLowerCase();

                // Removing sub-address from username before gmail validation
                final String username = user.split("\\+")[0];

                // Dots are not included in gmail length restriction
                if (!isByteLength(username.replaceAll("\\.", ""), 6, 30)) return false;

                final String[] userParts = username.split("\\.");
                for (String userPart : userParts)
                    if (!gmailUserPart.matcher(userPart).matches())
                        return false;
            }

            if (!annotation.ignoreMaxLength() && (!isByteLength(user, 0, 30) || !isByteLength(domain, 0, 254)))
                return false;

            if (!isFQDN(domain, annotation.requireTLD(), annotation.ignoreMaxLength())) {
                if (!annotation.allowIpDomain()) return false;

                if (!isIP(domain, "")) {
                    if (!domain.startsWith("[") || !domain.endsWith("]")) return false;

                    String noBracketDomain = domain.substring(1, domain.length() - 1);

                    if (noBracketDomain.length() == 0 || !isIP(noBracketDomain, "")) return false;
                }

            }

            if (user.charAt(0) == '"') {
                user = user.substring(1, user.length() - 1);
                return annotation.allowUtf8LocalPart() ?
                        quotedEmailUserUtf8.matcher(user).matches() :
                        quotedEmailUser.matcher(user).matches();
            }

            final Pattern pattern = annotation.allowUtf8LocalPart() ? emailUserUtf8Part : emailUserPart;

            final String[] userParts = user.split("\\.");
            for (String userPart : userParts)
                if (!pattern.matcher(userPart).matches())
                    return false;

            if (annotation.blacklistedChars().length > 0)
                return !user.matches("%s+".formatted(Arrays.toString(annotation.blacklistedChars())));

            return true;
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsEmail annotation) {
        String message = String.format("'%s' precisa ser um email válido", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }

    //TODO: after structural refactor, use IsIPValidator instead
    private boolean isIP(String str, String version) {

        final String IPv4SegmentFormat = "(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
        final String IPv4AddressFormat = "(%s[.]){3}%s".formatted(IPv4SegmentFormat, IPv4SegmentFormat);
        final Pattern IPv4AddressRegExp = Pattern.compile("^%s$".formatted(IPv4AddressFormat));

        final String IPv6SegmentFormat = "(?:[0-9a-fA-F]{1,4})";
        final Pattern IPv6AddressRegExp = Pattern.compile("^(" +
                "(?:"+ IPv6SegmentFormat +":){7}(?:"+ IPv6SegmentFormat +"|:)|" +
                "(?:"+ IPv6SegmentFormat +":){6}(?:"+ IPv4AddressFormat +"|:"+ IPv6SegmentFormat +"|:)|" +
                "(?:"+ IPv6SegmentFormat +":){5}(?::"+ IPv4AddressFormat +"|(:"+ IPv6SegmentFormat +"){1,2}|:)|" +
                "(?:"+ IPv6SegmentFormat +":){4}(?:(:"+ IPv6SegmentFormat +"){0,1}:"+ IPv4AddressFormat +"|(:"+ IPv6SegmentFormat +"){1,3}|:)|" +
                "(?:"+ IPv6SegmentFormat +":){3}(?:(:"+ IPv6SegmentFormat +"){0,2}:"+ IPv4AddressFormat +"|(:"+ IPv6SegmentFormat +"){1,4}|:)|" +
                "(?:"+ IPv6SegmentFormat +":){2}(?:(:"+ IPv6SegmentFormat +"){0,3}:"+ IPv4AddressFormat +"|(:"+ IPv6SegmentFormat +"){1,5}|:)|" +
                "(?:"+ IPv6SegmentFormat +":){1}(?:(:"+ IPv6SegmentFormat +"){0,4}:"+ IPv4AddressFormat +"|(:"+ IPv6SegmentFormat +"){1,6}|:)|" +
                "(?::((?::"+ IPv6SegmentFormat +"){0,5}:"+ IPv4AddressFormat +"|(?::"+ IPv6SegmentFormat +"){1,7}|:))" +
                ")(%[0-9a-zA-Z-.:]{1,})?$");

        if (version.isBlank()) {
            return isIP(str, "4") || isIP(str, "6");
        }
        if (version.equals("4")) return IPv4AddressRegExp.matcher(str).matches();

        if (version.equals("6")) return IPv6AddressRegExp.matcher(str).matches();

        return false;
    }

    //TODO: after structural refactor, use IsFQDNValidator instead
    private boolean isFQDN(String str, boolean requiredTLD, boolean ignoreMaxLength) {
        final boolean allow_underscores = false;
        final boolean allow_trailing_dot = false;
        final boolean allow_numeric_tld = false;
        final boolean allow_wildcard = false;

        /* Remove the optional trailing dot before checking validity */
        if (allow_trailing_dot && str.charAt(str.length() - 1) == '.') {
            str = str.substring(0, str.length() - 1);
        }

        /* Remove the optional wildcard before checking validity */
        if (allow_wildcard == true && str.indexOf("*.") == 0) {
            str = str.substring(2);
        }

        final String[] parts = str.split("\\.");
        final String tld = parts[parts.length-1];

        if (requiredTLD) {
            // disallow fqdns without tld
            if (parts.length < 2) return false;

            Pattern pattern = Pattern.compile("^([a-z\\u00A1-\\u00A8\\u00AA-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]{2,}|xn[a-z0-9-]{2,})$", Pattern.CASE_INSENSITIVE);
            if (allow_numeric_tld && pattern.matcher(tld).matches()) return false;

            if (tld.matches("\\s")) return false;
        }
        // reject numeric TLDs
        if (!allow_numeric_tld && tld.matches("^\\d+$")) return false;

        return Arrays.stream(parts).allMatch((part) -> {
            if (part.length() > 63 && !ignoreMaxLength)
                return false;

            if (!part.matches("^[a-z_\\u00a1-\\uffff0-9-]+$")) return false;

            // disallow full-width chars
            if (Pattern.compile("[\\uff01-\\uff5e]").matcher(part).find()) return false;

            // disallow parts starting or ending with hyphen
            if (part.matches("^-|-$")) return false;

            if (!allow_underscores && part.matches("_")) return false;

            return true;
        });
    }

    // TODO: after structural refactor, use IsByteLengthValidator instead
    private boolean isByteLength(String str, int min, int max) {
        final byte[] stringBytes = str.getBytes(StandardCharsets.UTF_8);
        return stringBytes.length >= min && stringBytes.length <= max;
    }

    /**
     * Validate display name according to the <a href="https://tools.ietf.org/html/rfc2822#appendix-A.1.2">RFC2822</a>.
     */
    private boolean validateDisplayName(String displayName) {
        final String displayNameWithoutQuotes = displayName.replaceAll("^\"(.+)\"$", "$1");

        // display name with only spaces is not valid
        if (displayName.isBlank()) return false;

        // check whether display name contains illegal character
        final boolean containsIllegal = Pattern.compile("[\\.\";<>]").matcher(displayNameWithoutQuotes).matches();
        if (containsIllegal) {
            // if contains illegal characters,
            // must be enclosed in double-quotes, otherwise it's not a valid display name
            if (displayNameWithoutQuotes.equals(displayName)) return false;

            // the quotes in display name must start with character symbol \
            return displayNameWithoutQuotes.split("\"").length == displayNameWithoutQuotes.split("\\\\\"").length;
        }
        return true;
    }
}
