/**
 * @author Kalle Elmdahl 21/09/20 (updated 21/09/23)
 * The code includes a quicksort implementation.
 * The code is for an assignment from the KTH-course ID1020
 * The code is based on examples from https://algs4.cs.princeton.edu/home/
 */
import java.io.File;
import java.util.Scanner;

/**
 * Merge sort algorithm with cutoff
 */
public class MergeSortVariations {
    /**
     * Merge two sorted arrays
     * @param nums the main array
     * @param cpy a copy for temporarely storing values
     * @param lowIndex lowest index of left array
     * @param middle separation point
     * @param highIndex highest index
     */
    private static void merge(int[] nums, int[] cpy, int lowIndex, int middle, int highIndex) {
        for (int k = lowIndex; k <= highIndex; k++) cpy[k] = nums[k];

        int j = middle + 1;
        for (int k = lowIndex; k <= highIndex; k++)
            if (lowIndex > middle) // low array is complete
                nums[k] = cpy[j++];
            else if (j > highIndex) // high array is complete
                nums[k] = cpy[lowIndex++];
            else if (cpy[j] < cpy[lowIndex])  // high array value is lower
                nums[k] = cpy[j++];
            else // low array value is lower
                nums[k] = cpy[lowIndex++];
    }
    
    /**
     * Merge sort recursive
     * @param nums the array to be sorted
     * @param cpy memory space for temporary storage
     * @param lowIndex lowest index to sort
     * @param highIndex highest index to sort
     * @param cutoff array size when cutoff to insertion sort
     */
    private static void sortMerge(int[] nums, int[] cpy, int lowIndex, int highIndex, int cutoff) {
        int n = highIndex - lowIndex + 1;
        if (n <= cutoff) {
            insertionSort(nums, lowIndex, highIndex);
            return;
        }
        if (highIndex <= lowIndex) return;
        int middle = (highIndex - lowIndex) / 2 + lowIndex;
        sortMerge(nums, cpy, lowIndex, middle, cutoff);
        sortMerge(nums, cpy, middle + 1, highIndex, cutoff);
        merge(nums, cpy, lowIndex, middle, highIndex);
    }

    /**
     * Sort array with merge sort
     * @param nums the array to be sorted
     * @param cutoff when to cutoff to insertion sort
     */
    public static void sortMerge(int[] nums, int cutoff) {
        int[] cpy = new int[nums.length];
        sortMerge(nums, cpy, 0, nums.length - 1, cutoff);
    }

    /**
     * Insertion sort part of an array
     * @param numbers the array to be sorted
     * @param lowIndex lowest index to sort
     * @param highIndex highest index to sort
     */
    private static void insertionSort(int[] numbers, int lowIndex, int highIndex) {
        for (int i = lowIndex; i <= highIndex; i++)
            for (int j = i; j > lowIndex && numbers[j] < numbers[j-1]; j--)
                swap(numbers, j, j-1);
    }
    
    /**
     * Swap two numbers
     * @param numbers an array of numbers
     * @param a index of number one
     * @param b index of number two
     */
    public static void swap(int[] numbers, int a, int b) {
        int t = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = t;
    }

    /**
     * Stringifies an integer array
     * @param numbers the array to be stringified
     * @return the String representation
     */
    public static String toString(int[] numbers) {
        StringBuilder s = new StringBuilder("[");
        for(int i = 0; i < numbers.length; i++) {
            s.append(numbers[i]);
            if(i < numbers.length - 1) s.append(", ");
        }
        return s.append("]").toString();
    }

    /**
     * Main function used for testing the class
     * @param args arguments from program execution
     */
    public static void main(String[] args) {
        Scanner input;
        try {
            input = new Scanner(new File(args[0]));
        } catch (Exception e) {
            System.err.println(e);
            return;
        }

        int[] numbers = new int[input.nextInt()];
        for(int i = 0; i < numbers.length; i++) numbers[i] = input.nextInt();

        input.close();
        input = new Scanner(System.in);

        System.out.print("What cutoff? \n$ ");
        int cutoff = input.nextInt();
        long startTime = System.nanoTime();

        sortMerge(numbers, cutoff);
        
        long endTime = System.nanoTime();
        long duration = endTime - startTime; 
        System.out.println("\n\nResult\n" + duration / 1000000 + " ms\n" + duration / 1000 + " ??s\n" + duration + " ns");

        System.out.println("\nShow?");
        input.next();
        System.out.println(toString(numbers));
        input.close();
    }
}
