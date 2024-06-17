package esami.es20_19_18;

import java.util.Stack;

public class G19_18 {
    public static void main(String[] args) {
        String[] result = diff("asdf", "qwsa");
        System.out.println(result[0] + " "+ result[1]);
        System.out.println(stIter(5, 3));
        int[] s = {3, 1, 2, 4, 5};
        System.out.println(q(s));
    }

    public static String[] diff(String u, String v){
        int n = u.length();
        int m = v.length();
        String[][][] mem = new String[n+1][m+1][2];

        return diffRec(u,v,n,m,mem);
    }

    public static String[] diffRec(String u, String v, int n, int m, String[][][] mem){
        String[] result;
        if (m == 0 || n == 0){
            result = new String[]{u,v};
        } else if(u.charAt(n-1) == v.charAt(m-1)){
            result = diffRec(u.substring(0,n-1), v.substring(0,m-1), n-1,m-1,mem);
        } else{
            String[] x = diffRec(u.substring(0, n-1), v, n-1, m, mem);
            String[] y = diffRec(u, v.substring(0, m-1), n, m-1, mem);

            if (x[0].length() < y[0].length()){
                result = new String[]{u.charAt(n-1)+x[0], x[1]};
            } else{
                result = new String[]{y[0], v.charAt(m-1)+y[1]};
            }
        }
        mem[n][m][0] = result[0];
        mem[n][m][1] = result[1];

        return new String[]{mem[n][m][0], mem[n][m][1]};
    }

    public static long stIter (int n, int k){
        long[] ct = new long[]{0};
        Stack<int[]> stack = new Stack<int[]>();

        int[] f = new int[]{n,k,1};
        stack.push(f);
        while(!stack.isEmpty()){
            f = stack.pop();
            if (f[1] == 1 || f[1] == f[0]){
                ct[0] = ct[0] + f[2];
            } else{
                stack.push(new int[]{f[0]-1, f[1]-1, f[2]});
                stack.push(new int[]{f[0]-1, f[1], f[1]*f[2]});
            }
        }
        return ct[0];
    }

    public static int q( int[] s ) { // s.length > 0
        int n = s.length;
        int[] t = new int[ n ]; t[0] = s[0];
        for ( int k=1; k<n; k=k+1 ) {
            int i=k-1;
            while ( (i >= 0) && (t[i] > s[k]) ) {
                t[i+1] = t[i]; i = i - 1;
            }
            t[i+1] = s[k];
        }
        int[][] mem = new int[n+1][n+1];
        return qRec(s, t, n, 0, 0, mem);
    }
    
    public static int qRec(int[] s, int[] t, int n, int i, int j, int[][] mem){
        if ((i == n || j == n)){
            mem[i][j] = 0;
        }else if (s[i] == t[j]){
            mem[i][j] = 1 + qRec(s, t, n, i+1, j+1, mem);
        } else{
            mem[i][j] = Math.max(qRec(s, t, n, i+1, j, mem), qRec(s, t, n, i, j+1, mem));
        }

        return mem[i][j];
    }
}
