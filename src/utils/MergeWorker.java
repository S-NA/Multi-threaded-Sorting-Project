package utils;

public class MergeWorker<T extends Comparable<? super T>> implements Runnable {

    private final T[] firstArray;
    private final T[] lastArray;
    private T[] mergedArray;

    public MergeWorker(T[] firstArray, T[] lastArray) {
        this.firstArray = firstArray;
        this.lastArray = lastArray;
    }

    public T[] getMergedArray() {
        return mergedArray;
    }
    
    private void merge(T[] first, T[] last) {
        mergedArray = (T[]) new Comparable[first.length + last.length];
        int i = 0, j = 0, k = 0;
        while (i < first.length && j < last.length) {
            if (first[i].compareTo(last[j]) < 0) {
                mergedArray[k] = first[i];
                i++;
            } else {
                mergedArray[k] = last[j];
                j++;
            }
            k++;
        }
        
        if (i < first.length) {
            System.arraycopy(first, i, mergedArray, k, (first.length - i));
        }

        if (j < last.length) {
            System.arraycopy(last, j, mergedArray, k, (last.length - j));
        }
    }

    @Override
    public void run() {
        merge(firstArray, lastArray);
    }

}
