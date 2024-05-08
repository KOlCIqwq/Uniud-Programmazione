public class BottomUpLIS {
  public static void main(String[] args) {
    System.out.println(llisDP(new int[] {10, 11, 12, 6, 7, 8, 9, 1, 2, 3, 4, 5}));
  }
  public static int llisDP( int[] s ) {
    int n = s.length;
    int[][] mem = new int[ n+1 ][ n+1 ];
    // Fill the first col with 0
    for ( int j=0; j<=n; j=j+1 ) {
      mem[n][j] = 0;
    }
    // Going backward as row but forward as col
    for ( int i=n-1; i>=0; i=i-1 ) {
      for ( int j=0; j<=n; j=j+1 ) {
        // if s[i] is <= than t that is 0 if we are at last col. else let the t be the upperbound with s[j] as value
        if (s[i] <= (j == n ? 0 : s[j])){
          mem[i][j] = mem[i+1][j];
        } else{
          // If that's not the case, then try both path and get the biggest value
          mem[i][j] = Math.max(1+mem[i+1][i+1], mem[i+1][j]);
        }
    }}
    // Return the value of 0,0 shown by the picture
    return  mem[0][0];
  }
  
  /* 
  // Longest Increasing Subsequence (LIS):
  // Programmazione dinamica bottom-up
  
  public static int[] lisDP( int[] s ) {
  
    int n = s.length;
    
    int[][] mem = new int[ n+1 ][ n+1 ];
    
    // 1. Matrice: valori delle ricorsioni di llisRec
    //    calcolati esattamente come per llisDP
    
    // ------------------------------------------------
    //  Replica qui il codice del corpo di llisDP
    //  che registra nella matrice i valori
    //  corrispondenti alle ricorsioni di llisRec
    // ------------------------------------------------
    
    // 2. Cammino attraverso la matrice per ricostruire
    //    un esempio di Longest Increasing Subsequence
    
    // ----------------------------------------------------
    //  Inserisci di seguito l'elemento della matrice
    //  il cui valore corrisponde a llis(s) :

    int m =  // elemento appropriato della matrice;
    
    // ----------------------------------------------------
    
    int[] r = new int[ m ];  // per rappresentare una possibile LIS
    
    // ----------------------------------------------------
    //  Introduci e inizializza qui gli indici utili
    //  per seguire un cammino attraverso la matrice e
    //  per assegnare gli elementi della sottosequenza r
    // ----------------------------------------------------

    while ( mem[i][j] > 0 ) {
    
      int t = ( j == n ) ? 0 : s[j];
      
      // --------------------------------------------------
      //  Inserisci qui strutture di controllo e comandi
      //  per scegliere e seguire un percorso appropriato
      //  attraverso la matrice in modo da ricostruire in
      //  r una possibile LIS relativa alla sequenza s
      // --------------------------------------------------
    }
    
    return r;                // = LIS relativa alla sequenza s
  }

 */ 
}  // class BottomUpLIS
 
