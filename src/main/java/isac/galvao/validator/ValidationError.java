package isac.galvao.validator;

import isac.galvao.validator.util.FieldHelper;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class ValidationError {
    /**
     * Objeto que foi validado.
     */
    private Object target;

    /**
     * Propriedade do objeto que não passou na validação.
     */
    private String property;

    /**
     * Valor que não passou na validação.
     */
    private Object value;

    /**
     * Decorador que causou o erro e a mensagem de erro
     * Map<DecoratorName, Message>
     */
    private Map<String, String> constraints;

    /**
     * Mensagem completa do erro.
     */
    private String message;


    public ValidationError() {
    }

    public ValidationError(FieldHelper helper, String message, Annotation annotation) {
        this.setProperty(helper.getAttributeName());
        this.setTarget(helper.getTargetInstance());
        this.setValue(helper.getValue());
        this.setMessage(message);

        Map<String, String> constraint = new HashMap<>();
        constraint.put(annotation.annotationType().getSimpleName(), message);
        this.setConstraints(constraint);
    }


    @Override
    public String toString() {
        return String.join(",", this.getConstraints().values());
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Map<String, String> getConstraints() {
        return constraints;
    }

    public void setConstraints(Map<String, String> constraints) {
        this.constraints = constraints;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}