import java.util.*;

import huffman_toolkit.*;

public class Huffman {
    private static final int LENGTH =  100000;
    private static final int[] frequency = new int[128];

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
        frequency[','] = 1875; // 45% of punctuation (LENGTH / 24 * 0.45)
        frequency['\''] = 1666; // 40% of punctuation
        frequency[':'] = 208; // 5% of punctuation
        frequency[';'] = 208; // 5% of punctuation
        frequency['"'] = 208; // 5% of punctuation

        frequency[' '] = LENGTH / 5; // Space between each word, each word has 5 letters

        for (char c = '0'; c <= '9'; c++){
            // Assuming that the numbers appear only 50 times
            frequency[c] = 50;
        }
        frequency['\n'] = LENGTH / 245; // Each phrase is 24,5 word long, assuming every 10 pharase it goes next line

        // Other symbols
        for (char c = 0; c < 128; c++){
            if (frequency[c] == 0){
                frequency[c] = 10;
            }
        }
    } 
    
    public static void randomText(String dst){
        OutputTextFile out = new OutputTextFile(dst);
        // Give a random int as length of the file
        int length = (int)((Math.random() * 100)) + 10;
        for (int i = 0; i < length; i++){
            // Let c be any number between 0 and 128
            Random random = new Random();
            int number = random.nextInt(127);
            char c = (char) number;
            out.writeChar(c);
        }
        System.out.println("genarated" + " " + length + " "+ "chars");
        out.close();
    }

    private static Node buildHuffmanTree() {
        //PriorityQueue<Node> queue = new PriorityQueue<Node>();
        NodeQueue queue = new NodeQueue();
        for (int c = 0; c < 128; c++) {
            // Adding all the 128 chars and it's frequency inside the NodeQueue as SingleNode
            if (frequency[c] > 0) {
                Node n = new Node((char) c, frequency[c]);
                // Each time we add the node it starts the process of heapify, to mantain the first as the one which weight the least
                queue.add(n);
            }
        }
        // Start to construct the tree, and store the paired node also insiede the NodeQueue
        // The program will terminate when it has done doing the tree (when queue.size() == 1)
        while (queue.size() > 1) {
            Node l = queue.poll();
            Node r = queue.poll();
            Node n = new Node(l, r);
            queue.add(n);
        }
        // Return the tree.
        return queue.poll();
    }

    public static String[] huffmanCodesTable(Node root) {
        String[] codes = new String[128];
        fillTable(root, "", codes);
        return codes;
    }

    private static void fillTable(Node n, String code, String[] codes) {
        if (n.isLeaf()) {
            codes[n.symbol()] = code;
        } else {
            fillTable(n.left(), code + "0", codes);
            fillTable(n.right(), code + "1", codes);
        }
    }

    public static void compress(String src, String dst) {
        Node root = buildHuffmanTree();
        String[] codes = huffmanCodesTable(root);

        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);
        // numero complessivo di caratteri
        //int count = root.weight();
        int count = 0;
        // Mutable String, creating a string obj, that can append(),insert(),delete(),replace(),toString().
        StringBuilder compressedData = new StringBuilder();
        
        // Read the document, increment count reading a char, append to stringbuilder the string
        while (in.textAvailable()) {
            char c = in.readChar();
            if (c < 128) {
                compressedData.append(codes[c]);
                count++;
            }
        }
        
        // To have the first line as the length of the compressed file
        out.writeTextLine( "" + count );
        out.writeCode(compressedData.toString());
        
        in.close();
        out.close();
    }

    public static void decompress(String src, String dst) {
        Node root = buildHuffmanTree();

        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);
        // Read length of compressed data (first line) 
        int count = Integer.parseInt(in.readTextLine());

        for (int j = 0; j < count; j++) {
            char c = decodeNextChar(root, in);
            //if (c != ','){ // Length is not equal to weight
                out.writeChar(c);
            //} else{
              //  break;
            //}
        }
        in.close();
        out.close();
    }

    private static char decodeNextChar(Node root, InputTextFile in) {
        Node n = root;
        do {
            if (in.readBit() == 0) {
                n = n.left();
            } else {
                n = n.right();
            }
        } while (!n.isLeaf());
        return n.symbol();
    }

    public static void main(String[] args) {
        System.out.println("huffman coding:");
        if (args[0].equals("compress")) {
            System.out.println("compressing...");
            compress(args[1], args[2]);
        } else if (args[0].equals("decompress")) {
            System.out.println("decompressing...");
            decompress(args[1], args[2]);
        } else {
            System.out.println("no operation specified: 1st parameter should be either 'compress' or 'decompress'");
        }
        System.out.println("done.");
    }
}