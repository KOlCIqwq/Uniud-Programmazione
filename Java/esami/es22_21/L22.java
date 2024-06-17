package esami.es22_21;

import java.util.Arrays;

public class L22 {
    public static void main(String[] args) {
        double[] heap = {5.0, 3.1, 5.7, 3.1, 8.5, 6.0, 3.0, 4.2, 9.3};
        System.out.println(Arrays.toString(heapTest(heap)));
        double[] s = {5.0, 3.1, 5.7, 3.1, 8.5, 6.0, 3.8, 4.2, 9.3};
        System.out.println("Length of LLDS is: " + llds(s));
    }
    //1.
    public static int[] heapTest(double[] list){
        for (int i = 1; i < list.length/2; i++){
            int left = 2 * i;
            int right = left + 1;
            if (list[i] > list[left]){
                return new int[] {i,left};
            }
            if (list[i] > list[right] && right < list.length){
                return new int[] {i, right};
            }
        }
        return null;
    }

    //2.
    public static int llds(double[] s){
        int n = s.length;
        int[][][] mem = new int[n+1][n+1][n+1];
        for (int i = 0; i <= n; i++){
            for(int j = 0; j <=n; j++){
                mem[n][i][j] = 0;
            }
        }
        for (int k = n-1; k >= 1; k--){
            for(int i = k-1; i >= 1; i--){
                for (int j = i-1; j >= 1; j--){
                    if (i == 0 || (Math.min(s[i], s[j]) < s[k] && s[k] < Math.max(s[i], s[j]))){
                        mem[k][i+1][j+1] = Math.max(1 + mem[k+1][j][k], mem[k+1][i][j]);
                    } else{
                        mem[k][i+1][j+1] = mem[k+1][i][j];
                    }
                }
            }
        }
        return mem[0][0][0];
    }
}

