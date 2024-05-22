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

          public static Node poll(){
                    Node minNode = heap[0];
                    heap[0] = heap[size--];
                    heap[size] = null;
                    heapifyToggle(0);
                    return minNode;
          }

          public static void add(Node n){
            if (size == heap.length){
              resize();
            }
            heap[size] = n;
            heapifyadd(size++);
          }
          // Transforming a head into a correct order after adding a new element
          private static void heapifyadd(int index){
            // To deepest
            while (index > 0){
                // Compare the val of the current leaf with it's parent 
                int parentIndex = (index - 1) / 2;
                if (heap[index].Weight() >= heap[parentIndex].Weight()){
                    break;
                }
                // If the current is less to it's parent swap them
                Node temp = heap[index];
                heap[index] = heap[parentIndex];
                heap[parentIndex] = temp;
            }
          }

          private static void heapifyToggle(int index){
            while(true){
              int leftChild = 2 * index + 1;
              int rightChild = 2 * index + 2;
              int small = index;
              //if (leftChild < size && heap[leftChild].Weight() < heap[small].Weight()){
                //small = leftChild;
              //}
              if (rightChild < size && heap[rightChild].Weight() < heap[leftChild].Weight()){
                small = rightChild;
              }
              if (small == index){
                break;
              }
              Node temp = heap[index];
              heap[index] = heap[small];
              heap[small] = temp;
              index = small;
            }
          }

          private static void resize(){
            Node[] newHeap = new Node[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap; 
          }
}