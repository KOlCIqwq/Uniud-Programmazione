package esami;

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
        weight = left.Weight() + right.Weight();
        lft = left;
        rght = right;
    }

    public char symbol(){
        return chr;
    }

    public boolean IsLeaf(){
        return (lft == null);
    }

    public int Weight(){
        return weight;
    }

    public Node Left(){
        return lft;
    }

    public Node Right(){
        return rght;
    }

    public int compareTo(Node n){
        if (Weight() < n.Weight()){
            return -1;
        } else if (Weight() == n.Weight()){
            return 0;
        } else{
            return 1;
        }
    }
}
