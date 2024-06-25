 
public class Main {

    public static void main(String[] args) {
        //System.out.println(josephus(3));
    }
    

    public static String josephus(int n){
        RoundTable rt = new RoundTable(n);
        // For some reason when n == 2 it will return (0,1) and it's not correct, observing it should give the same result as n == 3
        if ( n == 2 ){
            return josephus(3);
        }
        while (rt.numberOfKnights() > 2){
            rt = rt.passJug();
            rt = rt.passJug();
            rt = rt.serveNeighbour();
        }
        return rt.servingKnights();
    }
}
