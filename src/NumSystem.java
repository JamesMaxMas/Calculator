public enum NumSystem {
    DEC, BIN, OCT, HEX;

    static NumSystem stringToNumSystem(String str) {
        switch (str) {
            case "Dec":
                return DEC;
            case "Bin":
                return BIN;
            case "Oct":
                return OCT;
            case "Hex":
                return HEX;
            default:
                throw new IllegalArgumentException("Unknown operation: " + str);
        }
    }
    public int toInt() {
        switch (this) {
            case DEC : return 10;
            case BIN : return 2;
            case OCT : return 8;
            case HEX : return 16;
            default : return 0;
        }
    }
}
