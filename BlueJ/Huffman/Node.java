 

public class Node implements Comparable<Node> {

    private final char chr;
    private final int weight;
    private final Node lft;
    private final Node rght;

    public Node(char c, int w){
        chr = c;
        weight = w;
        lft = null;
        rght = null;
    }

    public Node(Node left, Node right){
        chr = (char)0;
        weight = left.weight() + right.weight();
        lft = left;
        rght = right;
    }

    public char symbol(){
        return chr;
    }

    public boolean isLeaf(){
        return (lft == null);
    }

    public int weight(){
        return weight;
    }

    public Node left(){
        return lft;
    }

    public Node right(){
        return rght;
    }

    public int compareTo(Node n){
        if (weight() < n.weight()){
            return -1;
        } else if (weight() == n.weight()){
            return 0;
        } else{
            return 1;
        }
    }
}
