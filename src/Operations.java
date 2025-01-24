public  class Operations {

    public enum Direction {
        LEFT, RIGHT
    }

    public static long add(long a, long b, MemSize memSize) {
        switch (memSize) {
            case BYTE:
                return (byte) ((byte) a + (byte) b);
            case WORD:
                return (short) ((short) a + (short) b);
            case DWORD:
                return ((int) a + (int) b);
            default:
                return a + b; // QWORD
        }
    }



    public static long subtract(long a, long b, MemSize memSize) {
        switch (memSize) {
            case BYTE:
                return (byte) ((byte) a - (byte) b);
            case WORD:
                return (short) ((short) a - (short) b);
            case DWORD:
                return ((int) a - (int) b);
            default:
                return a - b; // QWORD
        }
    }

    public static long multiply(long a, long b, MemSize memSize) {
        switch (memSize) {
            case BYTE:
                return (byte) ((byte) a * (byte) b);
            case WORD:
                return (short) ((short) a * (short) b);
            case DWORD:
                return (int) ((int) a * (int) b);
            default:
                return a * b; // QWORD
        }
    }

    public static long divide(long a, long b, MemSize memSize) {
        switch (memSize) {
            case BYTE:

                return (byte) ((byte) a / (byte) b);
            case WORD:
                return (short) ((short) a / (short) b);
            case DWORD:
                return (int) ((int) a / (int) b);
            default:
                return a / b; // QWORD
        }
    }

    public static long changeSign(long a) {
        return -a;
    }

    public static long OR(long a, long b, MemSize memSize) {
        switch (memSize) {
            case BYTE:
                return (byte) ((byte) a | (byte) b);
            case WORD:
                return (short) ((short) a | (short) b);
            case DWORD:
                return (int) ((int) a | (int) b);
            default:
                return a | b; // QWORD
        }
    }

    public static long AND(long a, long b, MemSize memSize) {
        switch (memSize) {
            case BYTE:
                return (byte) ((byte) a & (byte) b);
            case WORD:
                return (short) ((short) a & (short) b);
            case DWORD:
                return (int) ((int) a & (int) b);
            default:
                return a & b; // QWORD
        }
    }

    public static long XOR(long a, long b, MemSize memSize) {
        switch (memSize) {
            case BYTE:
                return (byte) ((byte) a ^ (byte) b);
            case WORD:
                return (short) ((short) a ^ (short) b);
            case DWORD:
                return (int) ((int) a ^ (int) b);
            default:
                return a ^ b; // QWORD
        }
    }

    public static long NOT(long a, MemSize memSize) {
        switch (memSize) {
            case BYTE:
                return (byte) ~((byte) a);
            case WORD:
                return (short) ~((short) a);
            case DWORD:
                return (int) ~((int) a);
            default:
                return ~a; // QWORD
        }
    }

    public static long bitShift(Direction direction, long a, MemSize size) {
        long result = 0;
        if (direction == Direction.LEFT) {
            result = a << 1;
            if ((a & size.mostSignificantBit()) == size.mostSignificantBit()) {
                result |= 1;
            }
        } else {
            result = a >> 1;
            if ((a & 1) == 1) {
                result |= size.mostSignificantBit();
            }
        }
        return result;
    }

    public static long bitShift(Direction direction, long a, long amount, MemSize size) {
        if (amount >= size.toInt()) {
            throw new ArithmeticException("Size ERR!");
        }

        if (direction == Direction.LEFT) {
            return a << amount;
        } else {
            return a >> amount;
        }
    }

    public static long mod(long a, long b, MemSize memSize) {
        switch (memSize) {
            case BYTE:
                return (byte) ((byte) a % (byte) b);
            case WORD:
                return (short) ((short) a % (short) b);
            case DWORD:
                return (int) ((int) a % (int) b);
            default:
                return a % b; // QWORD
        }
    }
}
