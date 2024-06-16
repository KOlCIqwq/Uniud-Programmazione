package esami.es22_23;


import java.util.Arrays;
import java.util.Stack;

public class G23 {
    public static void main(String[] args) {
        System.out.println(mostFrequentChar( new char[] {'a','b','c','b','c','a','a','d','c','d','c','e','f','f'} ));
        System.out.println(llisMem(new double[]{1, 2, 1, 9, 12}));
        SList<Board> solutions = listOfSolutionsIter(6);
        System.out.println(solutions.toString());
        char c = leastFrequentChar(new char[] {'a','b','c','b','c','a','a','d','c','d','c','e','e','e'});
        System.out.println(c);
        System.out.println(llisMem2(new double[] {3.0, 1.0, 8.0, 2.0, 5.0}));
        System.out.println(numberOfSolutionsIter(10));
    }

    //1.
    public static char mostFrequentChar(char[] charList){
        // If not allowed to use Hashmap, we create a frequency array of 128 chars, 
        //and assign each pointer (letter) to a number (frequency)
        int[] freq = new int[128];

        // +1 to each letter in array
        for (int i = 0; i < charList.length; i++){
            char currentChar = charList[i];
            freq[currentChar]++;
        }

        // loop the input array and on each letter we compare the history maxLength
        //if the letter has a bigger number in freq[], we change the history maxLength to its'
        int maxLength = 0;
        char maxLengthChar = charList[0];
        for (int i = 0; i < charList.length; i++){
            char currentChar = charList[i];
            if (freq[currentChar] > maxLength){
                maxLength = freq[currentChar];
                maxLengthChar = currentChar;
            }
        }
        return maxLengthChar;
    }
    // Other idea would be to compare 2 chars at time and remove the already scanned char (to reduce time)
    //and update the maxLengthChar and maxLength each loop
    /* 
    private char[] removeElement (char[] c,int index){
        char[] copy = new char[c.length - 1];
        for (int i = 0, j = 0; i < c.length; i++){
            if (i != index){
                copy[j++] = c[i];
            }
        }
        return copy;
    }*/

    //2.
    public static int llisMem( double[] s ) {
            int n = s.length;
            double[] v = s.clone();
            Arrays.sort( v );
            int[][] mem = new int[n+1][n+1];
            return llcsRec(0, 0, mem, s, v );
        }
    private static int llcsRec(int i, int j, int[][] mem, double[] s, double[] v ) {
        if (mem[i][j] == UNKNOWN) {
            if((i == mem.length-1)||(j == mem.length-1)){
                return 0;
            } else if (s[i] == v[j]){
                mem[i][j] = 1 + llcsRec(i+1, j+1, mem, s, v);
            } else{
                mem[i][j] = Math.max(llcsRec(i+1, j, mem, s, v),llcsRec(i, j+1, mem, s, v));
            }
        }
        return mem[i][j];
    }
    private static final int UNKNOWN = 0;

    //3.
    public static final SList<Board> NULL_BOARDLIST = new SList<Board>();
    public static SList<Board> listOfSolutionsIter( int n ) {
        Stack<Board> stack = new Stack<Board>();
        stack.push(new Board(n));
        SList<Board> solutions = NULL_BOARDLIST;
        do {
            // Each time the board is the prev stored board in stack
            Board b = stack.pop();
            n = b.size();
            int q = b.queensOn();
            // If there's a solution append the current configuration to solutions
            if ( q == n ) {
                solutions = solutions.cons(b);
            } else {
                // Else we create a new matches, if i,j can be added then we push to stack a new match
                int i = q + 1;
                for (int j = 1; j <= n; j++){
                    if (!b.underAttack(i, j)){
                        stack.push(b.addQueen(i, j));
                    }
                }
            }
        } while (!stack.isEmpty());
        return solutions;
    }

    //4.
    // Moved to Board2.java
    //---------------------------------------------------------------
    //B.

    //1.
    public static char leastFrequentChar (char[] list){
        int[] freq = new int[128];
        for (int i = 0; i < list.length; i++){
            char c = list[i];
            freq[c]++;
        }
        char out = 0;
        int minLen = 100;
        for (int i = 0; i < list.length; i++){
            char current = list[i];
            if(freq[current] < minLen){
                minLen = freq[current];
                out = current;                
            } 
        }
        return out;
    }

    //2.
    public static int llisMem2(double[] s){
        int n = s.length;
        double[] v = s.clone();
        Arrays.sort(v);
        int[][] mem = new int[n+1][n+1];
        for (int i = 0; i <= s.length; i++){
            for (int j = 0; j <= s.length; j++){
                mem[i][j] = -1;
            }
        }
        return llcsRec2(n, n, mem, s, v);
    }

    public static int llcsRec2(int i, int j, int[][]mem, double[]u, double[] v){
        
        if (mem[i][j] == -1){
            if (i == 0 || j == 0){
                mem[i][j] = 0;
            } else if (u[i-1] == v[j-1]){
                mem[i][j] = 1 + llcsRec2(i-1, j-1, mem, u, v);
            } else{
                mem[i][j] = Math.max(llcsRec2(i-1, j, mem, u, v), llcsRec2(i, j-1, mem, u, v));
            }
        }
        return mem[i][j];
    }

    //3.
    public static int numberOfSolutionsIter( int n ) {
        Stack<Board> stack = new Stack<Board>();
        stack.push(new Board(n));
        int count = 0;
        do {
            Board b = stack.pop();
            n = b.size();
            int q = b.queensOn();
            if ( q == n ) {
                count++;
            } else {
                int i = q + 1;
                for (int j = 1; j <= n; j++){
                    if (!b.underAttack(i, j)){
                        stack.push(b.addQueen(i, j));
                    }
                }
            }
        } while (!stack.empty());
        return count;
    }

    //4. 
    //Moved to Board2.java    
}
