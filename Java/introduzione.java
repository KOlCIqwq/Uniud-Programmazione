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
          public class IntSList{
                    // final == tuple, rimane una costante

                    private final boolean empty;

                    private final int first;

                    private final IntSList rest;
                    
                    public IntSList(){
                              empty = true;
                              // Non serviva (Lo faceva java)
                              first = 0;
                              rest = null;
                    }
                    public IntSList(int e, IntSList r){
                              empty = false;
                              
                              first = e;
                              rest = r;
                    }
                    public boolean isNull(){
                              return empty;
                    }
                    public int car(){
                              return first;
                    }
                    public IntSList cdr(){
                              return rest;
                    }
                    public IntSList cons(int e){
                              // this cambia dinamicamente 
                              return new IntSList(e, this);
                    }
          }
} 