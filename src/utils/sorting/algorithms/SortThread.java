package utils.sorting.algorithms;

public abstract class SortThread<T extends Comparable<? super T>> extends Thread implements Runnable {

	private final T[] internalArray;

	public SortThread(T[] internalArray) {
		this.internalArray = internalArray;
	}

	public T[] getInternalArray() {
		return internalArray;
	}

	@Override
	public void run() {
		sort(internalArray);
	}

	@SuppressWarnings("hiding")
	public abstract <T extends Comparable<? super T>> void sort(T[] collection);

}
