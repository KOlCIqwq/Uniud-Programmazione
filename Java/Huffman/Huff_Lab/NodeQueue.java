package Huffman.Huff_Lab;

class NodeQueue{
          private static Node[] heap;
          private static int size;

          public NodeQueue(){
                    heap = new Node[10];
                    size = 0;
          }

          public static int size(){
                    return size;
          }

          public static Node peek(){
                    if (size == 0){
                              return null;
                    }
                    return heap[0];
          }

          public static Node pool(){
                    Node minNode = heap[0];
                    heap[0] = heap[--size];
                    heap[size] = null;
                    
          }
}