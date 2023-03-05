package isac.galvao.validator.annotations.string;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsEmail {
    boolean allowDisplayName() default false;
    boolean requireDisplayName() default false;
    boolean allowUtf8LocalPart() default true;
    boolean requireTLD() default true;
    char[] blacklistedChars() default {};
    boolean ignoreMaxLength() default false;
    String[] hostBlackList() default {};
    String[] hostWhiteList() default {};
    boolean domainSpecificValidation() default false;
    boolean allowIpDomain() default false;
}

