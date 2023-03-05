package isac.galvao.validator.annotations.string;

import isac.galvao.validator.locale.LocaleAlpha;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsAlpha {
    LocaleAlpha locale() default LocaleAlpha.en_US;

    /**
     * Valor que será ignorado na verificação.
     * Utilize string ou regex.
     */
    String ignore() default "";
}
