package telran.interviews;

import java.util.HashMap;

public class MyArray<T> {
    private final HashMap<Integer, T> map;
    private T defaultValue;
    private final int size;

    public MyArray(int size) {
        this.size = size;
        this.map = new HashMap<>();
        this.defaultValue = null;
    }

    public void setAll(T value) {
        this.defaultValue = value;
        map.clear();
    }

    public void set(int index, T value) {
        checkIndex(index);
        map.put(index, value);
    }

    public T get(int index) {
        checkIndex(index);
        return map.getOrDefault(index, defaultValue);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }
}
