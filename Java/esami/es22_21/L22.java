package esami.es22_21;

import java.util.Arrays;

public class L22 {
    public static void main(String[] args) {
        double[] heap = {5.0, 3.1, 5.7, 3.1, 8.5, 6.0, 3.0, 4.2, 9.3};
        System.out.println(Arrays.toString(heapTest(heap)));
    }

    //1.
    public static int[] heapTest(double[] list){
        for (int i = 1; i < list.length/2; i++){
            int left = 2 * i;
            int right = left + 1;
            if (list[i] > list[left]){
                return new int[] {i,left};
            }
            if (list[i] > list[right] && right < list.length){
                return new int[] {i, right};
            }
        }
        return null;
    }
}

