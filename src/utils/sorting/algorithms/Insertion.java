package utils.sorting.algorithms;

public class Insertion {

    public static <T extends Comparable<? super T>> void sort(T[] collection) {
        int lowerBound = 1;
        while (lowerBound < collection.length) {
            T currentVal = collection[lowerBound];
            int prevValIndex = lowerBound - 1;
            while (prevValIndex >= 0 && collection[prevValIndex].compareTo(currentVal) > 0) {
                collection[prevValIndex + 1] = collection[prevValIndex];
                prevValIndex--;
            }
            collection[prevValIndex + 1] = currentVal;
            lowerBound++;
        }
    }

    public static void sort(boolean[] collection) {
        int lowerBound = 1;
        while (lowerBound < collection.length) {
            boolean currentVal = collection[lowerBound];
            int prevValIndex = lowerBound - 1;
            while (prevValIndex >= 0 && collection[prevValIndex]) {
                collection[prevValIndex + 1] = collection[prevValIndex];
                prevValIndex--;
            }
            collection[prevValIndex + 1] = currentVal;
            lowerBound++;
        }
    }

    public static void sort(byte[] collection) {
        int lowerBound = 1;
        while (lowerBound < collection.length) {
            byte currentVal = collection[lowerBound];
            int prevValIndex = lowerBound - 1;
            while (prevValIndex >= 0 && collection[prevValIndex] > currentVal) {
                collection[prevValIndex + 1] = collection[prevValIndex];
                prevValIndex--;
            }
            collection[prevValIndex + 1] = currentVal;
            lowerBound++;
        }
    }

    public static void sort(char[] collection) {
        int lowerBound = 1;
        while (lowerBound < collection.length) {
            char currentVal = collection[lowerBound];
            int prevValIndex = lowerBound - 1;
            while (prevValIndex >= 0 && collection[prevValIndex] > currentVal) {
                collection[prevValIndex + 1] = collection[prevValIndex];
                prevValIndex--;
            }
            collection[prevValIndex + 1] = currentVal;
            lowerBound++;
        }
    }

    public static void sort(short[] collection) {
        int lowerBound = 1;
        while (lowerBound < collection.length) {
            short currentVal = collection[lowerBound];
            int prevValIndex = lowerBound - 1;
            while (prevValIndex >= 0 && collection[prevValIndex] > currentVal) {
                collection[prevValIndex + 1] = collection[prevValIndex];
                prevValIndex--;
            }
            collection[prevValIndex + 1] = currentVal;
            lowerBound++;
        }
    }

    public static void sort(int[] collection) {
        int lowerBound = 1;
        while (lowerBound < collection.length) {
            int currentVal = collection[lowerBound];
            int prevValIndex = lowerBound - 1;
            while (prevValIndex >= 0 && collection[prevValIndex] > currentVal) {
                collection[prevValIndex + 1] = collection[prevValIndex];
                prevValIndex--;
            }
            collection[prevValIndex + 1] = currentVal;
            lowerBound++;
        }
    }

    public static void sort(long[] collection) {
        int lowerBound = 1;
        while (lowerBound < collection.length) {
            long currentVal = collection[lowerBound];
            int prevValIndex = lowerBound - 1;
            while (prevValIndex >= 0 && collection[prevValIndex] > currentVal) {
                collection[prevValIndex + 1] = collection[prevValIndex];
                prevValIndex--;
            }
            collection[prevValIndex + 1] = currentVal;
            lowerBound++;
        }
    }

    public static void sort(float[] collection) {
        int lowerBound = 1;
        while (lowerBound < collection.length) {
            float currentVal = collection[lowerBound];
            int prevValIndex = lowerBound - 1;
            while (prevValIndex >= 0 && collection[prevValIndex] > currentVal) {
                collection[prevValIndex + 1] = collection[prevValIndex];
                prevValIndex--;
            }
            collection[prevValIndex + 1] = currentVal;
            lowerBound++;
        }
    }

    public static void sort(double[] collection) {
        int lowerBound = 1;
        while (lowerBound < collection.length) {
            double currentVal = collection[lowerBound];
            int prevValIndex = lowerBound - 1;
            while (prevValIndex >= 0 && collection[prevValIndex] > currentVal) {
                collection[prevValIndex + 1] = collection[prevValIndex];
                prevValIndex--;
            }
            collection[prevValIndex + 1] = currentVal;
            lowerBound++;
        }
    }

}
