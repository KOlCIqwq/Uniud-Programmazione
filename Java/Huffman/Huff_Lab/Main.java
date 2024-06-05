package Huffman.Huff_Lab;
import java.util.*;

import huffman_toolkit.*;
public class Main {
    private static final int LENGTH =  10000;
    private static final int[] frequency = new int[128];

    public static void main(String[] args) {
        //randomText("Java/Huffman/Huff_Lab/test.txt");
        compress("Java/Huffman/Huff_Lab/test.txt", "Java/Huffman/Huff_Lab/compressed.txt");
        decompress("Java/Huffman/Huff_Lab/compressed.txt", "Java/Huffman/Huff_Lab/decompressed.txt");
        System.out.println(Arrays.toString(frequency));
    }

    public static void randomText(String dst){
        OutputTextFile out = new OutputTextFile(dst);
        // Give a random int as length of the file
        int length = (int)((Math.random() * 100)) + 10;
        for (int i = 0; i < length; i++){
            // Let c be any number between 0 and 128
            char c = (char) (128 * Math.random());
            out.writeChar(c);
        }
        out.close();
    }

    // Initialize freq
    static {
        // Frequenze delle lettere minuscole
        frequency['a'] = 8167;
        frequency['b'] = 1492;
        frequency['c'] = 2782;
        frequency['d'] = 4253;
        frequency['e'] = 12702;
        frequency['f'] = 2228;
        frequency['g'] = 2015;
        frequency['h'] = 6094;
        frequency['i'] = 6966;
        frequency['j'] = 153;
        frequency['k'] = 772;
        frequency['l'] = 4025;
        frequency['m'] = 2406;
        frequency['n'] = 6749;
        frequency['o'] = 7507;
        frequency['p'] = 1929;
        frequency['q'] = 95;
        frequency['r'] = 5987;
        frequency['s'] = 6327;
        frequency['t'] = 9056;
        frequency['u'] = 2758;
        frequency['v'] = 978;
        frequency['w'] = 2361;
        frequency['x'] = 150;
        frequency['y'] = 1974;
        frequency['z'] = 74;

        // Put the uppercase
        for (char c = 'A'; c <= 'Z'; c++){
            char currentLow = Character.toLowerCase(c);
            // The uppercase occurs same frequency as lower, and appears usually at the start of each phrase
            frequency[c] = frequency[currentLow] / 20;
        }

        frequency['.'] = LENGTH / 24; // Approximation: 1 period per sentence, assuming 24 words per sentence
        frequency[','] = 2925; // 45% of punctuation
        frequency['\''] = 2600; // 40% of punctuation
        frequency[':'] = 325; // 5% of punctuation
        frequency[';'] = 325; // 5% of punctuation
        frequency['"'] = 325; // 5% of punctuation

        frequency[' '] = LENGTH / 5; // Space between each word, each word has 5 letters

        for (char c = '0'; c <= '9'; c++){
            // Assuming that the numbers appear only 50 times
            frequency[c] = 50;
        }
        frequency['\n'] = LENGTH / 245; // Each phrase is 24,5 word long

        // Other symbols
        for (char c = 0; c < 128; c++){
            if (frequency[c] == 0){
                frequency[c] = 10;
            }
        }
    }

    /* 
    public static int[] checkFreq(String src){
        InputTextFile in = new InputTextFile(src);
        int[] freq = new int[InputTextFile.CHARS];
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
    }*/

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
        int[] freq = frequency;
        Node tree = huffmanTree(freq);
        String[] table = huffmantable(tree);
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);

        while (in.textAvailable()){
            char c = in.readChar();
            out.writeCode(table[c]);
        }
        in.close();
        out.close();
    }

    public static Node restoreTree(InputTextFile in) {
  
        char c = (char) in.readChar();                          // carattere dell'intestazione
        
        if ( c == '@' ) {                                       // '@' ?
        
          Node left  = restoreTree(in);                       // sottoalbero sinistro
          
          Node right = restoreTree(in);                       // sottoalbero destro
          
          return new Node( left, right );                       // nodo genitore
        
        } else {
          if ( c == '\\' ) {
            c = (char) in.readChar();                           // carattere speciale (skip)
          }
          return new Node( c, 0 );                              // foglia
        }
      }
      
      public static void decompress( String src, String dst ) {
        
        InputTextFile in = new InputTextFile(src);            // documento compresso
        
        int count = Integer.parseInt( in.readTextLine() );      // decodifica dell'intestazione
        Node root = restoreTree(in);
        
        String dummy = in.readTextLine();                       // codici caratteri dopo il fine-linea
        
        OutputTextFile out = new OutputTextFile(dst);         // documento ripristinato
        
        for ( int j=0; j<count; j=j+1 ) {                       // scansione: decodifica dei caratteri
          
          char c = decodeNextChar(root, in);
          out.writeChar(c);
        }
        in.close();
        out.close();
      }
      
      private static char decodeNextChar(Node root, InputTextFile in) {
      
        Node n = root;                                          // percorso lungo l'albero di Huffman
        
        do {
          if ( in.readBit() == 0 ) {
            n = n.Left();                                       // bit 0: figlio sinistro
          } else {
            n = n.Right();                                      // bit 1: figlio destro
          }
        } while ( !n.IsLeaf() );                                // fino al raggiungimento di una foglia
        
        return n.symbol();                                   // carattere individuato
      }
      
}
