import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubblesort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Keep tabs on pirate cove");
        }

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swap(arr, j, (j + 1));
                }
            }
        }

    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionsort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Wind the music box!");
        }

        int j;
        for (int i = 1; i < arr.length; i++) {
            j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                swap(arr, j, (j - 1));
                j--;
            }
        }
    }

    /**
     * Implement shell sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log(n))
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void shellsort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("hi");
        }

        int j;
        T temp;
        for (int s = arr.length / 2; s > 0;
             s = s == 2 ? 1 : (int) (s / 2.2)) {
            for (int i = s; i < arr.length; i++) {
                temp = arr[i];
                j = i;
                while (j >= s && comparator.compare(temp, arr[j - s]) < 0) {
                    arr[j] = arr[j - s];
                    j -= s;
                }
                arr[j] = temp;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quicksort(T[] arr, Comparator<T> comparator,
            Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Foxy's watching!");
        }

        quickSortHelper(arr, 0, arr.length - 1, comparator, rand);
    }

    /**
     * a recursive helper for quicksort
     * @param arr array
     * @param low lowest value
     * @param high highest value
     * @param c comparator
     * @param rand random number to be used as pivot
     * @param <T> generic type t
     */
    private static <T> void quickSortHelper(T[] arr, int low, int high,
                                            Comparator<T> c, Random rand) {
        int i = low;
        int j = high;
        if (j > i) {
            int pivIndex = rand.nextInt(high - low + 1) + low;
            if (pivIndex == 0) {
                pivIndex++;
            } else if (pivIndex == arr.length) {
                pivIndex--;
            }
            T piv = arr[pivIndex];
            swap(arr, pivIndex, i);
            pivIndex = i;
            i++;
            while (i <= j) {
                while (i <= high && c.compare(arr[i], piv) <= 0) {
//                System.out.println(pivIndex);
                    i++;
                }
                while (j > low && c.compare(arr[j], piv) >= 0) {
                    j--;
                }
                if (i <= j) {
                    swap(arr, i, j);
                    i++;
                    j--;
                }
            }
            swap(arr, pivIndex, j);
            quickSortHelper(arr, low, j - 1, c, rand);
            quickSortHelper(arr, j + 1, high, c, rand);
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * ********************* IMPORTANT ************************
     * FAILURE TO DO SO MAY CAUSE ClassCastException AND CAUSE
     * YOUR METHOD TO FAIL ALL THE TESTS FOR MERGE SORT
     * ********************************************************
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergesort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Gonna gitcha");
        }

        msh(0, arr.length - 1, arr, comparator);
    }

    /**
     * a recursive merge sort helper
     * @param low lowest value
     * @param high highest value
     * @param arr array to sort
     * @param c comparator
     * @param <T> generic type t
     */
    private static <T> void msh(int low, int high, T[] arr,
                                            Comparator<T> c) {
        if (low < high) {
            int cent = (high + low) / 2;
            msh(low, cent, arr, c);
            msh(cent + 1, high, arr, c);
            merge(low, cent + 1, high, arr, c);
        }
    }

    /**
     * merge all lists back together
     * @param low lowest value
     * @param mid middle value
     * @param high highest value
     * @param arr array to merge back into
     * @param c comparator
     * @param <T> generic type t
     */
    private static <T> void merge(int low, int mid, int high, T[] arr,
                                  Comparator<T> c) {
        T[] helper = (T[]) new Object[arr.length];

        int i = mid - 1;
        int j = low;
        int k = high - low + 1;

        while (low <= i && mid <= high) {
            if (c.compare(arr[low], arr[mid]) <= 0) {
                helper[j++] = arr[low++];
            } else {
                helper[j++] = arr[mid++];
            }
        }

        while (low <= i) {
            helper[j++] = arr[low++];
        }

        while (mid <= high) {
            helper[j++] = arr[mid++];
        }

        for (int z = 0; z < k; z++, high--) {
            arr[high] = helper[high];
        }
    }

    /**
     * Implement radix sort.
     *
     * Remember you CANNOT convert the ints to strings.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * You may use an ArrayList or LinkedList if you wish,
     * but it may only be used inside radixsort and any radix sort helpers
     * Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] radixsort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Close the doors");
        }
        int rad = 10;
        ArrayList<Integer>[] bucket = new ArrayList[rad];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<Integer>();
        }
        boolean max = false;
        int temp = -1;
        int put = 1;
        while (!max) {
            max = true;
            for (Integer i : arr) {
                temp = Math.abs(i / put);
                bucket[temp % rad].add(i);
                if (max && temp > 0) {
                    max = false;
                }
            }
            int a = 0;
            for (int b = 0; b < rad; b++) {
                for (Integer i : bucket[b]) {
                    arr[a++] = i;
                }
                bucket[b] = new ArrayList<Integer>();
            }
            put *= rad;
        }
        ArrayList<Integer> negBucket = new ArrayList();
        ArrayList<Integer> posBucket = new ArrayList();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                negBucket.add(arr[i]);
            } else {
                posBucket.add(arr[i]);
            }
        }
        int cnt = 0;
        for (int i = negBucket.size() - 1; i >= 0; i--) {
            arr[cnt++] = negBucket.get(i);
        }
        for (int i = 0; i < posBucket.size(); i++) {
            arr[cnt++] = posBucket.get(i);
        }
        return arr;
    }

    /**
     * a swap method to swap
     * @param array array to swap within
     * @param i index 1
     * @param j index 2
     */
    private static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
