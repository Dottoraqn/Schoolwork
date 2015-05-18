import java.util.NoSuchElementException;

public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] arr;
    private int size;
    // Do not add any more instance variables

    /**
     * Creates a MaxHeap.
     */
    public MaxHeap() {
        size = 0;
        arr = (T[]) new Comparable[STARTING_SIZE];
    }

    @Override
    public void add(T item) {
        if ((size + 1) == arr.length) {
            regrow();
        } else if (item == null) {
            throw new IllegalArgumentException("clean up this mess");
        }
        size++;
        arr[size] = item;
        upHeap(size, (size / 2));
    }

    /**
     * a method to help the add method heapify and reorganize the heap
     * @param index child
     * @param parentIndex parent
     */
    private void upHeap(int index, int parentIndex) {
        T temp = null;
        if (index != 0 && parentIndex != 0) {
            if (arr[parentIndex].compareTo(arr[index]) < 0) {
                temp = arr[parentIndex];
                arr[parentIndex] = arr[index];
                arr[index] = temp;
                upHeap(parentIndex, parentIndex / 2);
            }
        }
    }

    /**
     * regrow the backing array for the heap
     * @return new array
     */
    private T[] regrow() {
        T[] temp = (T[]) new Comparable[arr.length * 2];
        for (int i = 0; i <= size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
        return arr;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Your mother was a hamster "
                    + "and your father smelt of elderberries");
        } else {
            T temp = arr[1];
            arr[1] = arr[size];
            arr[size] = null;
            size--;
            downHeap(1, 2);
            return temp;
        }
    }

    /**
     * a method to help remove heapify and reorganize the heap
     * @param parent parent node
     * @param left left node
     */
    private void downHeap(int parent, int left) {
        int right = left + 1;
        if (left <= size) {
            boolean hasRight = right <= size;
            if (arr[left].compareTo(arr[parent]) > 0
                    && (!hasRight || arr[left].compareTo(arr[right]) > 0)) {
                swap(parent, left);
                downHeap(left, left * 2);
            } else if (hasRight && arr[right].compareTo(arr[parent]) > 0) {
                swap(parent, right);
                downHeap(right, right * 2);
            }
        }
    }

    /**
     * swap two values in an array
     * @param i index 1
     * @param j index 2
     */
    private void swap(int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        arr = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        return arr;
    }
}
