package isac.galvao.validator;

import isac.galvao.validator.annotations.string.*;
import isac.galvao.validator.interfaces.Validator;
import isac.galvao.validator.locale.Locale;

import java.util.List;

public class Test {

    @IsMultibyte
    public String data = "<>@\" *.";

    public static void main(String[] args) {
        Test testObj = new Test();
        Validator validator = ValidatorFactory.getClassValidatorInstance();
        List<ValidationError> errors = validator.validate(testObj);
        errors.forEach(System.out::println);
        System.out.println("Quantidade de erros: " + errors.size());
    }
}
