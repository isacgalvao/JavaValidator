package isac.galvao.validator.enums;

public enum HashAlgorithm {
    MD5(32),
    MD4(32),
    SHA1(40),
    SHA256(64),
    SHA384(96),
    SHA512(128),
    RIPEMD128(32),
    RIPEMD160(40),
    TIGER128(32),
    TIGER160(40),
    TIGER192(48),
    CRC32(8),
    CRC32B(8);

    private final int hashLength;

    HashAlgorithm(int length) {
        this.hashLength = length;
    }

    public int getHashLength() {
        return hashLength;
    }
}
