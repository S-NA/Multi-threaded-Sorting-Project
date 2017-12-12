package utils;

//import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MergeQueue<T extends Comparable<? super T>> {

	private final Queue<T[]> sortedArrays;

	public MergeQueue(Queue<T[]> sortedArrays) {
		this.sortedArrays = sortedArrays;
	}

	/*
	 * Benchmark. static int getCapacity(ArrayList<?> l) { try { Field dataField =
	 * ArrayList.class.getDeclaredField("elementData");
	 * dataField.setAccessible(true); return ((Object[]) dataField.get(l)).length; }
	 * catch (Exception e) { return -1; } }
	 */
	public void execute() {
		/* ArrayList */
		int capacity = 1 + ((sortedArrays.size() - 1) / 2);
		List<MergeWorker<T>> mWorkers = new ArrayList<>(capacity);
		List<Thread> threadPool = new ArrayList<>(capacity);
		while (sortedArrays.size() > 1) {
			while (sortedArrays.size() > 1) {
				T[] first = sortedArrays.poll(), last = sortedArrays.poll();
				MergeWorker<T> mWorker = new MergeWorker<>(first, last);
				Thread thread = new Thread(mWorker);
				mWorkers.add(mWorker);
				threadPool.add(thread);
				thread.start();
			} /* parallelism hurts here, a sequential loops is faster, why? */

			threadPool.parallelStream().forEach(thread -> {
				try {
					thread.join();
				} catch (InterruptedException e) {
					/* do nothing */ }
			});

			/*
			 * Leverages how a queue data structure works. Even though parallelism may
			 * misorder things the times that happens is substantially less than the benefit
			 * we gain from it. inputSize := 100, blockSize := 33 with parallism 6ms with
			 * loop 16ms
			 */
			mWorkers.parallelStream().forEach(mWorker -> {
				sortedArrays.offer(mWorker.getMergedArray());
			});
			// System.out.println("Actual Workers: " + mWorkers.size() + " " + "Capacity: "
			// + getCapacity((ArrayList<?>) mWorkers)); /* Test. */
			mWorkers.clear();
			threadPool.clear();
		}
	}

	public T[] getMergedArray() {
		return sortedArrays.peek();
	}

}
