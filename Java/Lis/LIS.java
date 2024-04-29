package Lis;

// To get the max of a number in an array 
import java.util.Arrays;

public class LIS {
    public static void main(String[] args) {
        System.out.println(lisMem(new int[] {10, 8, 9, 5, 6, 7, 1, 2, 3, 4}));
        System.out.println(lisDP(new int[] {10, 8, 9, 5, 6, 7, 1, 2, 3, 4}));
    }

    public static int lisMem(int[] s){
        int len = s.length;
        int[] mem = new int [len+1];

        for (int k = 0; k <= len; k++){
            mem[k] = -1;
        }
        return lisRec(s,0,0,mem);
    }
    // Need to add some is statement to function
    public static int lisRec(int[] s, int i, int t, int[] mem){
        if (mem[i] == -1){
            if (i == s.length){
                return 0;
            } else if (s[i] <= t){
                mem[i] = lisRec(s, i+1, t, mem);
            } else{
                mem[i] = Math.max(1+lisRec(s, i+1, s[i], mem), lisRec(s, i+1, t, mem));
            }
        }
        return mem[i];
    }
    // Need some fixes between two fors
    public static int lisDP(int[] s){
        int[] Dp = new int [s.length];
        for (int i = s.length - 1; i >= s.length; i--){
            for (int j = i + 1; j >= s.length; j++){
                if (s[i] < s[j]){
                    Dp[i] = Math.max(Dp[i], 1 + Dp[j]);
                }
            }
        }
        return Arrays.stream(Dp).max().getAsInt();
    }
}
