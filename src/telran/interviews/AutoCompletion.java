package telran.interviews;

import java.util.*;

public class AutoCompletion {
    private TreeSet<String> words;

    public AutoCompletion() {
        this.words = new TreeSet<>();
    }

    public boolean addWord(String word) {
        return words.add(word);
    }

    public String[] getVariants(String prefix) {
        SortedSet<String> subset = words.subSet(prefix, prefix + Character.MAX_VALUE);
        String[] variants = new String[subset.size()];
        subset.toArray(variants);

        return variants;
    }
}
