package esami.es22_21;

public class L22 {
    public static void main(String[] args) {
        
        System.out.println(heapTest( new double[] {5.0, 3.1, 5.7, 3.1, 8.5, 6.0, 3.0, 4.2, 9.3} ));
    }

    //1.
    public static double[] heapTest(double[] list){
        double[] out = new double[2];
        for (int i = 0; i < list.length/2; i++){
            int left = 2 * i;
            int right = left + 1;
            if (list[i] > list[left]){
                out[0] = list[left];
            }
            if (right > list.length){
                return out;
            }
            if (list[i] > list[right]){
                out[1] = list[right];
            }
        }
        return (out == null)? out : null;
    }
}
