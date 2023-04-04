package isac.galvao.validator;

import isac.galvao.validator.annotations.string.IsSurrogatePair;
import isac.galvao.validator.annotations.string.IsTimeZone;
import isac.galvao.validator.annotations.string.IsUUID;
import isac.galvao.validator.annotations.string.IsUppercase;
import isac.galvao.validator.enums.UUIDVersion;
import isac.galvao.validator.interfaces.Validator;

import java.util.List;

public class Test {


    public String data = "A987FBC9-4BED-2078-CF07-9141BA07C9F3";

    public static void main(String[] args) {
        Test testObj = new Test();
        Validator validator = ValidatorFactory.getClassValidatorInstance();
        List<ValidationError> errors = validator.validate(testObj);
        errors.forEach(System.out::println);
        System.out.println("Quantidade de erros: " + errors.size());
    }
}
