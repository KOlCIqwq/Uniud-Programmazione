package Lab10;

public class StringSList {
    private final boolean empty;
    private final String first;
    private final StringSList rest;

    public StringSList(){
        empty = true;
        first = "";
        rest = null;
    }

    public StringSList(String e, StringSList sl){
        empty = false;
        this.first = e;
        this.rest = sl;
    }

    public boolean isNull(){
        return empty;
    }

    public String car(){
        return first;
    }

    public StringSList cdr(){
        return rest;
    }

    public StringSList cons(String e){
        // Create a new list with e and current list
        return new StringSList(e, this);
    }

    public int length(){
        int i = 0; // Counter
        StringSList list = this;
        while (list != null){
            i += 1;
            list = list.cdr();
        }
        return i + 1;
    }

    public String listref(int k){
        int i = 0; // counter
        // Get current list
        StringSList list = this;
        // Increment i to k
        while (i != k){ 
            i += 1;
            // Update list
            list = list.cdr();
        }
        // First element is the kth element
        return list.first;
    }

    public boolean equals (StringSList sl){
        StringSList list = this;
        // If two list don't have same length, they cannot be equal
        if (sl.length() != list.length()){
            return false;
        }else if(list.isNull() || sl.isNull()){ // If one list is null
            return (list.isNull() && sl.isNull());
        }
        else{
            // Loop sl, can also be list (same length)
            while (sl != null){
                // If the first element is the same
                if (sl.car() == list.first){
                    // Update two list
                    sl = sl.cdr();
                    list = list.cdr();
                    // Return to while with lists updated
                    continue;
                } else{
                    // If not equal return false
                    return false;
                }
                // At the end of loop if all goes fine return true
            } return true;
        }
    }

    public StringSList append(StringSList sl){
        StringSList list = this;
        while (sl != null){
            // Cons the first element of sl to list
            list = list.cons(sl.car());
            // Update sl
            sl = sl.cdr();
        }
        return list;
    }

    public StringSList reverse(){
        StringSList list = this;
        // New empty List
        StringSList reversed = new StringSList();
        while (list != null){
            // cons the first element of list with this: pointing to reversed
            reversed = reversed.cons(list.car());
            // Update the list
            list = list.cdr();
        }
        return reversed;
    }

    public String toString(){
        String s = "";
        s = s + "[" + this.car(); // Fix the start ,
        StringSList list = this.rest; // Do the rest
        while (list != null){ // Loop list
            s = s + "," + list.car();
            list = list.cdr();
        }
        return s + "]";
    }
}
