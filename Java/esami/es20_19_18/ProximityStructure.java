package esami.es20_19_18;

public class ProximityStructure {
    public static void main(String[] args) {
        ProximityStructure s = new ProximityStructure();
        s.add(1.0);
        s.add(2.0);
        s.add(3.0);
        System.out.println( s.size());
        System.out.println(s.removeClosestTo(2.5));
        System.out.println(s.size());
    }
    private double[] list;
    private int size;

    public ProximityStructure(){
        list = new double[10];
        size = 0;
    }
    public int size(){
        return size;
    }
    public void add(double x) {
        double[] newlist = new double[list.length + 1];
        for (int i = 0; i <= size; i++){
            newlist[i] = list[i];
        }
        list = newlist;
        
        list[size] = x;
        size++;
    }
    public double removeClosestTo(double x){
        int index = 0;
        double closest = Math.abs(list[0] - x);
        for (int i = 1; i < size; i++){
            double diff = Math.abs(list[i] - x);
            if (diff < closest){
                closest = diff;
                index = i;
            }
        }
        double closestVal = list[index];
        double[] newList = new double[size];
        for (int i = 0; i < size; i++){
            if(i == index){
                continue;
            }
            newList[i] = list[i];
        }
        size--;
        list = newList;
        return closestVal;
    }
}
