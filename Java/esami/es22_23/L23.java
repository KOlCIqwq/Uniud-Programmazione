package esami.es22_23;

import java.util.Arrays;
import java.util.Stack;

public class L23 {
    public static void main(String[] args) {
        System.out.println(longestContiguousRepeat( new char[] {'b','b','b','b','b','b','c','c','c','c','b','b'} ));
        System.out.println(llisDP(new double[]{1, 2, 1, 9, 12}));
    }

    //2.
    public static char longestContiguousRepeat(char[] list){
        int[] freq = new int[128];
        for (int i = 0; i < list.length-1; i++){
            char current = list[i];
            if(freq[current] == 0 && list[i+1]!=list[i]){
                freq[current]++;
            } else if(list[i+1] == list[i]){
                int counter = 1;
                for (int j = i; j < list.length-1; j++){
                    if (list[j] == list[j+1]){
                        counter++;
                    } else{
                        i = j;
                        break;
                    }
                }
                if (counter > freq[current]){
                    freq[current] = counter;
                }
            } else{
                freq[current] = 1;
            }
        }
        char longestChar = 0;
        int longest = 0;
        for (int i = 0; i < list.length; i++){
            char current = list[i];
            if (longest < freq[current]){
                longest = freq[current];
                longestChar = current;
            }
        }
        return longestChar;
    }

    //3.
    public static int llisDP(double[] s){
        int n = s.length;
        double[] v = s.clone();
        Arrays.sort(v);
        int[][] mem = new int[n+1][n+1];
        for (int k = 0; k <= n; k++){
            mem[n][k] = 0;
            mem[k][n] = 0;
        }
        for (int i = n-1; i >= 0; i--){
            for (int j = n-1; j >= 0; j--){
                if (s[i] == v[j]){
                    mem[i][j] = 1 + mem[i+1][j+1];
                } else{
                    mem[i][j] = Math.max(mem[i+1][j], mem[i][j+1]);
                }
            }
        }
        return mem[0][0];
    }

    //4.
    public static Board queensSolutionIter( int n ) {
        Stack<Board> stack = new Stack<Board>();
        stack.push(new Board(n));
        do {
        Board b = stack.pop();
        n = b.size();
        int q = b.queensOn();
        if ( q == n ) {
            return b;
        } else {
            int i = q + 1;
            for (int k = 0; k < n; k++){
                if (!b.underAttack(i, k)){
                    stack.push(b.addQueen(i, k));
                }
            }
        }
        } while (!stack.isEmpty());
        return null;
    }
}
