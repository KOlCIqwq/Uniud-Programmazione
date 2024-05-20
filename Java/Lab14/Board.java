package Lab14;

public class Board {
    private static int [][] table;
    private static int dim;
    private static int iHole, jHole;

    public Board(int n){
        this.dim = n;
        table = new int [dim][dim];
        InitBoard();
    }

    public int getNum(int i, int j){
        return table[i][j];
    }

    private void InitBoard(){
        // Fill the table with numbers and the last one with a hole
        int count = 1;
        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                if (count == dim * dim){
                    table[i][j] = 0;
                    iHole = i;
                    jHole = j;
                } else{
                    table[i][j] = count;
                    count++;
                }
            }
        }
        // Times to shuffle the board
        int exTimes = 100;
        //Up, Down, Left, Right
        int[][] directions ={{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < exTimes; i++){
            int dirIndex = (int)(Math.random()*4);
            int[] direction = directions[dirIndex];
            
            int randIHole = iHole + direction[0];
            int randJHole = iHole + direction[1];
            // Check if moving to the direction is in the bound or not
            if (randIHole >= 0 && randIHole < dim && randJHole >= 0 && randJHole < dim){
                // Replace the present hole with the number and change the number to hole
                table[iHole][jHole] = table[randIHole][randJHole];
                table[randIHole][randJHole] = 0;
                iHole = randIHole;
                jHole = randJHole;
            }
        }
        /* 
        int iRandom = (int)(Math.random() * dim + 1);
        int jRandom = (int)(Math.random() * dim + 1);
        int temp = 0;
        int exTimes = 10;
        for (int i = 0; i < exTimes; i++){
            temp = table[iRandom][jRandom];
            if (temp == 0){
                iHole = iRandom;
                jHole = jRandom;
            }
            table[iRandom][jRandom] = 0;
        }*/
    }

    public boolean isOrdered(){
        int prev = 0;
        // Loop the board
        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                // i != dim - 1 && j != dim - 1 to not look at the last grid because it will hole
                if (table[i][j] != prev + 1 && i != dim - 1 && j != dim - 1){
                    return false;
                }
                prev = table[i][j];
            }
        }
        return true;
    }

    public static boolean canMove(int i, int j){
        // Check if the hole is in up or down position
        if (Math.abs(i - iHole) == 1 && j == jHole){
            return true;
        } // Check if the hole is in left or right position
        else if ((Math.abs(j - jHole) == 1 && i == iHole)){
            return true;
        } else{
            return false;
        }
    }

    public String toString(){
        String conf = "";
        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                conf += table[i][j] + " ";
            }
            conf += "\n";
        }
        return conf;
    }

    public String guiString(){
        String conf = "";
        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                conf += table[i][j] + "i";
            }
            conf += "n";
        }
        return conf;
    }

    public static void Movetile(int i, int j){
        if (!canMove(i, j)){
            System.out.println("Cannot move" + i + j);
        } else{
            // Change the position of the indicated i,j and Hole
            table[iHole][jHole] = table[i][j];
            table[i][j] = 0;
            iHole = i;
            jHole = j;
        }
    }

    public static int[] findPosition(int k){
        int[] pos = new int[2];

        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                if (table[i][j] == k){
                    pos[0] = i;
                    pos[1] = j; 
                    return pos;
                }
            }
        }
        return pos;
    }
}