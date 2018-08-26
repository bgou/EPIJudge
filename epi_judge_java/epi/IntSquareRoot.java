package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    long L=0, R = k;
    while (L <= R) {
      long M = L + (R-L)/2;
      if (M*M >k) {
        R = M-1;
      }
      else if (M*M <=k) {
        L = M + 1;
      }

    }
    return (int)L-1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
