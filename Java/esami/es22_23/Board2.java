package esami.es22_23;



public class Board2 {

    private final int size;
    private int queens;
    private int[] rowAttacked;
    private int[] colAttacked;
    private int[] dg1Attacked;
    private int[] dg2Attacked;
    private String config;

    public Board2(int n) {
        this.size = n;
        this.queens = 0;
        this.rowAttacked = new int[n];
        this.colAttacked = new int[n];
        this.dg1Attacked = new int[2 * n - 1];
        this.dg2Attacked = new int[2 * n - 1];
        this.config = "";
    }

    public int size() {
        return size;
    }

    public int queensOn() {
        return queens;
    }

    public boolean underAttack(int i, int j) {
        int n = size;
        return (rowAttacked[i - 1] > 0 || colAttacked[j - 1] > 0 || dg1Attacked[i - j + n - 1] > 0 || dg2Attacked[i + j - 2] > 0);
    }

    public Board2 addQueen(int i, int j) {
        rowAttacked[i - 1]++;
        colAttacked[j - 1]++;
        dg1Attacked[i - j + size - 1]++;
        dg2Attacked[i + j - 2]++;
        queens++;
        config += "(" + i + "," + j + ")";
        return this;
    }

    public void removeQueen(int i, int j) {
        rowAttacked[i - 1]--;
        colAttacked[j - 1]--;
        dg1Attacked[i - j + size - 1]--;
        dg2Attacked[i + j - 2]--;
        queens--;
        config = config.replace("(" + i + "," + j + ")", "");
    }
    public String arrangement() {
        return config;
    }

    public String toString() {
        return config;
    }

    public int freeCol(){
        // Iterate all colums
        for (int j = 1; j < size; j++){
            // If the current index of column is 0, meaning is not underattack
            // we can return j as free col
            if (colAttacked[j] == 0){
                return j;
            }
        }
        return -1;
    }

    public int freeRow(){
        for (int i = 1; i < size; i++){
            if (rowAttacked[i] == 0){
                return i;
            }
        }
        return -1;
    }
}
