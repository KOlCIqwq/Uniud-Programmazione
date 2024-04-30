public class TavolaRotonda {


    private int num;
    private int jug;                   
    private int[] cavalieri;         

    public TavolaRotonda( int n ) {
      num = n;
      jug = 1;
      cavalieri = new int [2*n-1];
      for (int i = 1; i <= n; i++){
        cavalieri[i-1] = i;
      }
    }

    public int numberOfKnights() {           
      return num;
    }
    
  
    public int knightWithJug() {            
      return cavalieri[jug];
    }
    
    public void serveNeighbour() {     
        if (num >1){
            cavalieri[jug+1] = cavalieri[jug];
            jug = jug + 1;
            num = num - 1;
        }
    }
    
    public void passJug() {        
        if (num >1){
            cavalieri[jug+num] = cavalieri[jug];
            jug = jug + 1;
        }
    }
}
  
   
    

