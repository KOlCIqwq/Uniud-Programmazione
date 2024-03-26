public class TavolaRotonda{

          private final int quanti;
          private final int brocca;
          private final IntSList altri;

          public TavolaRotonda(int n){
                    quanti = n;
                    brocca = 1;
                    altri = MainIntSList.intervallo(2, n);
          }

          private TavolaRotonda(int q, int b, IntSList a){
                    quanti = q;
                    brocca = b;
                    altri = a;
          }

          public int quantiCavalieri(){
                    return quanti;
          }

          public int chihaBrocca(){
                    return brocca;
          }

          public TavolaRotonda Serve(){
                    return new TavolaRotonda(quanti-1, brocca, altri.cdr());
          }

          public TavolaRotonda Passa(){
                    IntSList coda = ;
                    return new TavolaRotonda(quanti, altri.car(), altri.);
          }
}