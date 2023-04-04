package isac.galvao.validator.annotations.string;

import isac.galvao.validator.enums.UUIDVersion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsUUID {
    UUIDVersion version() default UUIDVersion.ALL;
}

