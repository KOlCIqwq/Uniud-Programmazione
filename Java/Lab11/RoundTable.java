package Lab11;

public class RoundTable {
    private IntSList knights;
    private int current;

    public RoundTable(int n) {
        knights = IntSList.NULL_INTLIST;
        for (int i = n; i >= 1; i--) {
            knights = knights.cons(i);
        }
        current = 0;
    }

    public int numberOfKnights() {
        return knights.length();
    }

    public String servingKnights() {
        return knights.toString();
    }

    public RoundTable serveNeighbour() {
        // Find the index of the knight to remove, jumping 2 knights
        // % knights.length() to prevent overflow
        // if current + 1 = 4 and knights.length = 4, we can't point to the 4th element since it goes outOfRange
        // so 4%4=0 and the current pointer correctly points to the first element as a circle
        int toRemoveIndex = (current + 2) % knights.length();
        // Remove knight at index
        knights = removeAtIndex(knights, toRemoveIndex);
        // Suppose we have [1,2,3,4], if I want to remove 3 (index = 2) but the current is 3 (last element)
        // The list after removeval will be [1,2,4] and current can't still be 3, otherwise it will go outOfRange
        if (toRemoveIndex < current) {
            current--;
        }
        // Update the current passing to the next knight
        current = (current + 1) % knights.length();
        return this;
    }

    public RoundTable passJug() {
        // Jump 1 knight
        current = (current + 1) % knights.length();
        return this;
    }

    // Helper function to remove the element of list at index
    private IntSList removeAtIndex(IntSList list, int index) {
        if (index == 0) {
            return list.cdr();
        } else {
            // Recursion
            return new IntSList(list.car(), removeAtIndex(list.cdr(), index - 1));
        }
    }
}