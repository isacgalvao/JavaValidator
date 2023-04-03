package isac.galvao.validator.annotationvalidators.string;

import isac.galvao.validator.ValidationError;
import isac.galvao.validator.annotations.string.IsMimeType;
import isac.galvao.validator.interfaces.AnnotationValidatorInterface;
import isac.galvao.validator.util.FieldHelper;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Check if the string matches to a valid MIME type format
 */
public class IsMimeTypeValidator implements AnnotationValidatorInterface<IsMimeType> {

    // Match simple MIME types
    // NB :
    //   Subtype length must not exceed 100 characters.
    //   This rule does not comply to the RFC specs (what is the max length ?).
    private final Pattern mimeTypeSimple = Pattern.compile("^(application|audio|font|image|message|model|multipart|text|video)\\/[a-zA-Z0-9\\.\\-\\+_]{1,100}$", Pattern.CASE_INSENSITIVE);

    // Handle "charset" in "text/*"
    private final Pattern mimeTypeText = Pattern.compile("^text\\/[a-zA-Z0-9\\.\\-\\+]{1,100};\\s?charset=(\"[a-zA-Z0-9\\.\\-\\+\\s]{0,70}\"|[a-zA-Z0-9\\.\\-\\+]{0,70})(\\s?\\([a-zA-Z0-9\\.\\-\\+\\s]{1,20}\\))?$", Pattern.CASE_INSENSITIVE);

    // Handle "boundary" in "multipart/*"
    private final Pattern mimeTypeMultipart = Pattern.compile("^multipart\\/[a-zA-Z0-9\\.\\-\\+]{1,100}(;\\s?(boundary|charset)=(\"[a-zA-Z0-9\\.\\-\\+\\s]{0,70}\"|[a-zA-Z0-9\\.\\-\\+]{0,70})(\\s?\\([a-zA-Z0-9\\.\\-\\+\\s]{1,20}\\))?){0,2}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean validate(FieldHelper helper, IsMimeType annotation) {
        Object valor = helper.getValue();

        if (Objects.isNull(valor)) return false;

        if (valor instanceof String) {
            String str = (String) valor;
            return this.isMimeType(str);
        }

        return true;
    }

    @Override
    public ValidationError buildMessage(FieldHelper helper, IsMimeType annotation) {
        String message = String.format("'%s' precisa ser um MIME type", helper.getAttributeName());
        return new ValidationError(helper, message, annotation);
    }

    /*
      Checks if the provided string matches to a correct Media type format (MIME type)
      This function only checks is the string format follows the
      established rules by the according RFC specifications.
      This function supports 'charset' in textual media types
      (https://tools.ietf.org/html/rfc6657).
      This function does not check against all the media types listed
      by the IANA (https://www.iana.org/assignments/media-types/media-types.xhtml)
      because of lightness purposes : it would require to include
      all these MIME types in this library, which would weigh it
      significantly. This kind of effort maybe is not worth for the use that
      this function has in this entire library.
      More information in the RFC specifications :
      - https://tools.ietf.org/html/rfc2045
      - https://tools.ietf.org/html/rfc2046
      - https://tools.ietf.org/html/rfc7231#section-3.1.1.1
      - https://tools.ietf.org/html/rfc7231#section-3.1.1.5
    */
    private boolean isMimeType(String str) {
        return this.mimeTypeSimple.matcher(str).matches() || this.mimeTypeText.matcher(str).matches() || mimeTypeMultipart.matcher(str).matches();
    }
}
