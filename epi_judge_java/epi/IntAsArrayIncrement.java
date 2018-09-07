package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IntAsArrayIncrement {
    @EpiTest(testDataFile = "int_as_array_increment.tsv")
    public static List<Integer> plusOne(List<Integer> A) {
        // TODO - you fill in here.
        boolean hasCarry = true;
        for (int k = A.size() - 1; k >= 0; k--) {
            A.set(k, A.get(k) + (hasCarry ? 1 : 0));
            if (A.get(k) >= 10) {
                A.set(k, A.get(k) % 10);
                hasCarry = true;
            } else {
                hasCarry = false;
            }
        }
        if (hasCarry) {
            A.add(0, 1);
        }
        return A;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
