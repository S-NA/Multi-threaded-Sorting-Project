package utils.sorting.algorithms;

public class Quick {

    public static <T extends Comparable<? super T>> void sort(T[] collection) {
        quickSort(collection, 0, collection.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] collection, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(collection, first, last);
            quickSort(collection, first, pivotIndex);
            quickSort(collection, pivotIndex + 1, last);
        }
    }

    /* Hoare Partition */
    private static <T extends Comparable<? super T>> int partition(T[] array, int head, int last) {
        T pivot = array[head];
        int i = head - 1;
        int j = last + 1;

        while (true) {

            do {
                i++;
            } while (array[i].compareTo(pivot) < 0);

            do {
                j--;
            } while (array[j].compareTo(pivot) > 0);

            if (i < j) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            } else {
                return j;
            }
        }

    }
}
