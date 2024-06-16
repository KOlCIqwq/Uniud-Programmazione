package esami;

public class G21 {
    public static void main(String[] args) {
        System.out.println(llmds(new double[] {10.0, 5.0, 2.5, 1.5, 0.75, 0.5}));
    }

    //1.
    public static int llmds(double[] s) {
        return llmdsRec(s, 0, 123);
    }

    private static int llmdsRec(double[] s, int i, double t) {
        if (i == s.length) {
            return 0;
        } else if (s[i] <= t && s[i] > 0.5 * t) {
            return llmdsRec(s, i + 1, t);
        } else {
            return Math.max(1 + llmdsRec(s, i + 1, s[i]), llmdsRec(s, i + 1, t));
        }
    }

    //1.2   
    // 1st cycle: i = 0, t = 123 -> 123/2 <= s[i] < 123
    // 2nd cycle: i = 1, t = 7.5 -> 7.5/2 <= s[i] < 7.5
    // 3rd cycle: i = 2, t = 4.5 -> max(1 + llmdsRec(s, 3, 2.7), llmdsRec(s, 3, 4.5))
    // 4th cycle: i = 3, t = 2.7||4.5 -> max(1+ llmdsRec(s, 4, 4.5), llmdsRec(s,4,2.7)||llmdsRec(s,4,4.5))
    // 5th cycle: i = 4, t = 4.5||2.7 -> max(1+ llmdsRec(s,5,7.5), llmdsRec(s,5,2.7||llmdsRec(s,5,4.5)))
    // 6th cycle: i = 5, t = 7.5||2.7||4.5 -> i == s.length -> 0

    //
}
