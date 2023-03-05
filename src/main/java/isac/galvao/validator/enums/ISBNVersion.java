package isac.galvao.validator.enums;

public enum ISBNVersion {
    V10(10),
    V13(13),
    ANY(null);

    private final Integer size;

    ISBNVersion(Integer number) {
        this.size = number;
    }

    public Integer getSize() {
        return this.size;
    }
}
