public enum NumSystem {
    DEC, BIN, OCT, HEX;

    static NumSystem stringToNumSystem(String str) {
        switch (str) {
            case "DEC":
                return DEC;
            case "BIN":
                return BIN;
            case "OCT":
                return OCT;
            case "HEX":
                return HEX;
            default:
                throw new IllegalArgumentException("Unknown operation: " + str);
        }
    }
}
