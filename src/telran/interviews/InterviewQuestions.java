package telran.interviews;

import java.util.*;

public class InterviewQuestions {

    public static void displayOccurrences(String[] strings) {
        HashMap<String, Integer> mapOccurrences = getOccurrencesMap(strings);
        TreeMap<Integer, TreeSet<String>> treeMapOccurrences = getTreeMapOccurrences(mapOccurrences);
        displayOccurrences(treeMapOccurrences);
    }

    private static void displayOccurrences(TreeMap<Integer, TreeSet<String>> treeMapOccurrences) {
        treeMapOccurrences.entrySet().forEach(e -> {
            e.getValue().forEach(str -> System.out.printf("%s => %d\n", str, e.getKey()));
        });
    }

    private static TreeMap<Integer, TreeSet<String>> getTreeMapOccurrences(HashMap<String, Integer> mapOccurrences) {
        TreeMap<Integer, TreeSet<String>> result = new TreeMap<>(Comparator.reverseOrder());
        mapOccurrences.entrySet().forEach(e -> result.computeIfAbsent(e.getValue(), k -> new TreeSet<>()).add(e.getKey()));
        return result;
    }

    private static HashMap<String, Integer> getOccurrencesMap(String[] strings) {
        HashMap<String, Integer> result = new HashMap<>();
        for (String str : strings) {
            result.merge(str, 1, Integer::sum);
        }
        return result;
    }

    public static boolean isSum2(int[] array, int sum) {
        Set<Integer> seen = new HashSet<>();
        boolean found = false;
        for (int num : array) {
            if (seen.contains(sum - num)) {
                found = true;
            }
            seen.add(num);
        }
        return found;
    }


    public static int getMaxWithNegativePresentation(int[] array) {
        Set<Integer> positives = new HashSet<>();
        Set<Integer> negatives = new HashSet<>();
        for (int num : array) {
            if (num > 0) {
                positives.add(num);
            } else {
                negatives.add(num);
            }
        }

        int max = -1;
        for (int positive : positives) {
            if (negatives.contains(-positive)) {
                max = Math.max(max, positive);
            }
        }

        return max;
    }
}
