 

import queens.*;

public class Queens {

  public static void main( String args[] ) {
    
    //System.out.println( numberOfSolutions(5) );
    //System.out.println( listOfAllSolutions(10) );
    //ChessboardView gui = new ChessboardView(10);
    //viewQueens(gui, 10);
  }
  public static final SList<Board> NULL_BOARDLIST = new SList<Board>();
  
  public static int numberOfSolutions( int n ) {
    // Init
    return numberOfCompletions( new Board(n) );
  }
  
  public static void viewQueens(int n) {
      ChessboardView gui = new ChessboardView(n);
    // Init
      Conf(gui,new Board(n));
  }

  private static int numberOfCompletions( Board b ) {
    int n = b.size();
    int q = b.queensOn();
    
    if ( q == n ) {
    
      return 1;
    
    } else {
    
      int i = q + 1;
      int count = 0;
      
      for ( int j=1; j<=n; j=j+1 ) {
        if ( !b.underAttack(i,j) ) {
        
          count = count + numberOfCompletions( b.addQueen(i,j) );
      }}
      return count;
    }
  }

  public static SList<Board> listOfAllSolutions( int n ) {
    // Init
    return listOfAllCompletions( new Board(n) );
  }
  
  
  private static SList<Board> listOfAllCompletions( Board b ) {
  
    int n = b.size();
    int q = b.queensOn();
    
    if ( q == n ) {
    
      return ( NULL_BOARDLIST.cons(b) );
    
    } else {
    
      int i = q + 1;
      SList<Board> solutions = NULL_BOARDLIST;
      
      for ( int j=1; j<=n; j=j+1 ) {
        if ( !b.underAttack(i,j) ) {
        
          solutions = solutions.append( listOfAllCompletions(b.addQueen(i,j)) );
      }}
      return solutions;
    }
  }

  private static void Conf(ChessboardView gui, Board b ) {
      
    int n = b.size();
    int q = b.queensOn();
    
    if (n == q){
      gui.setQueens(b.arrangement());
    } else{
      
      int i = q + 1;
      for (int j = 1; j <= n; j++){
        if (!b.underAttack(i, j)){
          // Instead of tracking it as String or List, we just display it on gui
          Conf(gui, b.addQueen(i, j));
        }
      }
    }
}

}

