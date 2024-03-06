public class Lab1 {
    public static void main(String[] args) {
        System.out.println(btrSucc("-+-+"));
    }
    public static String btrSucc (String btr){
        int len = btr.length();
        char lsb = btr.charAt(len - 1);
        if (len == 1){
            if (lsb == '+'){
                return "+-";
            } else{
                return "+";
            }
        }else{
            String pre = btr.substring(0, len - 1);
            if (lsb == '+'){
                return btrSucc(pre) + "-";
            }else{
                char a = (lsb == '-')? '.':'+';
                return pre + a;
            }
        }
    }
}
