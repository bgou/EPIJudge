package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {
      boolean isNeg = x < 0;
    String sign = (isNeg ? "-" : "");
    StringBuilder sb = new StringBuilder();
    do{
        int l = x%10;
      sb.append(Math.abs(l));
      x /= 10;
    } while (x != 0);

    return sign + sb.reverse().toString();
  }
  public static int stringToInt(String s) {
    return (s.charAt(0) == '-' ? -1 : 1) *
            s.substring(s.charAt(0) == '-' ? 1 : 0)
            .chars().reduce(0, (result, c) -> {
      return result * 10 + c-'0';
    });
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
      String res = intToString(x);
      if (!res.equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
