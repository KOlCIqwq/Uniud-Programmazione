package esami.es21_20;

import java.util.Stack;

public class G20 {
    public static void main(String[] args) {
        System.out.println(rec2(8, 5, 15));
        System.out.println(rec(8, 5, 15));
        System.err.println(rec4(4, 10));
        System.err.println(rec3(4, 10));
        System.err.println(f(9, 9, 3, 3));
    }

    
    // min x = 1, max = 8
    // min y = 5, max = 12
    // min z = 12, max = 12
    public static long rec2( int x, int y, int z ) { // 1 <= x, y <= z
        if ( (x == 1) || (y == z) ) {
        return 1;
        } else {
        return rec2( x-1, y, z ) + x * rec2( x, y+1, z );
        }
        }
    //2.2
    public static long rec (int x,int y, int z){
        long[][] mem = new long[x+1][z+1];
        for (int i = 0; i <= x; i++){
            mem[i][z] = 1;
        }
        for (int j = 0; j <= y; j++){
            mem[1][j] = 1;
        }
        for (int i = 1; i <= x; i++){
            for (int j = z-1; j >= 1; j--){
                    mem[i][j] = mem[i-1][j] + i * mem[i][j+1];
                
            }
        }
        return mem[x][y];
    }
    public static long rec4( int n, int k ) { // 0 ≤ n ≤ k
        if ( n * (n-k) >= 0 ) {
        return 1;
        } else {
        return rec4( n, k-1 ) + rec4( k-n, k-1 );
        }
        }

    public static long rec3(int n, int k){
        long[][] mem = new long[k-n][k+1];
        for (int j = 0; j <= k; j++) {
            for (int i = 0; i <= n; i++) {
                if (i * (i - j) >= 0) {
                    mem[i][j] = 1;
                }
            }
        }
        for (int j = 1; j <= k; j++){
            for (int i = 0; i <= n; i++){
                if (i*(i-j) < 0){
                    mem[i][j] = mem[i][j-1] + ((j - i >= 0 && j - i <= n) ? mem[j - i][j - 1] : 0);
                }
            }
        }
        return mem[n][k];
    }

    public static int f (int u, int v, int x, int y){
        int[][] mem = new int[x+1][y+1];
        if (u == 0){
            for (int i = 0; i < x; i++){
                mem[i][0] = 0;
            }
        } else{
            for (int i = 0; i < x; i++){
                mem[i][0] = 1;
            }
        }
        if (v == 0){
            for (int i = 0; i < y; i++){
                mem[0][i] = 0;
            }
        } else{
            for (int i = 0; i < y; i++){
                mem[0][i] = 1;
            }
        }
        return frec(u,v,x,y,mem);
    }
    public static int frec(int u, int v, int x, int y, int[][]mem){
        if (x == u && y == v){
            mem[x][y] = 0;
        }else if (y==0 || x==0){
            return mem[0][0];
        } 
        else{
            mem[x][y] = frec(u, v, x-1, y, mem) + frec(u, v, x, y-1, mem);
        }
        return mem[x][y];
    }

    //3.
    public static String hanoiIter(Towers hts, int d){
        int n = hts.height();
        Stack<int[]> stk = new Stack<int[]>();
        stk.push(new int[]{n,d});
        while(!stk.isEmpty()){
            int[] f = stk.pop();
            n = f[0];
            d = f[1];
            if (n > 0){
                if (hts.site(n) == d){
                    stk.push(new int[]{n-1,d});
                } else{
                    int t = hts.transit(n,d);
                    stk.push(new int[]{n-1,t});
                    stk.push(new int[]{-n,d});
                    stk.push(new int[]{n-1,d});
                } 
            } else if(n < 0){
                hts.move(-n, d);
            }
        }
        return hts.moves();
    }
    //3.2
    // n indicates the height of each tower, if it's negative means the disk number -n is being moved
    
    //4. Moved to Towers.java
}
