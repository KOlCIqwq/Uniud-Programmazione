public class BottomUpLIS {
  public static void main(String[] args) {
    //System.out.println(llisDP(new int[] {5,4,3,2,1}));
    int[] array = lisDP(new int[] {2, 7, 5, 7, 4});
    for (int k:array){
      System.out.println(k);
    }
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
    return mem[0][n];
  }
  
  // Longest Increasing Subsequence (LIS):
  // Programmazione dinamica bottom-up
  
  public static int[] lisDP( int[] s ) {
  
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
    //return mem[0][0];

    int m = n+1; // elemento appropriato della matrice;

    int[] r = new int[ m ];  // per rappresentare una possibile LIS
    
    int i = 0;
    int j = n;

    while ( mem[i][j] > 0 ) {
      int t = ( j == n ) ? 0 : s[j];
      
      if (s[i] <= t){
        r[i] = s[i];
        i++;
        if (j == n){
          j = 0;
        } else{
          j++;
        }
      } else if (mem[i+1][j] < mem[i][j]){
        j++;
      } else if (mem[i+1][j] > mem[i][j]){
        i++;
      } else{
        i++;
      }
    }
    return r;                // = LIS relativa alla sequenza s
  }
}  // class BottomUpLIS
 
