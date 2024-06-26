 

class NodeQueue{
  // An unbounded priority queue based on a priority heap (definition by oracle)
    private static Node[] heap;
    private static int size;

    public NodeQueue(){
      // Create a new Heap with 10 Nodes
      heap = new Node[10];
      size = 0;
    }

    public static int size(){
      return size;
    }

    // First element
    public static Node peek(){
      if (size == 0){
        return null;
      }
      return heap[0];
    }

    // Toggle the first element
    public static Node poll(){
      if (size == 0) {
        return null;
      }
      //heap = [0,1,2,3,4,5,6], min = heap[0] = 0, heap[0] = [6] = 6, size = 7-1
      Node min = heap[0];
      heap[0] = heap[size - 1];
      size--;
      heapifyToggle(0);
      return min;
    }

    // Add an element
    public static void add(Node n){
      if (size == heap.length){
        resize();
      }
      heap[size] = n;
      size++;
      heapifyadd(size-1);
    }
    
    // Transforming a head into a correct order after adding a new element
    private static void heapifyadd(int i){
      // Loop to the top
      while (i > 0){
          // Compare the val of the current leaf with it's parent 
          int parent = (i - 1) / 2;
          if (heap[i].weight() >= heap[parent].weight()){
              break;
          }
          // If the current is less to it's parent swap them
          swap(i, parent);
          i = parent;
      }
    }

    //Suppose we have a heap = [0,1,2,3,4,5,6], with poll() called new heap will be = [6,1,2,3,4,5], first cycle will be:
    // left = 2*0+1 = 1, right = 2, small = left. heap[right].weight() = 2 !< left; so small = left
    // heap[0] = 6 !<= heap[1] = 1 so no break
    // Swap the values of index, heap = [1,6,2,3,4,5]...
    // It continues to index = 3, heap = [1,3,2,6,4,5] and 3 < 3 so it ends:
    private static void heapifyToggle(int i){
      while (i < size / 2) { // Meaning the biggest weigth has arrived at the last leaf
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        // If we did not arrived at the last layer of leaf, then we need to compare 2 leaf's weight
        // At the start we suppose that the left is smaller than right
        int smallest = left;
        // If left is not smaller than right, we change the smallest number's index to right leaf
        if (right < size && heap[right].weight() < heap[left].weight()) {
            smallest = right;
        }
        
        // If the biggest number picked is smaller than the left/right leaf meaning the heap is not well built, break throwing an error
        if (heap[i].weight() <= heap[smallest].weight()) {
            //System.err.println("Heap is not organized");
            break;
        }
                
        swap(i, smallest);
        // Update the biggest number index 
        i = smallest;
      }
    }

    private static void resize(){
      Node[] newHeap = new Node[heap.length * 2];
      System.arraycopy(heap, 0, newHeap, 0, heap.length);
      heap = newHeap; 
    }

    private static void swap(int start, int end){
      Node temp = heap[start];
      heap[start] = heap[end];
      heap[end] = temp;
    }
}
