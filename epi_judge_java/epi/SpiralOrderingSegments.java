package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderingSegments {
    enum Direction {UP, DOWN, LEFT, RIGHT}

    @EpiTest(testDataFile = "spiral_ordering_segments.tsv")
    public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        // TODO - you fill in here.
        List<Integer> ans = new ArrayList<>();
        int size = squareMatrix.size();
        final int SEEN = Integer.MAX_VALUE;

        int curR = 0, curC = 0;
        Direction curDir = Direction.RIGHT;

        for (int k = 0; k < size * size; k++) {
            ans.add(squareMatrix.get(curR).get(curC));
            squareMatrix.get(curR).set(curC, SEEN);
            switch (curDir) {
                case UP:
                    curR--;
                    break;
                case DOWN:
                    curR++;
                    break;
                case LEFT:
                    curC--;
                    break;
                case RIGHT:
                    curC++;
                    break;
            }

            switch (curDir) {
                case UP:
                    if (curR <= 0 || squareMatrix.get(curR - 1).get(curC) == SEEN)
                        curDir = Direction.RIGHT;
                    break;
                case DOWN:
                    if (curR >= size - 1 || squareMatrix.get(curR + 1).get(curC) == SEEN)
                        curDir = Direction.LEFT;
                    break;
                case LEFT:
                    if (curC <= 0 || squareMatrix.get(curR).get(curC - 1) == SEEN)
                        curDir = Direction.UP;
                    break;
                case RIGHT:
                    if (curC >= size - 1 || squareMatrix.get(curR).get(curC + 1) == SEEN)
                        curDir = Direction.DOWN;
                    break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SpiralOrderingSegments.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
