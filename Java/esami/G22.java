package esami;

import java.util.Stack;

public class G22 {
    public static void main(String[] args) {
    
        System.out.println(heapCheck( new double[] { 5.0, 3.1, 5.7, 3.1, 8.5, 6.0, 3.0, 4.2, 9.3 } ));
        double[] s = {1.0, 2.5, 3.0, 4.0, 5.5, 3.5};
        System.out.println(llds(s));
    }


    public static boolean heapCheck(double[] list){
        for (int i = 1; i < list.length / 2; i++){
            int left = 2 * i;
            int right = 2 * i + 1;
            if (list[i] > list[left]){
                return false;
            }
            if (right >= list.length){
                break;
            }
            if (list[i] > list[right]){
                return false;
            }
        }
        return true;
    }

    public static int llds( double[] s ) {
        int n = s.length;
        // We need to memorize 3 pointers, thus we need 3 dim. array
        int[][][] mem = new int[n+1][n+1][n+1];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                for (int k = 0; k < n; k++){
                    mem[i][j][k] = -1;
                }
            }
        }
        // k the index to the array, i the first element of the prev, j the second
        return lldsRec(s, 0, -1, -1, mem);
    }
    private static int lldsRec( double[] s, int k, int i, int j, int[][][] mem) {
        if ( mem[k][i+1][j+1] == -1) {
            if (k == s.length){
                return 0;
            } else if(i == -1 || Math.min(s[j],s[k])<s[i] && s[i] < Math.max(s[j], s[k])){
                mem[k][i+1][j+1] = Math.max(1+lldsRec(s, k+1, j, k, mem),lldsRec(s, k+1, i, j, mem));
            } else{
                mem[k][i+1][j+1] = lldsRec(s, k+1, i, j, mem);
            }
        }
        return mem[k][i+1][j+1];
    }

    public static class Frame {
        public final Node node;
        public final int depth;
        public Frame(Node node, int depth) {
        this.node = node;
        this.depth = depth;
        }
    } // class Frame
    public static int codeSizeIter( Node root ) {
        long bits = 0;
        Stack<Frame> stack = new Stack<Frame>();
        stack.push(new Frame(root, 0));
        do {
        Frame current = stack.pop();
        Node n = current.node;
        int depth = current.depth;
        if(n.IsLeaf()){
            bits = depth * n.Weight();
        } else{
            stack.push(new Frame(n.Left(), depth+1));
            stack.push(new Frame(n.Right(), depth+1));
        }
        } while (!stack.isEmpty());
        return (int) ( bits / 7 ) + ( (bits%7 > 0) ? 1 : 0 );
        }
}

