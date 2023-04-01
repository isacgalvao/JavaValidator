package isac.galvao.validator;

import isac.galvao.validator.annotations.string.*;
import isac.galvao.validator.interfaces.Validator;

import java.util.List;

public class Test {

    @IsLocale
    public String data = "a-DE";

    public static void main(String[] args) {
        Test testObj = new Test();
        Validator validator = ValidatorFactory.getClassValidatorInstance();
        List<ValidationError> errors = validator.validate(testObj);
        errors.forEach(System.out::println);
        System.out.println("Quantidade de erros: " + errors.size());
    }
}
