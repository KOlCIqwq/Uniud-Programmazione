package Huffman;
import java.util.*; // Priority queue
import java.util.Arrays;

import huffman_toolkit.InputTextFile;
import huffman_toolkit.OutputTextFile;

public class Huffmanclasse {
    public static void main(String[] args) {
        int[] x = checkFreq("Java/Huffman/Main.java");
        //System.out.println(Arrays.toString(x));
        //System.out.println(huffmanTree(x));
        //compress("Java/Huffman/Main.java", "Java/Huffman/dst.txt");
        Node y = huffmanTree(x);
        String s = flatTree(y);
        System.out.println(s);
        //System.out.println(y);
        //decompress("Java/Huffman/dst.txt", "Java/Huffman/src.txt");
        
    }
    // Check the frequency of each char
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
    // Construct the huffmanTree
    public static Node huffmanTree(int[] freq){
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        for (int i = 0; i < freq.length; i++){
            if (freq[i] > 0){
                queue.add(new Node((char) i, freq[i]));
            }
        }
        while (queue.size() > 1){
            Node left = queue.poll();
            Node right = queue.poll();
            Node merge = new Node(left, right);
            queue.add(merge);
        }
        return queue.poll();
    }
    // Fill the table
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
    // Compress a file with new coded bits
    public static void compress (String src, String dst){
        int[] freq = checkFreq(src);
        Node tree = huffmanTree(freq);
        String[] table = huffmantable(tree);
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);

        out.writeTextLine("" + tree.Weight());
        out.writeTextLine(flatTree(tree));

        while (in.textAvailable()){
            char c = in.readChar();
            out.writeCode(table[c]);
        }
        in.close();
        out.close();
        
    }
    // The opposite
    public static void decompress (String src, String dst){
        
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);
        int count = Integer.parseInt(in.readTextLine());
        Node root = restoreTree(in);
        // skip a line 
        in.readTextLine();
        for (int i = 0; i < count; i++){
            char c = restoreChar(in,root);
            out.writeChar(c);
        }
        in.close();
        out.close();
    }

    private static char restoreChar(InputTextFile in, Node root){
        do{
            int bit = in.readBit();
            if (bit == 0){
                root = root.Left();
            } else{
                root = root.Right();
            }
        } while(!root.IsLeaf());
        return root.symbol();
    }

    private static Node restoreTree(InputTextFile in){
        Node n = new Node(null, null);
        Stack<Frame> stack = new Stack<Frame>();
        stack.push(new Frame());
        while (!stack.isEmpty()){
            Frame f = stack.peek();
            if (f.getState() == 0){
                char c = in.readChar();
                if(c == '@'){
                    f.setState(1);
                    stack.push(new Frame());
                } else{
                    if (c == '\\'){
                        c = in.readChar();
                    }
                    n = new Node (c, 0);
                    stack.pop();
                }
            } else if (f.getState() == 1){
                f.setSin(n);
                stack.push(new Frame());
                f.setState(2);
            } else{
                f.setDes(n);
                n = new Node(f.getSin(), f.getDes());
                stack.pop();
            }
        }
        return n;
    }
    // Write the huffeman tree as a flat text
    public static String flatTree(Node n){
        Stack<Node> stack = new Stack<Node>();
        stack.push(n);
        String out = "";
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            if (n.IsLeaf()){
                char c = node.symbol();
                // if it's '\' means a special char
                if ((c == '@') || (c == '\\')){
                    out += "\\" + c;
                } else{
                    out += c;
                }
            } else{
                out += "@";
                stack.push(node.Right());
                stack.push(node.Left()); 
            }
        }
        return out;
    }
}
