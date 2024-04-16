 public class SList<T> {
          
          //public static final IntSList NULL_INTLIST = new IntSList();
          
          private final boolean empty;             // oggetti immutabili:
          private final T first;                 // variabili di istanza "final"
          private final SList<T> rest;
          
          public SList() {                      
                                                   
            empty = true;
            first = null;                             
            rest = null;
          }
          
          public SList( T e, SList<T> il ) { 
                                                   
            empty = false;
            first = e;
            rest = il;
          }
          
          
          public boolean isNull() {                
                                                  
            return empty;
          }
          
        
          public T car() {                       
                                                   
            return first;                          
          }
          
          
          public SList<T> cdr() {                  
                                                  
            return rest;                           
          }
          
          public SList<T> cons( T e ) {         
                                                  
            return new SList<T>( e, this );
          }
          
          public int length() {                    
                                                   
            if ( isNull() ) {                     
              return 0;
            } else {
              return ( 1 + cdr().length() );      
            }                                      
          }
          
          
          public T listRef( int k ) {           
                                                   
            if ( k == 0 ) {
              return car();                        
            } else {
              return ( cdr().listRef(k-1) );       
                                                   
            }
          }                                        
          
          
          public boolean equals( SList<T> il ) { 
                                                   
            if ( isNull() || il.isNull() ) {
              return ( isNull() && il.isNull() );
            } else if ( car() == il.car() ) {
              return cdr().equals( il.cdr() );
            } else {
              return false;
            }
          }
          
          
          public SList<T> append( SList<T> il ) {  // fusione di liste
                                                   // Scheme: append
            if ( isNull() ) {
              return il;
            } else {
              // return new IntSList( car(), cdr().append(il) );
              return ( cdr().append(il) ).cons( car() );
            }
          }
          
          
          public SList<T> reverse() {              // rovesciamento di una lista
                                                   // Scheme: reverse
            return reverseRec( new SList<T>() );
          }
          
          private SList<T> reverseRec( SList<T> re ) {
          
            if ( isNull() ) {                      // metodo di supporto: private!
              return re;
            } else {
              // return cdr().reverseRec( new IntSList(car(),re) );
              return cdr().reverseRec( re.cons(car()) );
            }
          }
          
          
          // ----- Rappresentazione testuale (String) di una lista
          
          public String toString() {               // ridefinizione del metodo generale
                                                   // per la visualizzazione testuale
            if ( isNull() ) {
              return "()";
            } else {
              String rep = "(" + car();
              SList<T> r = cdr();
              while ( !r.isNull() ) {
                rep = rep + ", " + r.car();
                r = r.cdr();
              }
              return ( rep + ")" );
            }
          }
        
        
        }  // class IntSList
        
        