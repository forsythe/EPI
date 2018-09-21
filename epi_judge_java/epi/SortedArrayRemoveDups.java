package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.*;
import java.util.stream.Collectors;

public class SortedArrayRemoveDups {
    // Returns the number of valid entries after deletion.
    public static int deleteDuplicates(List<Integer> A) {
        // TODO - you fill in here.
//        Set<Integer> seen = new TreeSet<>(A);
//        A.clear();
//        A.addAll(seen);
//        return seen.size();

        if (A.isEmpty()) return 0;

        int lastKnownUniqueSpot = 0;
        for (int cur = 1; cur < A.size(); cur++) {
            if (!A.get(cur).equals(A.get(lastKnownUniqueSpot))) {
                A.set(++lastKnownUniqueSpot, A.get(cur));
            }
        }
        return lastKnownUniqueSpot + 1;
    }

    @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
    public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                        List<Integer> A)
            throws Exception {
        int end = executor.run(() -> deleteDuplicates(A));
        return A.subList(0, end);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
