import java.util.NoSuchElementException;

/**
 * ArrayQueue
 * Implementation of a queue using
 * an array as the backing structure
 *
 * @author Emma Hargrave
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueADT<T> {

    // Do not add instance variables
    private T[] backing;
    private int size;
    private int front;
    private int back;

    /**
     * Construct an ArrayQueue with an
     * initial capacity of INITIAL_CAPACITY
     * <p>
     * Use Constructor Chaining
     */
    public ArrayQueue() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct an ArrayQueue with the specified
     * initial capacity of initialCapacity
     *
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayQueue(int initialCapacity) {
        backing = (T[]) new Object[initialCapacity];
        front = 0;
        back = 0;
        size = 0;
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        } else if (size == backing.length) {
            resize();
        }
        backing[back] = data;
        back = (back + 1) % backing.length;
        size++;
    }

    /**
     * resizes the backing array.
     *
     * O(n)
     */
    private void resize() {
        T[] temp = (T[]) new Object[size * 2];
        int index = 0;
        int i = front;
        while (index < size) {
            temp[index] = backing[i];
            index++;
            i = (i + 1) % backing.length;
        }
        backing = temp;
        front = 0;
        back = size;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No element exists");
        }
        T temp = backing[front];
        backing[front] = null;
        front = (front + 1) % backing.length;
        size--;
        return temp;
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
