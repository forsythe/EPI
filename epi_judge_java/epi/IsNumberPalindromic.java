package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsNumberPalindromic {
    @EpiTest(testDataFile = "is_number_palindromic.tsv")
    public static boolean isPalindromeNumber(int x) {
        // TODO - you fill in here.
        if (x < 0)
            return false;

        int base = (int) Math.pow(10, Math.floor(Math.log10(x)));
        while (x > 0) {
            int lsd = x % 10;
            int msd = x / base;

//            System.out.printf("x=%d\n", x);
//            System.out.printf("lsd=%d, msd=%d\n", lsd, msd);

            if (lsd != msd)
                return false;

            x %= base;
            x /= 10;
//            System.out.printf("post x=%d\n", x);

            base /= 100;
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
