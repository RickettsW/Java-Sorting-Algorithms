import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static int[] toIntArray(ArrayList<Integer> list) {
        /*this converts the usable format */
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static int[] sortQuick(int[] array) {
        /* actual quicksort algorithm */
        if (array.length <= 1) {
            return array; /*basecase */
        }

        int pivot = array[array.length / 2]; /*pivot halfway */
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
            sortQuick(toIntArray(left)),
            toIntArray(middle),
            sortQuick(toIntArray(right))
        ); /*recursive call */
    }

    public static int[] quickMerge(int[] left, int[] middle, int[] right) {
        int[] result = new int[left.length + middle.length + right.length];
        System.arraycopy(left, 0, result, 0, left.length);
        System.arraycopy(middle, 0, result, left.length, middle.length);
        System.arraycopy(right, 0, result, left.length + middle.length, right.length);
        return result; /*merges arrays by copying the left right and middle into a single array */
    }

    public static int[] generateArray(int size) { /*generates random array */
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(Integer.MAX_VALUE);  /*keeps values positive for items */
        }
        return array;
    }

   
    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 50000, 75000, 100000, 500000}; /*sizes provided */

        for (int size : sizes) {
            System.out.println("\n Sorted Array of Size " + size + "\n");
            int[] array = generateArray(size);
            int[] sorted = sortQuick(array);
            System.out.println(Arrays.toString(sorted));
        }
    }
}
