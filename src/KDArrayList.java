import java.util.Arrays;

public class KDArrayList<T> {
    private int size;
    private int capacity;
    private Object[] elements;

    public KDArrayList() {
        this.capacity = 10;
        elements = new Object[capacity];
    }
    public KDArrayList(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    public void add(T element) {
        if (size == capacity) {
            capacity *= 2;
            Object[] newElements = new Object[capacity];
            if (size >= 0) System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
        elements[size++] = element;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[index];
    }

    public T set(int index, T element) {
        T oldValue = (T) elements[index];
        elements[index] = element;
        return oldValue;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    public int size() {
        return size;
    }

    public T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(elements, size, a.getClass());
    }
}
