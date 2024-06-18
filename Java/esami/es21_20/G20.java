package esami.es21_20;

import java.util.Stack;

public class G20 {
    public static void main(String[] args) {
        System.out.println(rec2(8, 5, 15));
        System.out.println(rec(8, 5, 15));
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
