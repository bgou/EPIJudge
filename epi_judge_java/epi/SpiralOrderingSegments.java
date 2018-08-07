package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SpiralOrderingSegments {
  @EpiTest(testDataFile = "spiral_ordering_segments.tsv")

  public static List<Integer>
  matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    List<Integer> result = new ArrayList<>();
    for (int offset = 0; offset < Math.ceil(squareMatrix.size() / 2.0); offset++) {
      goAround(offset, squareMatrix, result);
    }
    return result;
  }

  private static void goAround(int offset, List<List<Integer>> squareMatrix, List<Integer> result) {
    if (offset == squareMatrix.size() - 1 - offset) {
      result.add(squareMatrix.get(offset).get(offset));
      return;
    }

    int size = squareMatrix.size();
    int bound = size - 1 - offset;
    for (int i = offset; i < bound; i++) {
      result.add(squareMatrix.get(offset).get(i));
    }

    for (int j = offset; j < bound; j++) {
      result.add(squareMatrix.get(j).get(bound));
    }

    for (int i = bound; i > offset ; i--) {
      result.add(squareMatrix.get(bound).get(i));
    }

    for (int j = bound; j > offset ; j--) {
      result.add(squareMatrix.get(j).get(offset));
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrderingSegments.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
