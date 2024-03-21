public class introduzione{
          public static void main (String[] args){
                    //System.out.println(supTotCilindro(4, 5));
                    System.out.println(isFemminile("ciao"));
          }
          public static double supTotCilindro (double r, double h){
                    return ((2 * Math.PI * r) + (r + h));
          }
          public static boolean isFemminile(String s){
                    return (s.charAt(s.length() - 1) == 'a');
          }
} 