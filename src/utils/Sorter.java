package utils;

import java.util.Arrays;
import java.util.List;
import utils.enums.SortMethod;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import utils.sorting.algorithms.Bubble;
import utils.sorting.algorithms.Insertion;
import utils.sorting.algorithms.Quick;
import utils.sorting.algorithms.Selection;
import utils.sorting.algorithms.SortThread;

public class Sorter<T extends Comparable<? super T>> {

    private SortMethod sortMethod;
    private final UtilityArray utilityArray;
    private final Queue<T[]> sortedArrays;

    public Sorter(UtilityArray utilityArray) {
        sortedArrays = new ConcurrentLinkedQueue<>();
        this.utilityArray = utilityArray;
    }

    public Queue<T[]> getSortedArrays() {
        return sortedArrays;
    }

    public SortMethod getSortMethod() {
        return sortMethod;
    }

    public void setSortMethod(SortMethod sortMethod) {
        this.sortMethod = sortMethod;
    }

    public void execute() {
        List<SortThread<T>> collect = null;
        switch (sortMethod) {
            case SELECTION:
                collect = Arrays.asList(utilityArray.getChunkedArray()).parallelStream()
                        .map(subArr -> new SortThread<T>(subArr) {
                    @Override
                    public <T extends Comparable<? super T>> void sort(T[] sortableArray) {
                        Selection.sort(sortableArray);
                    }
                }).collect(Collectors.toList());
                break;
            case INSERTION:
                collect = Arrays.asList(utilityArray.getChunkedArray()).parallelStream()
                        .map(subArr -> new SortThread<T>(subArr) {
                    @Override
                    public <T extends Comparable<? super T>> void sort(T[] sortableArray) {
                        Insertion.sort(sortableArray);
                    }
                }).collect(Collectors.toList());
                break;
            case BUBBLE:
                collect = Arrays.asList(utilityArray.getChunkedArray()).parallelStream()
                        .map(subArr -> new SortThread<T>(subArr) {
                    @Override
                    public <T extends Comparable<? super T>> void sort(T[] sortableArray) {
                        Bubble.sort(sortableArray);
                    }
                }).collect(Collectors.toList());
                break;
            case QUICK:
                collect = Arrays.asList(utilityArray.getChunkedArray()).parallelStream()
                        .map(subArr -> new SortThread<T>(subArr) {
                    @Override
                    public <T extends Comparable<? super T>> void sort(T[] sortableArray) {
                        Quick.sort(sortableArray);
                    }
                }).collect(Collectors.toList());
                break;
            default:
                throw new UnsupportedOperationException("No algorithm selected.");
        }

        /* Start the workers! Seize the means of production. */
        collect.parallelStream().forEach(thread -> thread.start());

        /* ~Seize~ Wait for the goods. */
        collect.parallelStream()
                .forEach(thread -> {
                    try {
                        thread.join();
                    } catch (InterruptedException ex) { /* do nothing */ }
                });
        
        /* Seize the goods. */
        collect.parallelStream()
                .map(SortThread::getInternalArray)
                .forEach(array -> sortedArrays.offer(array));
    } /* Java streams here are faster due to the ordering of the chunks being irrelevant and parallelism can be achieved through means of concurrency here. */
}
