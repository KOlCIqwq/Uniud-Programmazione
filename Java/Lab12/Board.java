package Lab12;

//import java.util.function.*;


/**
 * Queen
 */
public class Board {
  // To print out the position
    private static final String ROWS = " 123456789ABCDEF";
    private static final String COLS = " abcdefghijk";
          // 7 Variables
          private final int size;
          private final int queens;
          // private final BiPredicate<Integer, Integer> attack;
          private String config;
          private final IntSList COL;
          private final IntSList ROW;
          private final IntSList DgP;
          private final IntSList DgM;

          public Board(int n){
            size = n;
            queens = 0;
            //attack = (x,y) -> false;
            config = " ";
            COL = IntSList.NULL_INTLIST;
            ROW = IntSList.NULL_INTLIST;
            DgP = IntSList.NULL_INTLIST;
            DgM = IntSList.NULL_INTLIST;
          }

          private Board(Board b, IntSList c, IntSList r, IntSList dgp, IntSList dgm, String conf){
            size = b.size();
            queens = b.queensOn() + 1;
            COL = c;
            ROW = r;
            DgP = dgp;
            DgM = dgm;
            config = conf;
            //attack = (x, y) -> ((x == i) || (y == j) || (x-y == i-j) || (x+y == i+j) || b.underAttack(x,y));
            //config = b.arrangment() + COLS.charAt(j) + ROWS.charAt(i) + " ";
          }

          public int size(){
            return size;
          }

          public int queensOn(){
            return queens;
          }

          public boolean underAttack(int i, int j){
            // Start a recursive function
            return checkAttack(i, j, COL, ROW, DgP, DgM);
          }

          public boolean checkAttack(int i, int j, IntSList col, IntSList row, IntSList dgp, IntSList dgm){
            if (col.isNull() && row.isNull() && dgp.isNull() && dgm.isNull()){ // Base case
              return false;
            } 
            // Check row, col, diagonals, if they're under attack
            else if((col.car() == i) || (row.car() == j) || (dgp.car() == i + j) || (dgm.car() == i - j)){ 
              return true;
            }else{
              // Check next until it ends
              return checkAttack(i, j, col.cdr(), row.cdr(), dgp.cdr(), dgm.cdr());
            }
          }

          public Board addQueen(int i, int j){
            // Give the queens position
            config = config + " " + COLS.charAt(i) + ROWS.charAt(j) +" ";
            return new Board(this, COL.cons(i), ROW.cons(j), DgP.cons(i+j), DgM.cons(i-j), config);
          }
          public String arrangment(){
            return config;
          }

          public String toString(){
            // Give full info about the match
            String s = "< " + 
            size + ", " + 
            queensOn() + "," + 
            ROW.reverse() + "," + 
            COL.reverse() + "," + 
            DgP.reverse() + "," + 
            DgM.reverse() + "," + 
            arrangment() + ">";
            return s;
  }
}