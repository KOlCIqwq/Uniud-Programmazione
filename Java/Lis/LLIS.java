// To get the max of a number in an array 
import java.util.Arrays;

public class LLIS {
    public static void main(String[] args) {
        System.out.println(lisMem(new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5}));
        System.out.println(lisGen(new int[] {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3}));
        System.out.println(lisDP(new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1}));
    }

    public static int lisMem(int[] s){
        int len = s.length;
        int[][] mem = new int [len+1][len+1];

        for (int k = 0; k <= len; k++){
            for (int l = 0; l <= len; l++){
                mem[k][l] = -1;
            }
        }
        return lisRec(s,0,0,mem);
    }

    public static int lisRec(int[] s, int i, int t, int[][] mem){ // t as upperbound 
       if (mem[i][t] == -1){
            if (i == s.length){
            return 0;        
            } else if (mem[i][t] != -1){
                return mem[i][t];
            }
             else if (s[i] <= t){ // Check if the value is over the upperbound
                return lisRec(s, i + 1, t, mem);
            } else{
                // If x is choose 1st case, if not second case
                // Give s[i] as upperbound to limit the function
                mem[i][t] = Math.max(1+lisRec(s, i + 1, s[i], mem), lisRec(s, i + 1, t, mem));
            }
       }
        return mem[i][t];
    }

        public static int lisGen(int[] s) {
            int len = s.length;
            int[][] mem = new int[len + 1][len + 1];
            for (int k = 0; k <= len; k++){
                for (int l = 0; l <= len; l++){
                    mem[k][l] = -1;
                }
            }
            return llisRec(s, 0, len, mem);
        }
    
        private static int llisRec(int[] s, int i, int j, int[][] mem) { 
            int t;
                if (j == s.length){
                    t = 0;
                } else{
                    t = s[j - 1];
                }      
                if (i == s.length) { // coda di s vuota
                    return 0;
                } else if (mem[i][j] != -1) {
                    return mem[i][j];
                } else if (s[i] <= t) {
                    return llisRec(s, i + 1, j, mem);
                } else {
                    // Give the 1st case the t as i+1 to jump diagonaly and let the upperbound be as same as i
                    mem[i][j] = Math.max(1+llisRec(s, i + 1, i + 1, mem), llisRec(s, i + 1, j, mem));
                }
            return mem[i][j];
        }
    
    // Using Dynamic Programming
    public static int lisDP(int[] s){
        int[] Dp = new int [s.length+1];
        for (int k = 0; k <= s.length; k++){
            Dp[k] = 1;
        }
        for (int i = 1; i < s.length; i++){
            for (int j = 0; j < i; j++){
                if (s[i] > s[j]){
                    Dp[i] = Math.max(Dp[i], 1 + Dp[j]);
                }
            }
        }
        return Arrays.stream(Dp).max().getAsInt();
    }
}
