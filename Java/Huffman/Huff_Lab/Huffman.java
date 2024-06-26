package Huffman.Huff_Lab;

import java.util.*;

import huffman_toolkit.*;

public class Huffman {
    public static void main(String[] args) {
        randomText("test.txt");
        compress("test.txt", "compress.txt");
        decompress("compress.txt", "decompress.txt");
    }
    private static final int LENGTH =  10000;
    private static final int[] frequency = new int[128];

    // Initialize freq
    static {
        // Lowercase freq
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

    // Costruzione dell'albero di Huffman predefinito
    private static final Node HUFFMAN_TREE = buildHuffmanTree();

    private static Node buildHuffmanTree() {
        //PriorityQueue<Node> queue = new PriorityQueue<Node>();
        NodeQueue queue = new NodeQueue();
        for (int c = 0; c < 128; c++) {
            if (frequency[c] > 0) {
                Node n = new Node((char) c, frequency[c]);
                queue.add(n);
            }
        }
        while (queue.size() > 1) {
            Node l = queue.poll();
            Node r = queue.poll();
            Node n = new Node(l, r);
            queue.add(n);
        }
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
        Node root = HUFFMAN_TREE;
        String[] codes = huffmanCodesTable(root);

        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);
        // numero complessivo di caratteri
        int count = root.weight();  
        
        out.writeTextLine( "" + count );
        
        // Leggi il documento, calcola la lunghezza e scrivi i codici
        while (in.textAvailable()) {
            char c = in.readChar();
            if (c < 128) {
                out.writeCode(codes[c]);
            }
        }
        
        in.close();
        out.close();
    }


    public static void decompress(String src, String dst) {
        Node root = HUFFMAN_TREE;

        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);
        // Leggi la lunghezza del documento (Prima riga)
        int count = Integer.parseInt(in.readTextLine());

        for (int j = 0; j < count; j++) {
            char c = decodeNextChar(root, in);
            if (c != ','){ // NodeQueue giving a weird bug to write ',' at the end of file
                out.writeChar(c);
            } else{
                break;
            }
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
}