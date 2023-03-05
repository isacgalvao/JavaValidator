package isac.galvao.validator.annotations.date;

import isac.galvao.validator.enums.DateFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MinDate {
    int day();

    int month();

    int year();

    DateFormat format() default DateFormat.DDMMYYYY_SLASH;
}
