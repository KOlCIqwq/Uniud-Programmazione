public class SchemeToJava{
    public static void main(String[] args) {
        //System.out.println(btrSucc("-+-+"));
        System.out.println(onesComplement("0101"));
    }
    public static String btrSucc (String btr){
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
                return btrSucc(pre) + "-";
            }else{
                // If last char is - convert it to . else to +
                char a = (lsb == '-')? '.':'+';
                return pre + a;
            }
        }
    }
    public static String onesComplement(String bin){
        // Initialize result
        String result = "";
        // For to loop the string
        for (int i = 0; i < bin.length(); i++){
            if (bin.charAt(i) == '0'){
                // Append to the result char 1
                result += '1';
            } else{
                result += '0';
            }
        } return result;
    }
}
