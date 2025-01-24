public abstract class Action {
    public abstract long wykonaj(long a, long b, MemSize memSize);
}
//todo moze na te akcje wszystkie tety i fajrant
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

class NOT extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {
        return Operations.NOT(a, memSize);
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
        return Operations.divide(a,b, memSize);
    }
}

class ROL extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {
        return Operations.bitShift(Operations.Direction.LEFT,a,  memSize);
    }
}

class ROR extends Action {
    public long wykonaj(long a, long b, MemSize memSize) {
        return Operations.bitShift(Operations.Direction.RIGHT,a,  memSize);
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



//todo reszta akcji też może zwracać bezpośrednio wynik operacji