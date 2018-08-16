package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {
    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        List<Iterator<Integer>> iters = new ArrayList<>();
        for (List<Integer> arr : sortedArrays) {
            iters.add(arr.iterator());
        }
        PriorityQueue<ArrayNode> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));

        for (int i = 0; i < iters.size(); i++) {
            if (iters.get(i).hasNext()) {
                q.add(new ArrayNode(iters.get(i).next(), i));
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            ArrayNode curNode = q.poll();
            result.add(curNode.value);
            if (iters.get(curNode.arrayIndex).hasNext()) {
                q.add(new ArrayNode(iters.get(curNode.arrayIndex).next(), curNode.arrayIndex));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args,
                                                   "SortedArraysMerge.java",
                                                   new Object() {}.getClass().getEnclosingClass()).ordinal());
    }

    public static class ArrayNode {
        public int value;
        public int arrayIndex;

        public ArrayNode(int value, int index) {
            this.value = value;
            this.arrayIndex = index;
        }
    }
}
