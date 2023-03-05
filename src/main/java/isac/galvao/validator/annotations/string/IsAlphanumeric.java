package isac.galvao.validator.annotations.string;

import isac.galvao.validator.locale.LocaleAlphanumeric;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsAlphanumeric {
    LocaleAlphanumeric locale() default LocaleAlphanumeric.en_US;
    String ignore() default "";
}

