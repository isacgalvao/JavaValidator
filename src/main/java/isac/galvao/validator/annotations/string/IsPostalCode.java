package isac.galvao.validator.annotations.string;

import isac.galvao.validator.locale.LocalePostalCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsPostalCode {
    LocalePostalCode value();
}

