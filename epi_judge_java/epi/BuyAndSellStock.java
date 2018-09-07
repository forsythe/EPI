package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class BuyAndSellStock {
    @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
    public static double computeMaxProfit(List<Double> prices) {
        // TODO - you fill in here.
        double minSoFar = prices.get(0);
        double maxProfit = 0;
        for (int k = 1; k < prices.size(); k++) {
            minSoFar = Math.min(minSoFar, prices.get(k));
            double profit = prices.get(k) - minSoFar;
            maxProfit = Math.max(profit, maxProfit);
        }
        return maxProfit;
    }

    /**
     * my nlogn solution. can be done better in linear time.
     */
    private static double maxRecursive(List<Double> prices) {
        if (prices.size() <= 1) {
            return 0;
        }

        int halfIndex = prices.size() / 2;
        double min = Collections.min(prices.subList(0, halfIndex));
        double max = Collections.max(prices.subList(halfIndex, prices.size()));
        return Math.max(
                max - min,
                Math.max(
                        maxRecursive(prices.subList(0, halfIndex)),
                        maxRecursive(prices.subList(halfIndex, prices.size()))
                ));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
