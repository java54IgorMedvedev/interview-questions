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
        int i = 0;
        boolean found = false;
        while (i < array.length && !found) {
            int num = array[i];
            if (seen.contains(sum - num)) {
                found = true;
            } else {
                seen.add(num);
                i++;
            }
        }
        return found;
    }

    public static int getMaxWithNegativePresentation(int[] array) {
        Set<Integer> values = new HashSet<>();
        int max = -1;
        for (int num : array) {
            if (values.contains(-num)) {
                max = Math.max(max, Math.abs(num));
            }
            values.add(num);
        }
        return max;
    }
}
