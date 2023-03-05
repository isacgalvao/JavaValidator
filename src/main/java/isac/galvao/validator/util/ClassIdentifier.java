package isac.galvao.validator.util;

import java.util.Objects;

public class ClassIdentifier {
    /**
     * Verifica se uma dada classe é nativa ou custom
     */
    static public boolean isJavaLang(Object obj) {
        if (Objects.nonNull(obj))
            return obj.getClass().getName().startsWith("java");
        return true;
    }
}
