package utils.sorting.algorithms;

public abstract class SortThread<T extends Comparable<? super T>> extends Thread implements Runnable {

    private final T[] internalArray;

    public SortThread(Comparable[] internalArray) {
        this.internalArray = (T[]) internalArray;
    }
    
    public T[] getInternalArray() {
        return internalArray;
    }

    public abstract <T extends Comparable<? super T>> void sort(T[] collection);

    @Override
    public void run() {
        sort(internalArray);
    }

}
