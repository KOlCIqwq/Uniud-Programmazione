package esercitazione;

import java.util.Stack;

public class esercitazione{
          public static void main(String[] args) {
                    System.out.println(initllcs3("cane", "can", "can"));
                    int[][] matrix1 = {
                        {1, 2, 3},
                        {2, 4, 5},
                        {4, 5, 7}
                    };
                    System.out.println(checkSym(matrix1));
                    //int[][] m = new int[3][3];
                    //for (int i = 0; i < 3; i++){
                      //        m[i][i] = 1;
                    //}
                    //System.out.println(checkSym(m));
                    double[] pair = closestPair( new double[] {0.3, 0.1, 0.6, 0.8, 0.5, 1.1} );
                    System.out.println(pair[0]+" "+pair[1]);
                    System.out.println(commonStretches( "1110110111", "1100011101" ));
          }

          //1.
          public static int initllcs3 (String t, String u, String v){
                    int a = t.length();
                    int b = u.length();
                    int c = v.length();
                    int maxLen = 0;
                    if (a > b){
                              if (a > c){
                                        maxLen = a;
                              } else{
                                        maxLen = c;
                              }
                    } else{
                              if (b > c){
                                        maxLen = b;
                              } else{
                                        maxLen = c;
                              }
                    }
                    int[][][] mem = new int[maxLen+1][maxLen+1][maxLen+1];
                    for (int i = 0; i <= a; i++) {
                        for (int j = 0; j <= b; j++) {
                            for (int k = 0; k <= c; k++) {
                                mem[i][j][k] = -1;
                            }
                        }
                    }
                    return llcs3(t, u, v, mem, a, b, c);
          }
          public static int llcs3 (String t, String u, String v, int[][][] mem, int i, int j, int k){
            if (i == 0 || j == 0 || k == 0){
                return 0;
            }

            if (mem[i][j][k] != -1){
                return mem[i][j][k];
            }

            if (t.charAt(i - 1) == u.charAt(j - 1) && t.charAt(i - 1) == v.charAt(k - 1)){
                mem[i][j][k] = 1 + llcs3(t, u, v, mem, i-1, j-1, k-1);
            }else{
                mem[i][j][k] = Math.max(Math.max(llcs3(t, u, v, mem, i-1, j, k),llcs3(t, u, v, mem, i, j-1, k)),llcs3(t, u, v, mem, i, j, k-1));

            }
            return mem[i][j][k];
          }
          //2.
          public static boolean checkSym (int[][] m){
                    
                    if (m == null){
                              return false;
                    }
                    for (int i = 0; i < m.length; i++){
                              for (int j = 0; j < m.length; j++){
                                        if (i == j){
                                                  j++;
                                        }else if (m[i][j] != m[j][i]){
                                                  return false;
                                        }else{
                                                  return true;
                                        }

                              }
                    }
                    return true;
          } 

          // 3. see the file Board.java
          public static void listOfCompletions( Board b ) {
                    int n = b.size(); int q = b.queensOn();
                    if ( q == n ) {
                    System.out.println( b.arrangement() );
                    } else {
                    int i = 1;
                    while ( !b.isFreeRow(i) ) {
                    i = i + 1; // ricerca di una riga libera
                    }
                    for ( int j=1; j<=n; j=j+1 ) {
                    if ( ! b.underAttack(i,j) ) {
                    b.addQueen( i, j );
                    listOfCompletions( b );
                    b.removeQueen( i, j );
                    }}}
          }

          //4.
          public static int shortestCodeLength( Node root ) {
                    int sc = 0;
                    Stack<Node> stack = new Stack<Node>();
                    Stack<Integer> depth = new Stack<Integer>();
                    stack.push( root );
                    depth.push( 0 );
                    do {
                              Node n = stack.pop();
                              int d = depth.pop();
                              if ( n.IsLeaf() ) {
                                        sc = Math.min( sc, d );
                    } else if ( d+1 < sc) {
                              depth.push(sc);
                              stack.push(n.Right());
                              depth.push(d+1);
                              stack.push(n.Left());
                              depth.push((d+1));
                    }
                    } while (!stack.isEmpty());
                    return sc;
          }
          // 5.
          public static double[] closestPair(double[] list){
            double[] pair = new double[2];
            double minDiff = Double.MAX_VALUE;
            double diff = 0;
            for (int i = 0; i < list.length; i++){
                for (int j = i + 1;j < list.length; j++){
                    diff = Math.abs(list[i] - list[j]);
                    if (minDiff > diff){
                        minDiff = diff;
                        pair[0] = list[i];
                        pair[1] = list[j];
                    }
                }
            }
            return pair;
          }

          //6.
          public static int commonStretches(String p1, String p2){
            int counter = -1;
            for (int i = 0; i < p1.length(); i++){
                if (p1.charAt(i) == p2.charAt(i)){
                    counter++;
                }
            }
            return counter;
          }

          //7.
          
}