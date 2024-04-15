/**
 * Queen
 */
public class Board {

          private final int size;
          private final int queens;
          private final BiPredicate<Integer, Integer> attack;
          private final String config;
          public Board(int n){
                    size = n;
                    queens = 0;
                    attack = (x,y) -> false;
                    config = " ";
          }

          private Board(Board b, int i, int j){
                    size = b.size();
                    queens = b.queensOn() + 1;
                    attack = (x, y) -> ((x == i)||(y == j)||(x-y == i-j)||(x+y == i+j)||b.underAttack(x,y));
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
          public String arrangment(){
                    return config;
          }
}