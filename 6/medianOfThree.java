/**
 * @author Kalle Elmdahl 21/09/20 (updated 21/09/23)
 * The code includes a quicksort implementation.
 * The code is for an assignment from the KTH-course ID1020
 * The code is based on examples from https://algs4.cs.princeton.edu/home/
 */
import java.io.File;
import java.util.Random;
import java.util.Scanner;

/**
 * Quicksort with median of three improvement
 */
public class medianOfThree {

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

        int m = median3(a, lo, (hi - lo) / 2 + lo, hi);
        swap(a, m, lo);
        
        int j = partition(a, lo, hi);
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
     * Takes out the median index of three indexes in an integer array
     * @param a the integer array
     * @param i index one
     * @param j index two
     * @param k index three
     * @return the median index
     */
    private static int median3(int[] a, int i, int j, int k) {
        if(a[i] < a[j])
            if(a[j] < a[k]) return j; // i < j < k
            else if(a[i] < a[k]) return k; // i < k < j
            else return i; // k < i < j
        else if(a[k] < a[j]) return j; // k < j < i
        else if(a[k] < a[i]) return k; // j < k < i
        else return i; // j < i < k
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

        long startTime = System.nanoTime();

        sortQuick(numbers);
        
        long endTime = System.nanoTime();
        long duration = endTime - startTime; 
        System.out.println("\n\nResult\n" + duration / 1000000 + " ms\n" + duration / 1000 + " μs\n" + duration + " ns");

        System.out.println("\nShow?");
        input.next();
        System.out.println(toString(numbers));
        input.close();
    }
}
