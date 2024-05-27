package Huffman.Huff_Lab;
import java.util.PriorityQueue;
import java.util.*;

import huffman_toolkit.*;
public class Main {
    public static void main(String[] args) {
        randomText("Java/Huffman/Huff_Lab/test.txt");
        compress("Java/Huffman/Huff_Lab/test.txt", "Java/Huffman/Huff_Lab/compressed.txt");
    }

    public static void randomText(String dst){
        OutputTextFile out = new OutputTextFile(dst);

        
        int length = (int)((Math.random() * 100)) + 10;
        for (int i = 0; i < length; i++){
            char c = (char) (128 * Math.random());
            out.writeChar(c);
        }
        out.close();
    }

    public static int[] checkFreq(String src){
        InputTextFile in = new InputTextFile( src );
        int [] freq = new int[InputTextFile.CHARS];
        for (int i = 0; i < freq.length; i++){
            // Init
            freq[i] = 0;
        }
        while (in.textAvailable()){
            char c = in.readChar();
            freq[c] = freq[c] + 1;
        }
        in.close();
        return freq;
    }

    private static final int CHARS = InputTextFile.CHARS;

    public static Node huffmanTree(int[] freq){
        NodeQueue queue = new NodeQueue();
        
        for ( int c=0; c<CHARS; c=c+1 ) {                      
        if ( freq[c] > 0 ) {
            Node n = new Node( (char) c, freq[c] );             
            queue.add( n );                                     
        }}
        while ( queue.size() > 1 ) {                            
        
        Node l = queue.poll();                                
        Node r = queue.poll();
        
        Node n = new Node( l, r );                            
        queue.add( n );                                       
        }
        return queue.poll();   
    }

    public static String[] huffmantable(Node root){
        String[] table = new String[InputTextFile.CHARS];
        fillTable (root, "", table);
        return table;
    }

    public static void fillTable(Node root, String s, String[] table){
        if (root.IsLeaf()){
            table[root.symbol()] = s;
        } else{
            fillTable(root.Left(), s+"0", table);
            fillTable(root.Right(), s+"1", table);
        }
    }

    public static void compress (String src, String dst){
        int[] freq = checkFreq(src);
        Node tree = huffmanTree(freq);
        String[] table = huffmantable(tree);
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);

        out.writeTextLine("" + tree.Weight());
        //out.writeTextLine(flatTree(tree));

        while (in.textAvailable()){
            char c = in.readChar();
            out.writeCode(table[c]);
        }
        in.close();
        out.close();
    }
}
