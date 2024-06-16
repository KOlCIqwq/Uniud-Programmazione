package esami.es22_21;


public class PairStack {
    private SList<Pair> pairs;
    
    public PairStack(){
        pairs = new SList<Pair>();
    }

    public boolean empty(){
        return pairs.isNull();
    }

    public void push (Pair pair){
        pairs.cons(pair);
    }

    public Pair peek(){
        Pair first = pairs.car();
        return first;
    }

    public Pair pop(){
        return (pairs.isNull()) ? pairs.car() : null;
    }
}
