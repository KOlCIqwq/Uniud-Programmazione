 

public class Main {
    public static void main(String[] args) {
        StringSList btrlist = btrplus("+-", 5);
        // The last dot of the list (reversed) need to be fixed
        System.out.println(btrlist.reverse().toString());
    }

    public static StringSList btrplus (String btr, int n){
        // Create new list
        StringSList btrlist = new StringSList(btr, null);
        for (int i = 1; i < n; i++){
            // Append the result of btr with calculation
            btrlist = btrlist.cons(Succ(btr));
            // Update btr
            btr = Succ((btr));
        }
        return btrlist;
    }

    public static String Succ(String btr){
        int len = btr.length();
        char lsb = btr.charAt(len - 1); // Last Char of btr
        if (len == 1){ 
            if (lsb == '+'){
                return "+-";
            } else{ 
                return "+";
            }
        }else{
            // pre is the remaining part (slicing last char)
            String pre = btr.substring(0, len - 1);
            if (lsb == '+'){
                return Succ(pre) + "-";
            }else{
                // If last char is - convert it to . else to +
                char a = (lsb == '-')? '.':'+';
                return pre + a;
            }
        }
    }
}
