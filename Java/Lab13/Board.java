package Lab13;

import java.util.function.*;

/**
 * Queen
 */
public class Board {
    private static final String ROWS = "123456789ABCDEF";
    private static final String COLS = "abcdefghijk";

          private final int size;
          private final int queens;
          private final BiPredicate<Integer, Integer> attack;
          private final String config;

          private final SList<SList<Integer>> coords;
          
          public Board(int n){
                    size = n;
                    queens = 0;
                    attack = (x,y) -> false;
                    config = " ";

                    coords = new SList<SList<Integer>>();
          }

          private Board(Board b, int i, int j){
                    size = b.size();
                    queens = b.queensOn() + 1;
                    attack = (x, y) -> ((x == i) || (y == j) || (x-y == i-j) || (x+y == i+j) || b.underAttack(x,y));
                    config = " "; //b.arrangment() + COLS.charAt(j) + ROWS.charAt(i) + " ";

                    SList<Integer> pair = (new SList<Integer>().cons(j).cons(i));
                    coords = b.arrangement() + (pair);
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

          public Board addQueen(int i, int j){
                    return new Board(this, i, j);
          }
          public String arrangement(){
                    //return config;
                    return coords;
          }

          public String toString(){
            return "[" + arrangement() + "]";
  }
}