import java.util.Arrays;

public class BottomUpLIS {
  public static void main(String[] args) {
    System.out.println(llisDP(new int[] {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3}));
    int[] array = lisDP(new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 6});
    System.out.println(Arrays.toString(array));
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
          mem[i][j] = Math.max(1+mem[i+1][i], mem[i+1][j]);
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
        // if j touch the down part -> change it to 0; otherwise compare s[j]
        if (s[i] <= (j == n ? 0 : s[j])){
          mem[i][j] = mem[i+1][j];
        } else{
          // If that's not the case, then try both path and get the biggest value
          mem[i][j] = Math.max(1+mem[i+1][i], mem[i+1][j]);
        }
    }}
    // Return the value of 0,0 shown by the picture
    //return mem[0][0];

    int m = mem[0][n]; // Start from i = 0, j = length

    int[] r = new int[ m ];  // Initialize a output array with length at least length
    
    int i = 0;
    int j = n;
    int pointer = 0; // Output array pointer

    while (mem[i][j] > 0 ) {
      int t = ( j == n ) ? 0 : s[j];
      if ((j == n || s[i] > t) && mem[i][j] == 1 + mem[i + 1][i]){
        r[pointer] = s[i];
        pointer++;
        j = i;
      }
      i++;
    }
    return r;  
  }
}  // class BottomUpLIS
 
