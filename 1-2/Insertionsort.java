/**
 * @author Kalle Elmdahl 21/09/20 (updated 21/09/23)
 * The code shows an implementation of insertion sort with some extras
 * The code is for an assignment from the KTH-course ID1020
 * The code is based on examples from https://algs4.cs.princeton.edu/home/
 */
import java.util.Scanner;

/**
 * insertionsort an integer array
 */
public class Insertionsort {
    /**
     * Count the number of needed inversions of an array for it to be sorted
     * @param numbers the array to be checked
     */
    public static void countInversions(int[] numbers) {
        StringBuilder s = new StringBuilder("Inversions:\n");
        for(int i = 0; i < numbers.length; i++)
            for(int j = i + 1; j < numbers.length; j++)
                if(numbers[j] < numbers[i])
                    s.append("[" + i + "," + numbers[i] + "], [" + j + ", " + numbers[j] + "]\n");
        System.out.println(s.toString());
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
     * Sort an array using insertion sort
     * @param numbers the array to be sorted
     */
    public static void sort(int[] numbers) {
        int swaps = 0;
        for(int i = 1; i < numbers.length; i++)
            for(int j = i; j > 0 && numbers[j - 1] > numbers[j]; j--, System.out.println(toString(numbers)), swaps++)
                swap(numbers, j - 1, j);
        
        System.out.println("Swaps: " + swaps);
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
        System.out.print("\n Write 's' to start, 'q' to exit \n$ ");
        Scanner input = new Scanner(System.in);
        while(true) {
            String s = input.next();
            if(s.contains("q")) break;
            System.out.print("\n How many numbers \n$ ");
            int[] numbers = new int[input.nextInt()];
            for(int i = 0; i < numbers.length; i++) {
                System.out.print("Enter number " + (i + 1) + " of " + numbers.length + "\n$ ");
                numbers[i] = input.nextInt();
            }
            
            countInversions(numbers);
            sort(numbers);
            System.out.println("Result: " + toString(numbers));
        }
        input.close();
    }
}