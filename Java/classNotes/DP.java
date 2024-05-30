public class DP {
    public static void main(String[] args) {
        System.out.println(llcsMem("arto", "atrio")); 
        System.out.println(lcsDP("arto", "atrio"));   
    }

    // Normal rec
    public static int fib (int n){
        if (n<2){
            return 1;
        } else {
            return fib(n-2) + fib(n-1);
        }
    }

    // DP remember the old paths
    // Init number
    private static final int UNKNOWN = -1;

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
        // If a cell is -1
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

    public static int llcsMem(String u, String v){
        int i = u.length();
        int j = v.length();
        // New long bi-dimensional array with length i+1 and j+1
        int[][] mem = new int [i+1][j+1];
        // Fill all the gaps with init
        for (int k = 0; k <= i; k++){
            for (int l = 0; l <=j; l++){
                mem[k][l] = UNKNOWN;
            }
        }
        return llcsRec(u, v, mem);
    }


    private static int llcsRec(String u, String v, int [][]mem){
        int i = u.length();
        int j = v.length();
        if (mem[i][j] == UNKNOWN){
            if ((i == 0)||(j==0)){
                // Empty strings
                mem[i][j] = 0;
            } else if (u.charAt(0) == v.charAt(0)){
                // Found 1 equal char
                mem[i][j] = 1 + llcsRec(u.substring(1), v.substring(1), mem);
            } else{
                // Try the longest by subtracking first of each in different path
                mem[i][j] = Math.max(llcsRec(u.substring(1), v, mem), llcsRec(u, v.substring(1), mem));
            }
        }
        return mem[i][j];
    }
    public static long llcsDP(String u, String v){
        int i = u.length();
        int j = v.length();
        // New long bi-dimensional array with length i+1 and j+1
        long[][] mem = new long [i+1][j+1];
        // Fill the row with 0
        for (int k = 0; k <= i; k++){
            mem[k][0] = 0;
        }
        // Fill the col with 0
        // l = 1 to fill skip the already filled tile with rows
        for (int l = 1; l <=j; l++){
            mem[0][l] = 0;
        }
        // Starting from tile 1,1 not 0,0
        for (int k = 1; k <= i; k++){
            for (int l = 1; l <=j; l++){
                if (u.charAt(i-k) == v.charAt(j-l)){
                    // if equal then let the row and col be +1
                    mem[k][l] = 1 + mem[k-1][l-1];
                } else{
                    // Get best result of two solution
                    mem[k][l] = Math.max(mem[k-1][l],  mem[k][l-1]);
                }
            }
        }
        // Return direct the result without recursive
        return mem[i][j];
    }

    public static String lcsDP(String u, String v){
        int i = u.length();
        int j = v.length();
        // New long bi-dimensional array with length i+1 and j+1
        int[][] mem = new int [i+1][j+1];
        // Fill the row with 0
        for (int k = 0; k <= i; k++){
            mem[k][0] = 0;
        }
        // Fill the col with 0
        // l = 1 to fill skip the already filled tile with rows
        for (int l = 1; l <=j; l++){
            mem[0][l] = 0;
        }
        // Starting from tile 1,1 not 0,0
        for (int k = 1; k <= i; k++){
            for (int l = 1; l <=j; l++){
                if (u.charAt(i-k) == v.charAt(j-l)){
                    // if equal then let the row and col be +1
                    mem[k][l] = 1 + mem[k-1][l-1];
                } else{
                    // Get best result of two solution
                    mem[k][l] = Math.max(mem[k-1][l],  mem[k][l-1]);
                }
            }
        }
        //int solution = mem[i][j];

        String s = "";

        int k = i;
        int l = j;

        while (mem[k][l] > 0){
            if (u.charAt(i-k) == v.charAt(j-l)){
                s = s + u.charAt(i-k);
                k--;
                l--;
            } else if (mem[k-1][l] < mem [k][l-1]){
                l--;
            }else if (mem[k-1][l] > mem [k][l-1]){
                k--;
            } else{
                l--;
            }
        }
        return s;
    }

    public static long elapsedTime(int n){
        long t = System.currentTimeMillis();

        // Function
        int x = fib(n);

        return (t - x);
    }
}
