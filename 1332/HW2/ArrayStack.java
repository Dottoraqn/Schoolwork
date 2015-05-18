import java.util.NoSuchElementException;

/**
 * ArrayStack
 * Implementation of a stack using
 * an array as a backing structure
 *
 * @author Emma Hargrave
 * @version 1.0
 */
public class ArrayStack<T> implements StackADT<T> {

    // Do not add instance variables
    private T[] backing;
    private int size;

    /**
     * Construct an ArrayStack with
     * an initial capacity of INITIAL_CAPACITY.
     * <p>
     * Use constructor chaining.
     */
    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct an ArrayStack with the specified
     * initial capacity of initialCapacity
     *
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayStack(int initialCapacity) {
        backing = (T[]) new Object[initialCapacity];
        size = 0;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You can't do that.");
        } else if (size == backing.length) {
            regrow();
        }
        backing[size] = data;
        size++;
    }

    /**
     * resizes the backing array.
     *
     * O(n)
     */
    private void regrow() {
        T[] temp = (T[]) new Object[size * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = backing[i];
        }
        backing = temp;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("You can't do that either!");
        }
        T tempData = backing[size - 1];
        backing[size - 1] = null;
        size--;
        return tempData;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the backing array for your queue.
     * This is for grading purposes only. DO NOT EDIT THIS METHOD.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        return backing;
    }
}
