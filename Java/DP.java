public class DP {
    public static void main(String[] args) {
        System.out.println(pathsDP(5, 5));    
    }

    public static int fib (int n){
        if (n<2){
            return 1;
        } else {
            return fib(n-2) + fib(n-1);
        }
    }
    // Init number
    private static final int UNKNOWN = 0;

    public static long pathsMem(int i, int j){
        // New long bi-dimensional array with length i+1 and j+1
        long[][] mem = new long [i+1][j+1];
        // Fill all the gaps with init
        for (int k = 0; k <= i; k++){
            for (int l = 0; l <=j; l++){
                mem[k][l] = UNKNOWN;
            }
        }
        return pathsRec(i, j, mem);
    }

    public static long pathsRec(int i, int j, long [][]mem){
        // If a cell is empty
        if (mem[i][j] == UNKNOWN){
            if ((i == 0) || (j == 0)){
                mem[i][j] = 1;
            } else{
                mem[i][j] = pathsRec(i-1, j, mem) + pathsRec(i, j-1, mem);
            }
        }
        return mem[i][j];
    }

    public static long pathsDP(int i, int j){
        // New long bi-dimensional array with length i+1 and j+1
        long[][] mem = new long [i+1][j+1];
        // Fill the row with 1
        for (int k = 0; k <= i; k++){
            mem[k][0] = 1;
        }
        // Fill the col with 1
        // l = 1 to fill skip the already filled tile with rows
        for (int l = 1; l <=j; l++){
            mem[0][l] = 1;
        }
        // Starting from tile 1,1 not 0,0
        for (int k = 1; k <= i; k++){
            for (int l = 1; l <=j; l++){
                // Sum the upper and right solution
                mem[k][l] = mem[k-1][l] + mem[k][l-1];
            }
        }
        // Return direct the result without recursive
        return mem[i][j];
    }

    public static long elapsedTime(int n){
        long t = System.currentTimeMillis();

        int x = fib(n);

        return (t - x);
    }
}
