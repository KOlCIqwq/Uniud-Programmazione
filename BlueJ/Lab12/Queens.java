 

public class Queens {

  public static void main( String args[] ) {
    //Queens q = new Queens();
		//System.out.println(q.numberOfSolutions(8));
    //System.out.println(listaSolu(8));
  }

  
  public static int numberOfSolutions( int n ) {
    // Init function
    return numberOfCompletions( new Board(n) );
  }

  private static int numberOfCompletions( Board b ) {
  
    int n = b.size();
    int q = b.queensOn();
    
    if ( q == n ) {
      return 1;
    } else {
    
      int i = q + 1;
      int count = 0;
      // try each col for the next row
      for ( int j=1; j<=n; j=j+1 ) {
        if ( !b.underAttack(i,j) ) {
          count = count + numberOfCompletions( b.addQueen(i,j) );
      }}
      return count;
    }
  }


  private static final SList<Board> NULL_BOARDLIST = new SList<Board>();

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
}

