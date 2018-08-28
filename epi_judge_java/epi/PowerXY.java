package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PowerXY {
    @EpiTest(testDataFile = "power_x_y.tsv")
    public static double power(double x, int y) {
        // TODO - you fill in here.
        //repeated squaring
        double ans = 1.0;
        long power = y;
        if (y < 0) {
            x = 1 / x;
            power = -power;
        }
        while (power != 0) {
            if ((power & 0x1) == 1) {
                ans *= x;
            }
            x *= x;

            power >>>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PowerXY.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
