package telran.interviews;

public class MyArray<T> {
    private final T[] array;
    private T defaultValue;
    private int defaultVersion;
    private int[] versions;

    public MyArray(int size) {
        this.array = (T[]) new Object[size];
        this.defaultValue = null;
        this.defaultVersion = 0;
        this.versions = new int[size];
    }

    public void setAll(T value) {
        this.defaultValue = value;
        this.defaultVersion++;
    }

    public void set(int index, T value) {
        checkIndex(index);
        array[index] = value;
        versions[index] = defaultVersion;
    }

    public T get(int index) {
        checkIndex(index);
        return versions[index] == defaultVersion ? array[index] : defaultValue;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
    }
}
