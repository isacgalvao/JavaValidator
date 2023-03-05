package isac.galvao.validator;

import java.util.List;

public class ValidationException extends Exception {

    private List<ValidationError> errors;

    public List<ValidationError> getErrors() {
        return errors;
    }

    public ValidationException() {}

    public ValidationException(List<ValidationError> errors) {
        this.errors = errors;
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
