/**
 * @author Kalle Elmdahl 21/09/20 (updated 21/09/23)
 * The code includes quick-, merge-, and insertionsort implementations.
 * The code is for an assignment from the KTH-course ID1020
 * The code is based on examples from https://algs4.cs.princeton.edu/home/
 */
import java.io.File;
import java.util.Random;
import java.util.Scanner;

/**
 * This class includes different methods of sorting
 * The main function can be used to time them
 */
public class SortCompare {

    /**
     * Sorts an integer array
     * @param numbers the array to be sorted
     */
    public static void sortQuick(int[] numbers) {
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) swap(numbers, i, random.nextInt(numbers.length - 1));
        sortQuick(numbers, 0, numbers.length - 1);
    }
    /**
     * Quicksorts an array recursively
     * @param a the integer array
     * @param lo the left most index
     * @param hi the right most index
     */
    private static void sortQuick(int[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        // System.out.println(toString(a));
        sortQuick(a, lo, j-1);
        sortQuick(a, j+1, hi);
    }

    /**
     * Partitions an array for quicksorting
     * When done, everything to the right of the returned index will be greater than the returned index value
     * And everything to the left will have a lower value.
     * @param a the array to be partitioned
     * @param lo most left index
     * @param hi most right index
     * @return the partitioning index
     */
    private static int partition(int[] a, int lo, int hi) {
        for(int i = lo + 1, j = hi, pivot = a[lo]; ; i++, j--) {
            // Move over i and j to correct position
            for(; a[i] < pivot && i < hi; i++) {}
            for(; pivot < a[j] && j > lo; j--) {}

            // check if pointers cross
            if (i < j) 
                swap(a, i, j);
            else {
                swap(a, lo, j);
                return j;
            }
        }
    }
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
     */
    private static void sortMerge(int[] nums, int[] cpy, int lowIndex, int highIndex) {
        if (highIndex <= lowIndex) return;
        int middle = (highIndex - lowIndex) / 2 + lowIndex;
        sortMerge(nums, cpy, lowIndex, middle);
        sortMerge(nums, cpy, middle + 1, highIndex);
        merge(nums, cpy, lowIndex, middle, highIndex);
    }

    /**
     * Sort array with merge sort
     * @param nums the array to be sorted
     * @param cutoff when to cutoff to insertion sort
     */
    public static void sortMerge(int[] nums) {
        int[] cpy = new int[nums.length];
        sortMerge(nums, cpy, 0, nums.length - 1);
    }

    /**
     * Insertion sort array
     * @param numbers the array to be sorted
     */
    public static void sortInsertion(int[] numbers) {
        for(int i = 1; i < numbers.length; i++)
            for(int j = i - 1; j >= 0 && numbers[j] > numbers[j + 1]; j--)
                swap(numbers, j, j + 1);
        
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

        System.out.print("What algorithm?, (i/m/q - Insertion/Merge/Quick) \n$ ");
        char c = input.next().charAt(0);
        long startTime = System.nanoTime();

        switch(c) {
            case 'i': sortInsertion(numbers); break;
            case 'm': sortMerge(numbers); break;
            case 'q': sortQuick(numbers); break;
        }
        
        long endTime = System.nanoTime();
        long duration = endTime - startTime; 
        System.out.println("\n\nResult\n" + duration / 1000000 + " ms\n" + duration / 1000 + " μs\n" + duration + " ns");

        System.out.println("\nShow?");
        input.next();
        System.out.println(toString(numbers));
        input.close();
    }
}
