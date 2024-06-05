package esercitazione;

public class Board {
    private static final String ROWS = " 123456789ABCDEF";
    private static final String COLS = " abcdefghijk";

          private final int size;
          private final int queens;
          private String config;
          private SList<SList<Integer>> coords;
          private static final SList<SList<Integer>> NULL_INTLIST = new SList<SList<Integer>>();
          
          public Board(int n){
            size = n;
            queens = 0;
            config = "";
            coords = NULL_INTLIST;
          }

          private Board(Board b, int i, int j, boolean remove){
            if (remove == true){
              size = b.size();
              queens = b.queensOn() + 1;
              config = b.arrangement() + COLS.charAt(j) + ROWS.charAt(i) + " ";
              // Keep track of the current queen position as list
              SList<Integer> pair = (new SList<Integer>().cons(j).cons(i));
              coords = b.coordsA().cons(pair);
            }else{
              size = b.size();
              queens = b.queensOn() - 1;
              config = b.arrangement().substring(0,2);
              coords = b.coordsA().cdr();
            }
            
          }

          public int size(){
            return size;
          }

          public int queensOn(){
            return queens;
          }
          
          public boolean underAttack(int i, int j){
            for (int k = 0; k < coords.length(); k++){
              SList<Integer> pair = coords.listRef(k);
              
              int v = pair.cdr().car();
              int u = pair.car();
              if ((u == i) || (v == j) || (i-j == u-v) || (i+j == u+v)){
                return true;
              }
            }
            return false; 
          }
          

          public Board addQueen(int i, int j){
            return new Board(this, i, j, false);
          }

          public String arrangement(){
            return config;
          }
          public SList<SList<Integer>> coordsA(){
            return coords;
          }

          public String toString(){
            String s = "< " + 
            size + ", " + 
            queensOn() + "," + 
            coords + "," +
            config + ">";
            return s;
  }

  public boolean isFreeRow(int i){
    for (int k = 0; k < coords.length(); k++){
      SList<Integer> pair = coords.listRef(k);
      int row = pair.cdr().car();
      if (i == row){
        return false;
      }
    }
    return true;
  } 

  public void addQueenAsString(String pos){
    int[] position = returnpos(pos);
    int i = position[0];
    int j = position[1];
    addQueen(i, j);
  }
  public int[] returnpos(String pos){
    char col = pos.charAt(0);
    char row = pos.charAt(1);
    int[] position = new int[2];
    for (int k = 0; k < ROWS.length(); k++){
      if (row == ROWS.charAt(k)){
        position[0] = k;
      }
    }
    for (int l = 0; l < COLS.length(); l++){
      if (col == COLS.charAt(l)){
        position[1] = l;
      }
    }
    return position;
  }


  public Board removeQueen(int i, int j){
    return new Board(this, i ,j, true);
  }
}