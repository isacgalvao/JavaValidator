package isac.galvao.validator.annotations.string;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsFQDN {
    boolean requireTLD() default true;
    boolean allowUnderscores() default false;
    boolean allowTrailingDot() default false;
    boolean allowNumericTLD() default false;
    boolean allowWildcard() default false;
    boolean ignoreMaxLength() default false;
}

