public class IntSList{
    // final == tuple, rimane una costante

    private final boolean empty;

    private final int first;

    private final IntSList rest;
    
    public IntSList(){
              empty = true;
              // Non serviva (Lo faceva java)
              first = 0;
              rest = null;
    }
    public IntSList(int e, IntSList r){
              empty = false;
              first = e;
              rest = r;
    }
    public boolean isNull(){
              return empty;
    }
    public int car(){
              return first;
    }
    public IntSList cdr(){
              return rest;
    }
    public IntSList cons(int e){
              // this cambia dinamicamente 
              return new IntSList(e, this);
    }
}