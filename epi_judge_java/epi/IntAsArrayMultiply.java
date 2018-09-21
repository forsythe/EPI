package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntAsArrayMultiply {
    @EpiTest(testDataFile = "int_as_array_multiply.tsv")
    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
        // TODO - you fill in here.
        int sign = (int) (Math.signum(num1.get(0)) * Math.signum(num2.get(0)));
        num1.set(0, Math.abs(num1.get(0)));
        num2.set(0, Math.abs(num2.get(0)));

        int length = num1.size() + num2.size();
        int[] ans = new int[length];
        for (int bottom = num1.size() - 1; bottom >= 0; bottom--) {
            for (int top = num2.size() - 1; top >= 0; top--) {
                var temp = num1.get(bottom) * num2.get(top);
                ans[bottom + top + 1] += temp;
                ans[bottom + top] += ans[bottom + top + 1] / 10;
                ans[bottom + top + 1] %= 10;
            }
        }
        List<Integer> tempAns = Arrays.stream(ans).boxed().collect(Collectors.toList());
        while (tempAns.size() > 1 && tempAns.get(0) == 0) {
            tempAns.remove(0);
        }
        tempAns.set(0, tempAns.get(0) * sign);
        return tempAns;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
