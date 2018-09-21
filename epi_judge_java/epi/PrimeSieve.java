package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimeSieve {
    @EpiTest(testDataFile = "prime_sieve.tsv")
    // Given n, return all primes up to and including n.
    public static List<Integer> generatePrimes(int n) {
        // TODO - you fill in here.
        boolean[] isNotPrime = new boolean[n + 1];
        isNotPrime[0] = isNotPrime[1] = isNotPrime[2] = true;

        for (int multiple = 2; multiple < n; multiple++) {
            for (int k = 1; k <= n; k += multiple) {
                isNotPrime[k] = true;
            }
        }
        Stream<Integer> stream = IntStream.range(1, n).filter(i -> !isNotPrime[i]).boxed();
        return stream.collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
