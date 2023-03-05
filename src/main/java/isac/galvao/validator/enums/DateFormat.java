package isac.galvao.validator.enums;

public enum DateFormat {
    /**
     * dd-mm-yyyy (ex: 01-01-2023)
     */
    DDMMYYYY_DASH("dd-MM-yyyy", "-"),

    /**
     * dd_mm_yyyy (ex: 01_01_2023)
     */
    DDMMYYYY_UNDER("dd_MM_yyyy", "_"),

    /**
     * dd/mm/yyyy (ex: 01/01/2023)
     */
    DDMMYYYY_SLASH("dd/MM/yyyy", "/"),

    /**
     * yyyy-mm-dd (ex: 2023-01-01)
     */
    YYYYMMDD_DASH("yyyy-MM-dd", "-"),

    /**
     * yyyy_mm_dd (ex: 2023_01_01)
     */
    YYYYMMDD_UNDER("yyyy_MM_dd", "_"),

    /**
     * yyyy/mm/dd (ex: 2023/01/01)
     */
    YYYYMMDD_SLASH("yyyy/MM/dd", "/"),

    /**
     * mm-dd-yyyy (ex:12-30-2023)
     */
    MMDDYYYY_DASH("MM-dd-yyyy", "-"),

    /**
     * mm_dd_yyyy (ex: 12_30_2023)
     */
    MMDDYYYY_UNDER("MM_dd_yyyy", "_"),

    /**
     * mm/dd/yyyy (ex: 12/30/2023)
     */
    MMDDYYYY_SLASH("MM/dd/yyyy", "/");

    final private String value;

    final private String separator;

    DateFormat(String value, String separator) {
        this.value = value;
        this.separator = separator;
    }

    public String getValue() {
        return this.value;
    }

    public String getSeparator() {
        return this.separator;
    }
}
