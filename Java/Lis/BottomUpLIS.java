
import java.util.Arrays;

public class BottomUpLIS {
  public static void main(String[] args) {
    System.out.println(llisDP(new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6} ));
    int[] array = lisDP(new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6});
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
        // Scan the through the string and check if the number is smaller if yes, it cannot be a number in subsequence, so skip it
        // Meaning the value of current cell will be the previous, remaining unchanged
        if (s[i] <= (j == n ? 0 : s[j])){
          mem[i][j] = mem[i+1][j];
        } else{
          // If that's not the case, then try both path, put the number in subsequence or don't put it
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
        // Scan the through the string and check if the number is smaller if yes, it cannot be a number in subsequence, so skip it
        // Meaning the value of current cell will be the previous, remaining unchanged
        if (s[i] <= (j == n ? 0 : s[j])){
          mem[i][j] = mem[i+1][j];
        } else{
          // If that's not the case, then try both path, put the number in subsequence or don't put it
          mem[i][j] = Math.max(1+mem[i+1][i], mem[i+1][j]);
        }
    }}

    int m = mem[0][n]; // Start from the end point

    int[] r = new int[ m ];  // Initialize a output array with length at least length
    
    // Initialize new pointers to restore the subsequence
    int i = 0;
    int j = n;
    int pointer = 0; // Output array pointer

    while (mem[i][j] > 0 ) {
      //int t = (j == n) ? 0 : s[j];
      // If the diagonal or the left values are equal to the current + 1 then it can be a candidate to number in subsequence
      if (mem[i][j] == 1 + mem[i + 1][i] || mem[i][j] == 1 + mem[i + 1][j]){
        // Add the number to array
        r[pointer] = s[i];
        pointer++;
        // Reset the j pointer
        j = i;
      }
      i++;
    }
    return r;  
  }
}  // class BottomUpLIS
 
