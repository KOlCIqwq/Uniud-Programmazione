package Lab12;

//import java.util.function.*;


/**
 * Queen
 */
public class Board {
  // To print out the position
    private static final String ROWS = " 123456789ABCDEF";
    private static final String COLS = " abcdefghijklmno";
          // 7 Variables
          private int size;
          private int queens;
          // private final BiPredicate<Integer, Integer> attack;
          private String config;
          private IntSList COL;
          private IntSList ROW;
          private IntSList DgP;
          private IntSList DgM;

          public Board(int n){
            size = n;
            queens = 0;
            //attack = (x,y) -> false;
            config = "";
            COL = IntSList.NULL_INTLIST;
            ROW = IntSList.NULL_INTLIST;
            DgP = IntSList.NULL_INTLIST;
            DgM = IntSList.NULL_INTLIST;
          }

          private Board(Board b, IntSList r, IntSList c, IntSList dgp, IntSList dgm, String conf){
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
            /*IntSList row = ROW;
            for (int k = 0; k < ROW.length(); k++){
              if(ROW.car() == i){
                return true;
              }
              row = row.cdr();
            } 
            IntSList col = COL;
            for (int k = 0; k < COL.length(); k++){
              if(COL.car() == j){
                return true;
              }
              col = col.cdr();
            } 
            IntSList dgp = DgP;
            for (int k = 0; k < DgP.length(); k++){
              if(DgP.car() == i+j){
                return true;
              }
              dgp = dgp.cdr();
            } 
            IntSList dgm = DgM;
            for (int k = 0; k < DgM.length(); k++){
              if(DgM.car() == i-j){
                return true;
              }
              dgm = dgm.cdr();
            }
            return false; */
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