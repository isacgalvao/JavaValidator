package isac.galvao.validator;

import isac.galvao.validator.annotations.common.ValidateNested;
import isac.galvao.validator.annotations.string.*;
import isac.galvao.validator.interfaces.Validator;

import java.util.List;
import java.util.regex.Pattern;

public class Test {
    @NotContains(value = "teucu", ignoreCase = true)
    public String data = "Teucu";

    public static void main(String[] args) {
        Test testObj = new Test();
        Validator validator = ValidatorFactory.getClassValidatorInstance();
        List<ValidationError> errors = validator.validate(testObj);
        errors.forEach(System.out::println);
        System.out.println("Quantidade de erros: " + errors.size());
    }
}
