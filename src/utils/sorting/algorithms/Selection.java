package utils.sorting.algorithms;

public class Selection {

	public static <T extends Comparable<? super T>> void sort(T[] collection) {
		final int collectionSize = collection.length;

		for (int i = 0; i < collectionSize; i++) {
			int lowerBound = i;

			for (int j = i; j < collectionSize; j++) {
				if (collection[j].compareTo(collection[lowerBound]) < 0) {
					lowerBound = j;
				}
			}
			T tmp = collection[i];
			collection[i] = collection[lowerBound];
			collection[lowerBound] = tmp;
		}
	}

}
