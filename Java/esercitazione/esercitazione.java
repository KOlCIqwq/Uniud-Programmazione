package esercitazione;

import java.util.Stack;

public class esercitazione{
          public static void main(String[] args) {
                    System.out.println(initllcs3("cane", "can", "can"));
                    int[][] m = new int[3][3];
                    for (int i = 0; i < 3; i++){
                              m[i][i] = 1;
                    }
                    System.out.println(checkSym(m));
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
                    return llcs3(t, u, v, mem, 0, 0, 0);
          }
          public static int llcs3 (String t, String u, String v, int[][][] mem, int i, int j, int k){
                    if ((t == "") || (u == "") || (v == "")){
                              return mem[i][j][k];
                    }else if (t.charAt(0) == u.charAt(0) && u.charAt(0) == v.charAt(0)){
                              mem[i+1][j+1][k+1] = mem[i][j][k] + 1;
                              return llcs3(t.substring(1), u.substring(1), v.substring(1), mem, i+1, j+1, k+1);
                    }else{
                              mem[i+1][j+1][k+1] = Math.max(llcs3(t.substring(1), u, v, mem, i+1, j, k), Math.max(llcs3(t, u.substring(1), v, mem, i, j+1, k),llcs3(t, u, v.substring(1), mem, i, j, k+1)));

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
                              stack.push(n.Left());
                    }
                    } while (!stack.isEmpty());
                    return sc;
          }
}