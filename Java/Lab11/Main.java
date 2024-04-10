package Lab11;
public class Main {

    public static void main(String[] args) {
        System.out.println(josephus(5));
    }
    

    public static String josephus(int n){
        RoundTable rt = new RoundTable(n);
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
