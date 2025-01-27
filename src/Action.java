public abstract class Action {
    public abstract long wykonaj(long a, long b, MemSize memSize);
}
class Add extends Action {
    @Override
    public long wykonaj(long a, long b, MemSize memSize) {
        return Operations.add(a, b, memSize);
    }
}

class Sub extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {
        return Operations.subtract(a,b, memSize);
    }
}

class AND extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {
         return Operations.AND(a,b, memSize);
    }
}


class OR extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {
        return Operations.OR(a,b, memSize);
    }
}

class XOR extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {
        return Operations.XOR(a,b, memSize);
    }
}
class MUL extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {

        return Operations.multiply(a,b, memSize);
    }
}

class MOD extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {
        return Operations.mod(a,b, memSize);
    }
}
class DIV extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {
        if(b == 0L){
            return 0L;
        }
        return Operations.divide(a,b, memSize);
    }
}
class LSH extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {
        return Operations.bitShift(Operations.Direction.LEFT,a,b, memSize);
    }
}
class RSH extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {
        return Operations.bitShift(Operations.Direction.RIGHT,a,b, memSize);
    }
}