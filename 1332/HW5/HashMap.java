import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries.
     */
    public HashMap() {
        size = 0;
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
    }

    @Override
    public V add(K key, V value) {
        boolean foundrep = false;
        MapEntry<K, V> data = new MapEntry<K, V>(key, value);
        double loadfactor = ((double) size + 1) / table.length;
        if (key == null || value == null) {
            throw new IllegalArgumentException("null");
        } else if (loadfactor >= MAX_LOAD_FACTOR) {
            regrow();
        }
        int counter = 0;
        int index = Math.abs((key.hashCode()
                + (counter * counter)) % table.length);
        int ii = index;
        V toReturn = null;
        while (table[index] != null) {
            if (!table[index].isRemoved()) {
                if (table[index].getKey().equals(key)) {
                    foundrep = true;
                    ii = index;
                } else if (!foundrep) {
                    ii = Math.abs((key.hashCode() + ((counter + 1)
                            * (counter + 1))) % table.length);
                }
            } else if (table[index].isRemoved()) {
                foundrep = true;
                ii = index;
            } else if (counter > table.length) {
                regrow();
                return add(key, value);
            }
            counter++;
            index = Math.abs((key.hashCode()
                    + (counter * counter)) % table.length);
        }
        if (table[ii] == null) {
            toReturn = null;
            table[ii] = data;
            size++;
        } else if (table[ii] != null && !table[ii].isRemoved()) {
            toReturn = table[ii].getValue();
            table[ii] = data;
        } else if (table[ii] != null && table[ii].isRemoved()) {
            toReturn = null;
            table[ii] = data;
            size++;
        }
        return toReturn;
    }

    /**
     * doubles the size of the array + 1
     *
     * @return the new backing array
     */
    private MapEntry<K, V>[] regrow() {
        MapEntry<K, V>[] temp =
                (MapEntry<K, V>[]) new MapEntry[(table.length * 2) + 1];
        MapEntry<K, V>[] current = table;
        table = temp;
        size = 0;
        for (int i = 0; i < current.length; i++) {
            if (current[i] != null) {
                add(current[i].getKey(), current[i].getValue());
            }
        }
        return table;
    }

    @Override
    public V remove(K key) {
        boolean hasFound = false;
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        } else if (isEmpty()) {
            throw new NoSuchElementException("the mash bringing");
        }
        int counter = 0;
        int index = 0;
        V toReturn = null;
        while (!hasFound) {
            index = Math.abs((key.hashCode()
                    + (counter * counter)) % table.length);
            if (table[index] == null) {
                throw new NoSuchElementException("Ninja turtles");
            } else if (table[index] != null && !table[index].isRemoved()) {
                if (table[index].getKey().equals(key)) {
                    toReturn = table[index].getValue();
                    table[index].setRemoved(true);
                    size--;
                    hasFound = true;
                }
            } else if (table[index].getKey().equals(key)
                    && table[index].isRemoved()) {
                throw new NoSuchElementException("away");
            } else if (counter > table.length) {
                throw new NoSuchElementException("Ninja turtles vs shredder");
            }
            counter++;
        }
        return toReturn;
    }

    @Override
    public V get(K key) {
        boolean hasFound = false;
        if (key == null) {
            throw new IllegalArgumentException("[witty comment here]");
        }
        int counter = 0;
        int index = 0;
        V toReturn = null;
        while (!hasFound) {
            index = Math.abs((key.hashCode()
                    + (counter * counter)) % table.length);
            if (table[index] == null || (table[index].isRemoved()
                    && table[index].getKey().equals(key))) {
                throw new NoSuchElementException("8luh");
            } else if (table[index].getKey().equals(key)) {
                toReturn = table[index].getValue();
                hasFound = true;
            } else {
                counter++;
            }
        }
        return toReturn;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Please not Dick Cheney");
        }
        try {
            get(key);
        } catch (NoSuchElementException e) {
            return false;
        }

        return true;
    }

    @Override
    public void clear() {
        size = 0;
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
    }

    /**
     * a method to check if the table is empty
     *
     * @return whether or not the table is empty.
     */
    private boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public MapEntry<K, V>[] toArray() {
        return table;
    }

    @Override
    public Set<K> keySet() {
        Set<K> all = new HashSet<K>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                all.add(table[i].getKey());
            }
        }
        return all;
    }

    @Override
    public List<V> values() {
        List<V> all = new LinkedList<V>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (!table[i].isRemoved()) {
                    all.add(table[i].getValue());
                }
            }
        }
        return all;
    }
}