package telran.interviews;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
public class InterviewQuestions {
public static void displayOccurrences(String [] strings) {
	HashMap<String, Integer> mapOccurrences = getOccurrencesMap(strings);
	TreeMap<Integer, TreeSet<String>> treeMapOccurrences = getTreeMapOccurrences(mapOccurrences);
	displayOccurrences(treeMapOccurrences);
}
public static void displayOccurrencesStream(String[] strings) {
	Arrays.stream(strings)
	.collect(Collectors.groupingBy(s -> s, Collectors.counting()))
	.entrySet().stream().sorted((e1, e2) -> {
		int res = Long.compare(e2.getValue(), e1.getValue());
		return res == 0 ? e1.getKey().compareTo(e2.getKey()) : res;
	}).forEachOrdered(e -> System.out.printf("%s -> %d\n", e.getKey(), e.getValue()));
}

private static void displayOccurrences(TreeMap<Integer, TreeSet<String>> treeMapOccurrences) {
	treeMapOccurrences.entrySet().forEach(e -> {
		e.getValue().forEach(str -> System.out.printf("%s => %d\n",str, e.getKey()));
	});
	
}

private static TreeMap<Integer, TreeSet<String>> getTreeMapOccurrences(HashMap<String, Integer> mapOccurrences) {
	TreeMap<Integer, TreeSet<String>> result =
			new TreeMap<Integer, TreeSet<String>>(Comparator.reverseOrder());
	mapOccurrences.entrySet()
	.forEach(e -> result.computeIfAbsent(e.getValue(), k -> new TreeSet<>() ).add(e.getKey()));
	
	return result;
}

private static HashMap<String, Integer> getOccurrencesMap(String[] strings) {
	HashMap<String, Integer> result = new HashMap<>();
	for(String str: strings) {
		result.merge(str, 1, Integer::sum);
	}
	return result;
}
static public boolean isSum2(int [] array, int sum) {
	//returns true if a given array contains two numbers, the summing of which
	//equals a given 'sum' value
	//complexity O[N] only one pass over the elements
	HashSet<Integer> helper = new HashSet<>();
	int index = 0;
	while(index < array.length && !helper.contains(sum - array[index])) {
		helper.add(array[index++]);
	}
	return index < array.length;
}
static public int getMaxWithNegativePresentation(int [] array) {
	//returns maximal positive value for which exists negative one with the same abs value
	//if no pair of positive and negative values with the same abs value the method returns -1
	//complexity O[N] only one pass over the elements
	int maxRes = -1;
	HashSet<Integer> helper = new HashSet<>();
	for(int num: array) {
		if(helper.contains(-num)) {
			maxRes = Math.max(maxRes, Math.abs(num));
		} else {
			helper.add(num);
		}
	}
	return maxRes;
}
public static Map<Integer, Integer> getMapSquares(List<Integer> numbers) {
	Map<Integer, Integer> res = numbers.stream()
			.collect(Collectors.toMap(n -> n, n -> n * n, (v1, v2) -> v1,
					LinkedHashMap::new));
	return res;
}
public static boolean isAnagram(String word, String anagram) {
    boolean result = true;

    if (word.length() != anagram.length() || word.equals(anagram)) {
        result = false;
    } else {
        int[] letters = new int[256];
        for (int i = 0; i < word.length(); i++) {
            letters[word.charAt(i)]++;
            letters[anagram.charAt(i)]--;
        }

        for (int count : letters) {
            if (count != 0) {
                result = false;
            }
        }
    }
    
    return result;
}

public static List<DateRole> assignRoleDates(List<DateRole> rolesHistory, List<LocalDate> dates) {
    List<DateRole> result = new ArrayList<>();
    int roleIndex = 0;
    DateRole currentRole = null;

    for (LocalDate date : dates) {
        while (roleIndex < rolesHistory.size() && rolesHistory.get(roleIndex).date().isBefore(date)) {
            currentRole = rolesHistory.get(roleIndex);
            roleIndex++;
        }
        String role = (currentRole != null && (roleIndex == 0 || !rolesHistory.get(roleIndex - 1).date().isAfter(date)))
                      ? currentRole.role() : null;
        result.add(new DateRole(date, role));
    }

    return result;
}

public static void displayDigitsStatistics() {
    new Random().ints(1_000_000, 0, Integer.MAX_VALUE)
        .flatMap(n -> Integer.toString(n).chars())
        .boxed()
        .collect(Collectors.groupingBy(d -> d, Collectors.counting()))
        .entrySet()
        .stream()
        .sorted((e1, e2) -> {
            int res = e2.getValue().compareTo(e1.getValue());
            return res == 0 ? e1.getKey().compareTo(e2.getKey()) : res;
        })
        .forEach(e -> System.out.printf("%d -> %d\n", e.getKey() - '0', e.getValue()));
}
}