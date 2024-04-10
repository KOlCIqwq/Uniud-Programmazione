package Lab11;

public class RoundTable {


  private final int num;                   // numero di cavalieri a tavola
  private final int jug;                   // etichetta del cavaliere con la brocca
  private final IntSList head;             // lista di cavalieri successivi (numerati)
  private final IntSList tail;             // lista rovesciata dei cavalieri rimanenti

  
  public RoundTable( int n ) {             // creazione di una tavola
                                           // con n cavalieri
    num = n;
    jug = 1;
    head = range( 2, n );
    tail = IntSList.NULL_INTLIST;
  }
  
  
  // ----- Costruttore non pubblico di supporto
  
  private RoundTable( int n, int j, IntSList h, IntSList t ) {
  
    num = n;
    jug = j;
    head = h;
    tail = t;
  }
  
  
  // ----- Metodi del protocollo: acquisizione di informazioni sulla configurazione
  
  public int numberOfKnights() {           // numero di cavalieri a tavola
  
    return num;
  }

  
  // ----- Metodi del protocollo: generazione di configurazioni successive
  
  //        (*)  n: c_1 () (c_k, ..., c_3, c_2)  -->  n-1: c_1 (c_3, c_4, ..., c_k) ()
  //       (**)  n: c_1 (c_2, ..., c_j) (c_k, ..., c_j+1)
  //                                   -->  n-1: c_1 (c_3, ..., c_j) (c_k, ..., c_j+1)
  
  public RoundTable serveNeighbour() {     // serve il commensale vicino a sinistra:
                                           // il commensale servito lascia la tavola
    if ( num < 2 ) {                       // meno di due commensali
      return this;
    } else if ( head.isNull() ) {          // (*)
      IntSList rev = tail.reverse();
      return new RoundTable( num-1, jug, rev.cdr(), IntSList.NULL_INTLIST );
    } else {                               // (**)
      return new RoundTable( num-1, jug, head.cdr(), tail );
    }
  }
  
  
  //        (*)  n: c_1 () (c_k, ..., c_3, c_2)  -->  n: c_2 (c_3, c_4, ..., c_k, c_1) ()
  //       (**)  n: c_1 (c_2, ..., c_j) (c_k, ..., c_j+1)
  //                                   -->  n: c_2 (c_3, ..., c_j) (c_1, c_k, ..., c_j+1)
  
  public RoundTable passJug() {            // passa la brocca al commensale vicino
                                           // (a sinistra)
    if ( num < 2 ) {                       // meno di due commensali
      return this;
    } else if ( head.isNull() ) {          // (*)
      IntSList rev = ( tail.cons(jug) ).reverse();
      return new RoundTable( num, rev.car(), rev.cdr(), IntSList.NULL_INTLIST );
    } else {                               // (**)
      return new RoundTable( num, head.car(), head.cdr(), tail.cons(jug) );
    }
  }
  
  
  // ----- Procedura interna di supporto (private!)
  
  private static IntSList range( int inf, int sup ) {
  
    if ( inf > sup ) {
      return IntSList.NULL_INTLIST;
    } else {
      return range( inf+1, sup ).cons( inf );
    }
  }

  public String servingKnights(){
    IntSList list = IntSList.NULL_INTLIST;
    // The result is returned as n+1 (maybe it's messed up with starting point), so -1 for the correct visualization
    list = list.cons(jug-1);
    list = list.cons(head.car()-1);
    //list = list.reverse();
    return list.toString();
  }

} 

