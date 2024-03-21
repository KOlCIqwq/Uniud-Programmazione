public class MainIntSList {
    public static void main(String[] args) {
        IntSList a = new IntSList(1, null);
        a = a.cons(2);
        
    }

    /* public static IntSList intervallo (int inf, int sup){
        if (inf > sup){
            return new IntSList();
        } else{
            return intervallo(inf+1, sup).cons(inf);
        }
    } */
}
