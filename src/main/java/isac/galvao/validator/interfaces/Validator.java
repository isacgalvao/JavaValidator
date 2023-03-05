package isac.galvao.validator.interfaces;


import isac.galvao.validator.ValidationError;
import isac.galvao.validator.ValidationException;

import java.util.List;

public interface Validator {
    List<ValidationError> validate(Object obj);
    void validateOrThrow(Object obj) throws ValidationException;
}
