package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseDigits {
    @EpiTest(testDataFile = "reverse_digits.tsv")
    public static long reverse(int x) {
        // TODO - you fill in here.
        long ans = 0;
        while (x != 0) {
            ans *= 10;
            ans += x % 10;
            x /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
