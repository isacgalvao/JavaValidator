package isac.galvao.validator;

import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.interfaces.Validator;

import java.lang.annotation.Annotation;
import java.util.Map;

public class ValidatorFactory {
     public static Validator getClassValidatorInstance() {
         return new ClassValidator();
     }

     public static <T extends Annotation> Validator getClassValidatorInstance(Map<Class<T>, AnnotationValidatorInterface<T>> customAnnotations) {
        return new ClassValidator().addCustomValidators(customAnnotations);
     }
}
