public class Main {
    public static void main(String[] args) {
        
    }

    public static int numSoluzioni(int n){
        return numcomp(new Board(n));
    }

    public static int numcomp(Board b){
        int n = b.size();
        int q = b.queensOn();
        if (q == n){
            return 1;
        }else{
            int i = q + 1;
            int count = 0;
            for (int j = 1; j==n; j++){
                if (!b.underAttack(i,j)){
                    count = count + numcomp(b.addQueen(i,j));
                }
            } return count;
        }
    }

    public static BoardSList listaSoluzioni(int n){
        return numcomp(new Board(n));
    }

    public static BoardSList listacomp(Board b){
        int n = b.size();
        int q = b.queensOn();
        if (q == n){
            return BoardSList NULL.BOARDLIST.cons(b);
        }else{
            int i = q + 1;
            BoardSList list = BoardSList NULL.BOARDLIST;
            for (int j = 1; j==n; j++){
                if (!b.underAttack(i,j)){
                    list = list.append(listacomp(b.addQueen(i,j)));
                }
            } return list;
        }
    }

    public static int ultimoCavaliere(int n){
        TavolaRotonda tr = new TavolaRotonda(n);
        while (tr.quantiCavalieri() > 1){
            tr = tr.Serve();
            tr = tr.Passa();
        }
        return tr.chihaBrocca();
    }

    public static IntSList intervallo (int inf, int sup){
        if (inf > sup){
            return new IntSList();
        } else{
            return intervallo(inf+1, sup).cons(inf);
        }
    }
}
