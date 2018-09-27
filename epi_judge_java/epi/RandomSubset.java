package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class RandomSubset {

    // Returns a random k-sized subset of {0, 1, ..., n - 1}.
    public static List<Integer> randomSubset(int n, int k) {
        // TODO - you fill in here.
        Random rd = new Random();
        Map<Integer, Integer> index = new HashMap<>();
        for (int j = 0; j < k; j++) {
            int pos = rd.nextInt(n - j) + j;
            if (!index.containsKey(pos) && !index.containsKey(j)) {
                index.put(pos, j);
                index.put(j, pos);
            } else if (!index.containsKey(pos) && index.containsKey(j)) {
                var temp = index.get(j);
                index.put(j, pos);
                index.put(pos, temp);
            } else if (index.containsKey(pos) && !index.containsKey(j)) {
                var temp = index.get(pos);
                index.put(pos, j);
                index.put(j, temp);
            } else {
                var temp1 = index.get(pos);
                var temp2 = index.get(j);
                index.put(j, temp1);
                index.put(pos, temp2);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(index.get(i));
        }
        return ans;
    }

    private static boolean randomSubsetRunner(TimedExecutor executor, int n,
                                              int k) throws Exception {
        List<List<Integer>> results = new ArrayList<>();

        executor.run(() -> {
            for (int i = 0; i < 1000000; ++i) {
                results.add(randomSubset(n, k));
            }
        });

        int totalPossibleOutcomes = RandomSequenceChecker.binomialCoefficient(n, k);
        List<Integer> A = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            A.add(i);
        }
        List<List<Integer>> combinations = new ArrayList<>();
        for (int i = 0; i < RandomSequenceChecker.binomialCoefficient(n, k); ++i) {
            combinations.add(RandomSequenceChecker.computeCombinationIdx(A, n, k, i));
        }
        List<Integer> sequence = new ArrayList<>();
        for (List<Integer> result : results) {
            Collections.sort(result);
            sequence.add(combinations.indexOf(result));
        }
        return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
                sequence, totalPossibleOutcomes, 0.01);
    }

    @EpiTest(testDataFile = "random_subset.tsv")
    public static void randomSubsetWrapper(TimedExecutor executor, int n, int k)
            throws Exception {
        RandomSequenceChecker.runFuncWithRetries(
                () -> randomSubsetRunner(executor, n, k));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RandomSubset.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
