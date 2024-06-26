 
public class Main {

    public static void main(String[] args) {
        //System.out.println(josephus(3));
    }
    

    public static String josephus(int n) {
        RoundTable rt = new RoundTable(n);
        while (rt.numberOfKnights() > 2) {
            rt.serveNeighbour();
            rt.passJug();
        }
        return rt.servingKnights();
    }
}
