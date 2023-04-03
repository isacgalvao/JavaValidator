package isac.galvao.validator.annotations.string;

import isac.galvao.validator.locale.Locale;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsMobilePhone {
    Locale[] locales() default {};
    boolean strictMode() default true;
}

