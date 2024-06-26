 

import java.util.Scanner; // To get user input

import puzzleboard.*;
/*
 * Need to do further work on gui things
 */
public class Main {
    public static void main(String[] args) {
        // Request an input of size
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the size of puzzle");
        int x = reader.nextInt();
        reader.close();

        Board n = new Board (x);
        System.out.println(n.toString());
        n.Movetile(3,1);
        System.out.println(n.toString());
        String overview = n.guiString();
        System.out.println(overview);
        
        start(x);
        //int[] x = n.findPosition(6);
        //System.out.println(Arrays.toString(x));
        
        //PuzzleBoard gui = new PuzzleBoard(x);
        //gui.setNumber(1, 1, 2);
        //gui.display();
        //Init(x, overview, gui);
        //play(x, n, gui);
    }

    public static void start(int n){
        PuzzleBoard gui = new PuzzleBoard(n);
        Board b = new Board(n);
        String overview = b.guiString();
        Init(n,overview,gui);
        play(n,b,gui);
    }
    // Initialize the puzzle
    public static void Init(int n, String overview, PuzzleBoard gui){
        int row = 1;
        int col = 1;
        // Loop the flatten table 
        for (int i = 0; i < overview.length(); i++){
            char current = overview.charAt(i);
            String num = ""; // In case the number is > 9
            if (Character.isDigit(current)){
                int j = i + 1;
                num += current;
                // Check if the next digits are numbers too, giving the new pointer j
                while (j < overview.length() && Character.isDigit(overview.charAt(j))) {
                    // Append the char to string
                    num += overview.charAt(j);
                    j++;
                }
                //Given k to be the value of current number
                int k = 0;
                if (num != ""){
                    // If the number is > 9, transform the string into int and store as k
                    k = Integer.parseInt(num);
                } else{
                    k = current - '0'; 
                }
                gui.setNumber(row, col, k);
                // Move the col, while is there's no instruction to jump row (n)
                col++;
                // Adjust the i pointer to the jth position - 1
                i = j - 1;
            } else if (current == 'i'){
                // Unecesarry
                continue;
            } else if (current == 'n') {
                // Jump row iif it's next line (n)
                row++;
                col = 1;
            }
        }
    } 

    //Update the Gui after user's click
    public static void UpdateGUI(int dim, Board n, PuzzleBoard gui){
        // Loop the table
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                int num = n.getNum(i, j);
                // If the num is a hole
                if (num == 0){
                    // + 1 Because the range of index goes from 1 to n
                    // But the index I used for my table goes from 0 to n-1
                    gui.clear(i+1,j+1);
                } else{
                    // If it's a number set it on gui
                    gui.setNumber(i + 1, j + 1, num);
                }
            }
        }
        gui.display();
    }

    // Detect user's input and update the table
    public static void play(int dim, Board n, PuzzleBoard gui){
        int countClicks = 0;
        // Keep gui alive
        while(!n.isOrdered()){
            int k = gui.get();
            // Get the position of the number clicked by user as it's position in table
            int[] position = Board.findPosition(k);
            int i = position[0];
            int j = position[1];
            if (k != 0){
                if (Board.canMove(i, j)){
                    countClicks++;
                    Board.Movetile(i, j);
                    UpdateGUI(dim,n,gui);
                } else{
                    System.out.println("Can't move tile" + " " + k);
                }
            }
        }
        System.out.println("Usato " + countClicks + " per completare il problema");
    }
}
