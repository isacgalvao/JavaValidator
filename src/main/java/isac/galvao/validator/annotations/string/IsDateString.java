package isac.galvao.validator.annotations.string;

import isac.galvao.validator.enums.DateFormat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsDateString {
    DateFormat format() default DateFormat.YYYYMMDD_SLASH;
    boolean withTime() default false;
}

