// To get the max of a number in an array 
import java.util.Arrays;

public class LLIS {
    public static void main(String[] args) {
        //System.out.println(lisMem(new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5}));
        System.out.println(lisGen(new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 6}));
        System.out.println(lisDP(new int[] {2, 7, 5, 7, 4}));
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

    // t is the prev number of the subsequence
    // Each time it compares the current number, and if the number s[i] is <= t meaning it can't form a increasing subsequence
    // then we jump the the case 
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
                // If the current value s[i] is greater than t meaning it can be a number in the subsequence
                // We try both ways, the first is to add the current number to the subsequence, increasing the length,
                // changing t to let it compare to the next value
                // The second is to skip the number and try next, not changing t because we didn't add new number
                mem[i][t] = Math.max(1+lisRec(s, i + 1, s[i], mem), lisRec(s, i + 1, t, mem));
            }
       }
       // If it reached the end return the last pointed position of matrix
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
    
        // Instead of giving the number and compare it, we use j as a pointer to the number
        private static int llisRec(int[] s, int i, int j, int[][] mem) { 
            // t will be the number
            int t;
            // If the j == len (1st case), the prev number will be 0
                if (j == s.length){
                    t = 0;
                } else{ // Else it will assume the prev number
                    t = s[j];
                }      
                if (i == s.length) {
                    return 0;
                } else if (mem[i][j] != -1) {
                    return mem[i][j];
                } else if (s[i] <= t) {
                    return llisRec(s, i + 1, j, mem);
                } else {
                    // 2 ways, the first picking the number and add 1, changing j to current number pointer, thus moving the pointer
                    // The second is to skip the current and not moving j pointer
                    mem[i][j] = Math.max(1+llisRec(s, i + 1, i, mem), llisRec(s, i + 1, j, mem));
                }
            return mem[i][j];
        }
    
    // Using Dynamic Programming
    public static int lisDP(int[] s){
        int n = s.length;
        int[] Dp = new int [s.length+1];
        for (int k = 0; k <= s.length; k++){
            Dp[k] = 1;
        }
        for (int i = n-1; i>= 0; i--){
            for (int j = i; j < n; j ++){
                if (s[i] < s[j]){
                    Dp[i] = Math.max(Dp[i], 1 + Dp[j]);
                }
            }
        }
        return Arrays.stream(Dp).max().getAsInt();
    }
}
