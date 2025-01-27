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
    public static long parseSignedBinary(String binary, int base) {
//        int value = Integer.parseInt(binary, n);
//        if (binary.length() == 8 && binary.charAt(0) == '1') {
//            value -= (1 << binary.length()); // Odejmij 2^długość (np. 2^8)
//        }
//        return value;
        long value = Long.parseLong(binary, base);
        int bitLength = binary.length();

        // Jeśli najstarszy bit (MSB) jest ustawiony, liczba jest ujemna w notacji uzupełnienia do dwóch
        if (binary.charAt(0) == '1') {
            // Odejmij 2^bitLength, aby uwzględnić znak
            value -= (1L << bitLength);
        }

        return value;
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
