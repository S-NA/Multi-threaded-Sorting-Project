package utils.sorting.algorithms;

public class Bubble {

    public static <T extends Comparable<? super T>> void sort(T[] collection) {
        bubbleSort(collection);
    }

    private static <T extends Comparable<? super T>> void bubbleSort(T[] collection) {
        int n = collection.length;
        T temp;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (collection[j - 1].compareTo(collection[j]) > 0) {
                    temp = collection[j - 1];
                    collection[j - 1] = collection[j];
                    collection[j] = temp;
                }

            }
        }
    }

    /* Alternative implementation. */
    private static <T extends Comparable<? super T>> void bubbleSort_q(T[] collection) {
        boolean changed = false;
        do {
            changed = false;
            for (int i = 0; i < collection.length - 1; i++) {
                if (collection[i].compareTo(collection[i + 1]) > 0) {
                    T tmp = collection[i];
                    collection[i] = collection[i + 1];
                    collection[i + 1] = tmp;
                    changed = true;
                }
            }
        } while (changed);
    }

}
