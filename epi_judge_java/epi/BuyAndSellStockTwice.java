package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {  // calculate forward looking
    List<Double> maxSingleList = new ArrayList<>();
    double minSoFar = Double.MAX_VALUE;
    double maxTotalProfit = 0;
    for (double i : prices) {
      minSoFar = Math.min(minSoFar, i);
      maxTotalProfit = Math.max(maxTotalProfit, i-minSoFar);
      maxSingleList.add(maxTotalProfit);
    }

    double maxSoFar = Double.MIN_VALUE;
    for (int i=prices.size()-1; i>0; --i) {
      maxSoFar = Math.max(maxSoFar, prices.get(i));
      maxTotalProfit = Math.max(maxTotalProfit, maxSingleList.get(i-1) + maxSoFar - prices.get(i));
    }

    return maxTotalProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
