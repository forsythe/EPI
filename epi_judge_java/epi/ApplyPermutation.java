package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApplyPermutation {
    public static void applyPermutation(List<Integer> perm, List<Integer> A) {
        // TODO - you fill in here.
        for (int k = 0; k < A.size(); k++) {
            int curPos = k; //index of original element (currently stored at k)
            int destPos = perm.get(curPos);
            while (destPos != -1) {
                Collections.swap(A, k, destPos);
                perm.set(curPos, -1); //original element has no more destPos (since it's in the right spot already)
                curPos = destPos;
                destPos = perm.get(curPos);
            }
        }
    }

    @EpiTest(testDataFile = "apply_permutation.tsv")
    public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                        List<Integer> A) {
        applyPermutation(perm, A);
        return A;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
