package Lab13;

import java.util.function.*;
/**
 * Queen
 * TODO: need to finish the underAttack function
 */
public class Board {
    private static final String ROWS = "123456789ABCDEF";
    private static final String COLS = "abcdefghijk";

          private final int size;
          private final int queens;
          private final BiPredicate<Integer, Integer> attack;
          private String config;
          private SList<SList<Integer>> coords;
          private static final SList<SList<Integer>> NULL_INTLIST = new SList<SList<Integer>>();
          
          public Board(int n){
            size = n;
            queens = 0;
            attack = (x,y) -> false;
            config = "";
            coords = NULL_INTLIST;
          }

          private Board(Board b, int i, int j){
            size = b.size();
            queens = b.queensOn() + 1;
            attack = (x, y) -> ((x == i) || (y == j) || (x-y == i-j) || (x+y == i+j) || b.underAttack(x,y));
            // -1 to fit the position (I don't know why), maybe the array starts with 0 instead of 1
            config = b.arrangement() + COLS.charAt(j-1) + ROWS.charAt(i-1) + " ";
            // Keep track of the current queen position as list
            SList<Integer> pair = (new SList<Integer>().cons(j).cons(i));
            coords = b.coordsA().cons(pair);
          }

          public int size(){
            return size;
          }

          public int queensOn(){
            return queens;
          }

          public boolean underAttack(int i, int j){
            return attack.test(i,j);
          }
          //Need some fix
          /*
          public boolean underAttack(int i, int j){
            for (int k = 0; k < coords.length()-1; k++){
              SList<Integer> pair = coords.listRef(k);
              int col = pair.listRef(1);
              int row = pair.car();
              if ((row == j) || (col == i) || (row+col == i+j) || (col-row == i-j)){
                return true;
              } else {
                continue;
              }
            }
            return false; 
          }
          */

          public Board addQueen(int i, int j){
            
            return new Board(this, i, j);
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
}