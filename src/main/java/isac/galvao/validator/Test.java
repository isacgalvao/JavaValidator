package isac.galvao.validator;

import isac.galvao.validator.annotations.string.IsISBN;
import isac.galvao.validator.annotations.string.IsISIN;
import isac.galvao.validator.interfaces.Validator;

import java.util.List;

public class Test {

    @IsISIN
    public String data = "US9311421039";

    public static void main(String[] args) {
        Test testObj = new Test();
        Validator validator = ValidatorFactory.getClassValidatorInstance();
        List<ValidationError> errors = validator.validate(testObj);
        errors.forEach(System.out::println);
        System.out.println("Quantidade de erros: " + errors.size());
    }
}
