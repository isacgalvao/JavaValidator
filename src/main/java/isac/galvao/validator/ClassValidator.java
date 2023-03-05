package isac.galvao.validator;

import isac.galvao.validator.annotations.common.IsOptional;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.interfaces.Validator;
import isac.galvao.validator.util.ClassIdentifier;
import isac.galvao.validator.util.FieldHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Esta lib realiza a validação de classes por meio de anotações.
 * Inspirada pela lib typescript 'class-validator'.
 *
 * @author Isac Galvão
 * @version 1.0
 */
public class ClassValidator implements Validator {
    private final Map<Class<? extends Annotation>, AnnotationValidatorInterface<?>> validators = AnnotationValidatorBuilder.build();

    protected ClassValidator() {
    }

    public List<ValidationError> validate(Object obj) {
        return this.execute(obj, null);
    }

    public void validateOrThrow(Object obj) throws ValidationException {
        List<ValidationError> errors = this.execute(obj, null);
        if (errors.size() > 0)
            throw new ValidationException(errors);
    }

    protected <T extends Annotation> ClassValidator addCustomValidators(Map<Class<T>, AnnotationValidatorInterface<T>> customAnnotations) {
        this.validators.putAll(customAnnotations);
        return this;
    }

    private List<ValidationError> execute(Object obj, String superObj) {
        List<ValidationError> errors = new ArrayList<>();
        /*
         * Iterando pelos atributos do objeto
         */
        for (Field field : obj.getClass().getDeclaredFields()) {
            List<ValidationError> fieldErros = new ArrayList<>();
            /*
             * Iterando pelas anotações declaradas no atributo
             */
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                AnnotationValidatorInterface validator = validators.get(annotation.annotationType());
                try {
                    /*
                     * Encerrando a iteração do atributo atual caso o campo esteja anotado com IsOptional
                     * e se o valor for nulo.
                     * Em outras palavras: se o campo estiver anotado com IsOptional, ele não é obrigatório.
                     * Se ele for diferente de nulo, todas as validações serão realizadas, caso contrário
                     * serão ignoradas.
                     */
                    if (annotation.annotationType() == IsOptional.class && Objects.isNull(field.get(obj))) {
                        fieldErros.clear();
                        break;
                    } else {
                        if (Objects.nonNull(validator)) {
                            FieldHelper helper = new FieldHelper(field, obj, superObj);
                            if (!validator.validate(helper, annotation))
                                fieldErros.add(validator.buildMessage(helper, annotation));
                        } else
                            System.out.println("warning: A anotação '%s' não pertence a esta lib".formatted(annotation.annotationType().getSimpleName()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            errors.addAll(fieldErros);

            try {
                /*
                 * Inicia uma recursão caso haja uma classe aninhada que possua anotações desta lib
                 */
                field.setAccessible(true);
                if (!ClassIdentifier.isJavaLang(field.get(obj)))
                    errors.addAll(execute(field.get(obj), field.getName()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return errors;
    }

}