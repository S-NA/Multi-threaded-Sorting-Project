package utils;

import utils.enums.ArrayType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class UtilityArray<T extends Comparable<? super T>> {

    private final ArrayType arrayType;
    private T[] internalArray;
    private T[][] chunkedArray;
    private final int inputSize;
    private final int blockSize;

    public UtilityArray(int inputSize, int blockSize, ArrayType arrayType) {
        this.inputSize = inputSize;
        this.blockSize = blockSize;
        this.arrayType = arrayType;

        internalArray = (T[]) new Comparable[inputSize];
        initalize();
    }
    
    private void chunkArray() {
        // q = 1 + ((x - 1) / y); // where x <= 0 (this is a fast round)
        int numberOfChunks = 1 + ((internalArray.length - 1) / blockSize);
        chunkedArray = (T[][]) new Comparable[numberOfChunks][];

        int counter = 0;
        for (int i = 0; i < internalArray.length - blockSize + 1; i += blockSize) {
            chunkedArray[counter++] = Arrays.copyOfRange(internalArray, i, i + blockSize);
        }

        if (internalArray.length % blockSize != 0) {
            chunkedArray[counter] = Arrays.copyOfRange(internalArray, internalArray.length - internalArray.length % blockSize, internalArray.length);
        }
    }

    private void generate() {
        Random rrand = new Random();
        for (int i = 0; i < inputSize; i++) {
            internalArray[i] = (T) ((Integer) rrand.nextInt(100 /* 0 - 99 */));
        }

        switch (arrayType) {
            case RANDOM:
                break;
            case ALREADY_SORTED:
                Arrays.sort(internalArray);
                break;
            case REVERSE_ORDER:
                Arrays.sort(internalArray, Comparator.reverseOrder());
                break;
            default:
                break;
        }
    }

    private void initalize() {
        generate();
        chunkArray();
    }

    public T[] getInternalArray() {
        return internalArray;
    }

    public void setInternalArray(T[] internalArray) {
        this.internalArray = internalArray;
    }

    public T[][] getChunkedArray() {
        return chunkedArray;
    }

    public void setChunkedArray(T[][] chunkedArray) {
        this.chunkedArray = chunkedArray;
    }

}
