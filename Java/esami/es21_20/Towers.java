package esami;

import java.util.Stack;

public class Towers {
    private int totalDisk;
    private Stack<Integer>[] rods;
    private String moves;
    @SuppressWarnings("unchecked")

    public Towers(int n){   
        totalDisk = n;
        rods = new Stack[4];
        moves = "";
        // Init the 3 rods
        for (int i = 1; i <= 3; i++){
            rods[i] = new Stack<>();
        }
        // Put all the disk on the first rod
        for (int i = n; i >= 1; i--){
            rods[1].push(i);
        }
    }

    public void put (int disk, int rod){
        rods[rod].push(disk);
    }

    public void move(int disk, int dst){
        int src = site(disk);
        rods[dst].push(rods[src].pop());
        moves += moves + src + "->" + dst;
    }

    public int height(){
        return totalDisk;
    }

    public int site (int disk){
        for (int i = 1; i <= 3; i++){
            if (rods[i].contains(disk)){
                return i;
            }
        }
        return -1;
    }

    public int transit(int disk, int dst){
        return (6- site(disk) - dst);
    }

    public String moves(){
        return moves;
    }
}
