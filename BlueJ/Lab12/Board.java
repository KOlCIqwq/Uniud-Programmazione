 

//import java.util.function.*;


/**
 * Queen
 */
public class Board extends IntSList {
  // To print out the position
    private static final String ROWS = " 123456789ABCDEF";
    private static final String COLS = " abcdefghijklmno";
          // 7 Variables
          private int size;
          private int queens;
          private String config;
          // Each will memorize the inserted queens and their attack position as list
          private IntSList COL;
          private IntSList ROW;
          private IntSList DgP;
          private IntSList DgM;

          public Board(int n){
            size = n;
            queens = 0;
            config = "";
            COL = IntSList.NULL_INTLIST;
            ROW = IntSList.NULL_INTLIST;
            DgP = IntSList.NULL_INTLIST;
            DgM = IntSList.NULL_INTLIST;
          }

          // Each time we add a queen we need to update the variables
          private Board(Board b, IntSList r, IntSList c, IntSList dgp, IntSList dgm, String conf){
            size = b.size();
            queens = b.queensOn() + 1;
            COL = c;
            ROW = r;
            DgP = dgp;
            DgM = dgm;
            config = conf;
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
            else if((i == row.car()) || (j == col.car()) || (i + j == dgp.car()) || (i - j == dgm.car())){ 
              return true;
            }else{
              // Check next until it ends
              return checkAttack(i, j, col.cdr(), row.cdr(), dgp.cdr(), dgm.cdr());
            }
          }
          

          public Board addQueen(int i, int j){
            // Give the queens position
            String conf = arrangment() + " " + ROWS.charAt(i) + COLS.charAt(j) +" ";
            // Init a new Board containing info. of current board and the new positions
            return new Board(this, ROW.cons(i), COL.cons(j), DgP.cons(i+j), DgM.cons(i-j), conf);
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
            config + ">";
            return s;
  }
}
