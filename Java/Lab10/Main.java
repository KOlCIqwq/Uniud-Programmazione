package Lab10;

public class Main {
    public static void main(String[] args) {
        /*StringSList list = new StringSList("Test0", null);
        list = list.cons("1");
        System.out.println(list.toString());
         System.out.println(list.car());
        StringSList list2 = new StringSList("Test0", null);
        list2 = list2.cons("1");
        System.out.println(list.equals(list2)); */
        StringSList btrlist = btrplus("+-", 5);
        // The last dot of the list (reversed) need to be fixed
        System.out.println(btrlist.reverse().toString());

    }

    public static StringSList btrplus (String btr, int n){
        StringSList btrlist = new StringSList(btr, null);
        for (int i = 1; i < n; i++){
            btrlist = btrlist.cons(Succ(btr));
            btr = Succ((btr));
        }
        return btrlist;
    }

    public static String Succ(String btr){
        if (btr.length() == 1){
            if(btr.charAt(btr.length() - 1) == '+'){
                return "+-";
            } else{
                return "+";
            }
        }
        if (btr.charAt(btr.length() - 1) == '+'){
            return Succ(btr.substring(0, btr.length() - 1)) + "-";

        } else{
            if (btr.charAt(btr.length() - 1) == '-'){
                return btr.substring(0, btr.length() - 1) + ".";
            } else{
                return btr.substring(0, btr.length() - 1) + "+";
            }
        }
    }
}
