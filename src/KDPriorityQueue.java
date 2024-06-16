import java.util.Comparator;

public class KDPriorityQueue<T> {
    private final KDArrayList<T> queue;
    private final Comparator<T> comparator;

    public KDPriorityQueue(int capacity, Comparator<T> comparator) {
        this.queue = new KDArrayList<>(capacity);
        this.comparator = comparator;
    }

    public void add(T element) {
        queue.add(element);
        int index = queue.size() - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (comparator.compare(queue.get(parentIndex), queue.get(index)) > 0) {
                swap(parentIndex, index);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public T poll() {
        if (queue.size() == 0) {
            return null;
        }
        T element = queue.get(0);
        queue.set(0, queue.get(queue.size() - 1));
        queue.remove(queue.size() - 1);
        heapify(0);
        return element;
    }

    public T peek() {
        return queue.get(0);
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    private void heapify(int index) {
        int leftIndex = index * 2 + 1;
        int rightIndex = index * 2 + 2;
        int smallestIndex = index;
        if (leftIndex < queue.size() && comparator.compare(queue.get(leftIndex), queue.get(index)) < 0) {
            smallestIndex = leftIndex;
        }
        if (rightIndex < queue.size() && comparator.compare(queue.get(rightIndex), queue.get(smallestIndex)) < 0) {
            smallestIndex = rightIndex;
        }
        if (smallestIndex != index) {
            swap(smallestIndex, index);
            heapify(smallestIndex);
        }
    }

    private void swap(int i, int j) {
        T temp = queue.get(i);
        queue.set(i, queue.get(j));
        queue.set(j, temp);
    }
}
