import java.util.ArrayList;
import java.util.Random;

public class HybridQuickInsertionSort {

    public static int[] toIntArray(ArrayList<Integer> list) {
        /*this converts the usable format */
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static int[] insertionSort(int[] array) {
        /* algorithm for insertion sort, used in hybrid */
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
    }

    public static int[] sortHybridQuick(int[] array) {
        /* mix of insertion sort and quicksort */
        int cutoff = 500; /* amount of item left before insertion sort starts */
        if (array.length <= cutoff) {
            return insertionSort(array);
        }

        int pivot = array[array.length / 2]; /* pivot in middle */
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> middle = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for (int num : array) {
            if (num < pivot) {
                left.add(num);
            } else if (num > pivot) {
                right.add(num);
            } else {
                middle.add(num);
            }
        }

        return quickMerge(
            sortHybridQuick(toIntArray(left)),
            toIntArray(middle),
            sortHybridQuick(toIntArray(right)) /* recursive call */
        );
    }

    public static int[] quickMerge(int[] left, int[] middle, int[] right) {
        int[] result = new int[left.length + middle.length + right.length];
        System.arraycopy(left, 0, result, 0, left.length);
        System.arraycopy(middle, 0, result, left.length, middle.length);
        System.arraycopy(right, 0, result, left.length + middle.length, right.length);
        return result; /*copies arrays from left right middle into one */
    }

    public static int[] generateArray(int size) { /*generates random array */
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(Integer.MAX_VALUE);  /*keeps values positive for items */
        }
        return array;
    }

    public static int[] generateSortedArray(int size) { /*creates sorted array for testing */
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    public static int[] generateReversedArray(int size) { /*creates reverse sorted array for testing */
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 50000, 75000, 100000, 500000}; /*sizes provided */

        for (int size : sizes) {
            System.out.println("\nArray Size: " + size);

            long randomTotalTime = 0;
            for (int i = 0; i < 10; i++) { /* runs program 10 times and works out the total time */
                int[] array = generateArray(size);
                long start = System.currentTimeMillis();
                sortHybridQuick(array);
                long end = System.currentTimeMillis();
                randomTotalTime += (end - start);
            }
            System.out.println("Average RANDOM time:   " + (randomTotalTime / 10.0) + " ms");

            long sortedTotalTime = 0;
            for (int i = 0; i < 10; i++) { /* runs program 10 times and works out the total time for sorted array */
                int[] array = generateSortedArray(size);
                long start = System.currentTimeMillis();
                sortHybridQuick(array);
                long end = System.currentTimeMillis();
                sortedTotalTime += (end - start);
            }
            System.out.println("Average SORTED time:   " + (sortedTotalTime / 10.0) + " ms");

            long reversedTotalTime = 0;
            for (int i = 0; i < 10; i++) { /* runs program 10 times and works out the total time for reverse sorted array */
                int[] array = generateReversedArray(size);
                long start = System.currentTimeMillis();
                sortHybridQuick(array);
                long end = System.currentTimeMillis();
                reversedTotalTime += (end - start);
            }
            System.out.println("Average REVERSED time: " + (reversedTotalTime / 10.0) + " ms");
        }
    }
}
