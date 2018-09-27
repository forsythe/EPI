package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
    /**
     * @param numAsString an integer in base b1
     * @param b1          the original base
     * @param b2          the base to be converted to
     * @return the string of the integer in the new base (b2)
     */
    @EpiTest(testDataFile = "convert_base.tsv")
    public static String convertBase(String numAsString, int b1, int b2) {
        boolean isNegative = numAsString.contains("-");
        if (isNegative) {
            numAsString = numAsString.substring(1);
        }
        int num = 0;
        for (int i = numAsString.length() - 1; i >= 0; --i) {
            var val = Character.isDigit(numAsString.charAt(i)) ?
                    numAsString.charAt(i) - '0' :
                    numAsString.charAt(i) - 'A' + 10;
            num += val * Math.pow(b1, numAsString.length() - 1 - i);
        }

        StringBuilder sb = new StringBuilder();

        do {
            char temp = num % b2 < 10 ?
                    (char) ((num % b2) + '0')
                    : (char) ((num % b2) - 10 + 'A');
            sb.append(temp);
            num /= b2;
        } while (num != 0);
        if (isNegative) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
