package esami;

public class Esame24B {
    public static void main(String[] args) {
        System.err.println(lec(5, 3));
        System.err.println(lec2(5, 3));
        System.err.println(midval(new int[]{10,1,1,1,1,1,1,15,1}));
    }
    public static int midval(int[] list){
        int total = 0;
        for (int i = 0; i < list.length; i++){
            total = total + list[i];
        }
        if (total%2!=0){
            return 0;
        } else{
            int half = total /2;
            int counter = 0;
            for (int i = 0; i < list.length; i++){
                counter = counter + list[i];
                if (counter == half){
                    return counter;
                }
            }
        } return 0;
    } 

    public static int lec(int n, int k){
        if (n==k || k==1){
            return 1;
        } else{
            return lec(n-1,k-1) + k*lec(n-1, k);
        }
    }

    public static int lec2(int n, int k){
        int[] mem = new int[n+1];
        mem[1] = 1;
        for (int i = 2; i <= n; i++){
            helper(i, mem);
        }
        return mem[k];
    }

    public static void helper(int n, int[] mem) {
        // Create a temporary array to store current row values
        int[] temp = new int[n + 1];
        
        // Fill the temporary array with the new values
        for (int k = 1; k <= n; k++) {
            if (n == k || k == 1) {
                temp[k] = 1;
            } else {
                temp[k] = mem[k - 1] + k * mem[k];
            }
        }
        
        // Copy the values back to the original memoization array
        for (int k = 1; k <= n; k++) {
            mem[k] = temp[k];
        }
    }
}
