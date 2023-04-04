package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsSemVer;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string is a Semantic Versioning Specification (SemVer).
 */
public class IsSemVerValidator implements AnnotationValidatorInterface<IsSemVer> {

    /**
     * Regular Expression to match
     * semantic versioning (SemVer)
     * built from multi-line, multi-parts regexp
     * Reference: <a href="https://semver.org/">semver.org</a>
     */
    private final Pattern semanticVersioningRegex = Pattern.compile(
            "^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)" +
                    "(?:-((?:0|[1-9]\\d*|\\d*[a-z-][0-9a-z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-z-][0-9a-z-]*))*))" +
                    "?(?:\\+([0-9a-z-]+(?:\\.[0-9a-z-]+)*))?$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public boolean validate(FieldHelper helper, IsSemVer annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof final String str) {
            return this.semanticVersioningRegex.matcher(str).matches();
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsSemVer annotation) {
        String message = String.format("'%s' precisa ser um Semantic Versioning Specification", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }
}
