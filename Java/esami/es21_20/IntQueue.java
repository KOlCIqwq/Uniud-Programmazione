package esami.es21_20;

public class IntQueue {
    public static void main(String[] args) {
        IntQueue q = new IntQueue(5); // Coda con capacità massima di 5
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.peek()); // Output: 1
        System.out.println(q.poll()); // Output: 1
        System.out.println(q.peek()); // Output: 2
        q.add(4);
        q.add(5);
        q.add(6);
        q.add(7); // Non viene aggiunto perché la capacità massima è stata raggiunta
        System.out.println(q.size()); // Output: 4
    }

    private int[] list;
    private int size;
    private int capacity;
    private int newPointer;
    private int lastPointer;

    public IntQueue(int n){
        list = new int[capacity];
        size = 0;
        capacity = n;
        newPointer = 0;
        lastPointer = -1;
    }

    public int size(){
        return size;
    }

    public void add(int n){
        /*if (size != capacity){
            int[] newList = new int[size+1];
            newList[0] = n;
            for (int i = 1; i < list.length; i++){
                newList[i] = list[i];
            }
            newList = list;
            size++;
        }else{
            System.err.println("Reached max capacity");
        }*/

        if (size >= capacity){
            System.err.println("Reached max capacity");
            return;
        }
        lastPointer = lastPointer + 1 % capacity;
        list[lastPointer] = n;
        size++;
    }

    public int peek(){
        return list[newPointer];
    }

    public int poll(){
        /*int[] newList = new int[size];
        for (int i = 0; i < list.length-1; i++){
            newList[i] = list[i];
        }
        newList = list;
        size--;
        return list[size];*/
        int last = list[newPointer];
        newPointer = (newPointer+1) % capacity;
        size--;
        return last;
    }
}
