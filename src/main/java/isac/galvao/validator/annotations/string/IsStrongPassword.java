package isac.galvao.validator.annotations.string;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsStrongPassword {
    int minLength() default 8;
    int minLowercase() default 1;
    int minUppercase() default 1;
    int minNumbers() default 1;
    int minSymbols() default 1;
}

