package Lab14;

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
        //int[] x = n.findPosition(6);
        //System.out.println(Arrays.toString(x));
        
        PuzzleBoard gui = new PuzzleBoard(x);
        //gui.setNumber(1, 1, 2);
        //gui.display();
        Init(x, overview, gui);
        play(x, n, gui);
    }

    
    public static void Init(int n, String overview, PuzzleBoard gui){
        int row = 1;
        int col = 1;
        for (int i = 0; i < overview.length(); i++){
            char current = overview.charAt(i);
            String num = "";
            if (Character.isDigit(current)){
                int j = i + 1;
                num += current;
                while (j < overview.length() && Character.isDigit(overview.charAt(j))) {
                    num += overview.charAt(j);
                    j++;
                }
                
                int k = 0;
                if (num != ""){
                    k = Integer.valueOf(num);
                } else{
                    k = current - '0'; 
                    
                }
                gui.setNumber(row, col, k);
                col++;
                i = j - 1;
            } else if (current == 'i'){
                continue;
            } else if (current == 'n') {
                row++;
                col = 1;
            }
        }
    } 

    public static void UpdateGUI(int dim, Board n, PuzzleBoard gui){
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                int num = n.getNum(i, j);
                if (num == 0){
                    gui.clear(i+1,j+1);
                } else{
                    gui.setNumber(i + 1, j + 1, num);
                }
            }
        }
        gui.display();
    }

    public static void play(int dim, Board n, PuzzleBoard gui){
        while(!n.isOrdered()){
            int k = gui.get();
            int[] position = n.findPosition(k);
            int i = position[0];
            int j = position[1];
            if (k != 0){
                if (n.canMove(i, j)){
                    n.Movetile(i, j);
                    UpdateGUI(dim,n,gui);
                } else{
                    System.out.println("Can't move tile");
                }
            }
        }
        System.out.println("Bravo, piccotto");
    }
}
