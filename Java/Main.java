public class Main {
    public static void main(String[] args) {
        System.out.println(listaSolu(4));
    }

    public static SList<Board> listaSolu (int n){
        return listacomp(new Board(n));
    }

    public static SList<Board> listacomp(Board b){
        int n = b.size();
        int q = b.queensOn();
        if (q == n){
            return NULL_BOARDLIST.cons(b);
        }else{
            int i = q + 1;
            SList<Board> list = NULL_BOARDLIST;
            for (int j = 1; j<=n; j++){
                if (!b.underAttack(i,j)){
                    list = list.append(listacomp(b.addQueen(i, j)));
                }
            } return list;
        }
    }

    private static final SList<Board> NULL_BOARDLIST = new SList<Board>();
    
    public static int last(int n){
        RoundTable tr = new RoundTable(n);
        while (tr.numberOfKnights() > 1){
            tr = tr.serveNeighbour();
            tr = tr.passJug();
        }
        return tr.knightWithJug();
    }

    public static IntSList intervallo (int inf, int sup){
        if (inf > sup){
            return new IntSList();
        } else{
            return intervallo(inf+1, sup).cons(inf);
        }
    }
}
