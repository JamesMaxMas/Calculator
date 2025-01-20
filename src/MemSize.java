public enum MemSize {
    BYTE, WORD, DWORD, QWORD;

    public int toInt() {
        switch (this) {
            case BYTE : return 8;
            case WORD : return 16;
            case DWORD : return 32;
            case QWORD : return 64;
        };
        return 0;
    }

    public long mostSignificantBit() {
        switch (this) {
            case BYTE:
                return 0b10000000L;
            case WORD:
                return 0b1000000000000000L;
            case DWORD:
                return 0b10000000000000000000000000000000L;
            case QWORD:
                return 0b1000000000000000000000000000000000000000000000000000000000000000L;
            default:
                throw new IllegalArgumentException();
        }
    }


    static public MemSize stringToMemSize(String str) {
        switch (str) {
            case "Byte":
                return BYTE;
            case "Word":
                return WORD;
            case "Dword":
                return DWORD;
            case "Qword":
                return QWORD;
            default:
                throw new IllegalArgumentException("Unknown operation: " + str);
        }
    }
}
