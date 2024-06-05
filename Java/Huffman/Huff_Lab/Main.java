package Huffman.Huff_Lab;
import java.util.*;

import huffman_toolkit.*;
public class Main {
    private static final int LENGTH =  10000;
    private static final int[] frequency = new int[128];

    public static void main(String[] args) {
        randomText("Java/Huffman/Huff_Lab/test.txt");
        compress("Java/Huffman/Huff_Lab/test.txt", "Java/Huffman/Huff_Lab/compressed.txt");
        decompress("Java/Huffman/Huff_Lab/compressed.txt", "Java/Huffman/Huff_Lab/decompressed.txt");
    }

    public static void randomText(String dst){
        OutputTextFile out = new OutputTextFile(dst);
        // Give a random int as length of the file
        int length = (int)((Math.random() * 100)) + 10;
        for (int i = 0; i < length; i++){
            // Let c be any number between 0 and 128
            Random random = new Random();
            int number = 33 + random.nextInt(127 - 33 + 1);
            char c = (char) number;
            out.writeChar(c);
        }
        System.out.println("genarated" + " " + length + " "+ "chars");
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
            frequency[c] = frequency[currentLow] / 10;
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

    public static String[] huffmanCodesTable( Node root ) {
  
        String[] codes = new String[ CHARS ];                   
        
        fillTable( root, "", codes );                         
        
        return codes;
      }
      
      private static void fillTable( Node n, String code, String[] codes ) {
      
        if ( n.IsLeaf() ) {
          codes[ n.symbol() ] = code;                       
        } else {
          fillTable( n.Left(),  code+"0", codes );              
          fillTable( n.Right(), code+"1", codes );             
        }
      }
      
      public static String flattenTree( Node n ) {
        
        if ( n.IsLeaf() ) {                                 
          char c = n.symbol();
          if ( (c == '\\') || (c == '@') ) {
            return ( "\\" + c );                                
          } else {
            return ( "" + c );                                 
          }
        } else {
          return ( "@"                                         
                 + flattenTree( n.Left() )                      
                 + flattenTree( n.Right() )                     
                   );
        }
      }
      
      public static void compress( String src, String dst ) {
        
        int[] freq = frequency;                      
        
        Node root = huffmanTree( freq );                        
        
        int count = root.Weight();                              
        String[] codes = huffmanCodesTable( root );             
        
        InputTextFile in = new InputTextFile( src );            
        
        OutputTextFile out = new OutputTextFile( dst );         
        
        out.writeTextLine( "" + count );                        
        out.writeTextLine( flattenTree(root) );
        
        for ( int j=0; j<count; j=j+1 ) {                       
        
          char c = in.readChar();
          // To prevent compressing character over the ascii table
          if (c < 128){
            out.writeCode( codes[c] );
          } else{
            break;
          }
          
        }
        in.close();
        out.close();
      }
      
      public static Node restoreTree( InputTextFile in ) {
      
        char c = (char) in.readChar();                          
        
        if ( c == '@' ) {                                      
        
          Node left  = restoreTree( in );                       
          
          Node right = restoreTree( in );                       
          
          return new Node( left, right );                       
        
        } else {
          if ( c == '\\' ) {
            c = (char) in.readChar();                           
          }
          return new Node( c, 0 );                              
        }
      }
      
      public static void decompress( String src, String dst ) {
        
        InputTextFile in = new InputTextFile( src ); 
        
        int count = Integer.parseInt( in.readTextLine() );  
        Node root = restoreTree( in );                     
        
        OutputTextFile out = new OutputTextFile( dst );         
        
        for ( int j=0; j<count; j=j+1 ) {                       
          
          char c = decodeNextChar( root, in );
          // for some reason it keeps going, writing in the file ','; so added a check to exit when reached the end
          if (c == ','){
            break;
          } else{
                out.writeChar( c );
            }
        }
        in.close();
        out.close();
      }
      
      private static char decodeNextChar( Node root, InputTextFile in ) {
      
        Node n = root;                                          
        do {
          if ( in.readBit() == 0 ) {
            n = n.Left();                                      
          } else {
            n = n.Right();                                     
          }
        } while ( !n.IsLeaf() );                               
        
        return n.symbol();                                  
      }
}
