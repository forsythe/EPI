package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

    public static String intToString(int x) {
        // TODO - you fill in here.
        StringBuilder sb = new StringBuilder();
        boolean negative = x < 0;

        do {
            sb.append(Math.abs(x % 10));
            x /= 10;
        } while (x != 0);

        if (negative) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    public static int stringToInt(String s) {
        int pos = 1;
        int ans = 0;

        boolean isNegative = s.contains("-");
        if (isNegative) {
            s = s.substring(1);
        }
        for (int k = s.length() - 1; k >= 0; k--) {
            ans += (s.charAt(k) - '0') * pos;
            pos *= 10;
        }
        return isNegative ? -1 * ans : ans;
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    public static void wrapper(int x, String s) throws TestFailure {
        if (!intToString(x).equals(s)) {
            throw new TestFailure("Int to string conversion failed");
        }
        if (stringToInt(s) != x) {
            throw new TestFailure("String to int conversion failed");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
