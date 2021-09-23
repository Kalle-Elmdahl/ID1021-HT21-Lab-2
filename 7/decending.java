/**
 * @author Kalle Elmdahl 21/09/20 (updated 21/09/23)
 * This code can be used to sort integer arrays in ascending order.
 * The test code is augmented to sort in decending order
 * The code is for an assignment from the KTH-course ID1020
 * The code is based on examples from https://algs4.cs.princeton.edu/home/
 */
import java.util.Scanner;
/**
 * Sort integer arrays in ascending order
 */
public class decending {
    
    /**
     * Swap two numbers
     * @param numbers an array of numbers
     * @param a index of number one
     * @param b index of number two
     */
    private static void swap(int[] numbers, int a, int b) {
        int t = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = t;
    }

    /**
     * Sort integers in ascending order
     * @param numbers the array to be sorted
     */
    public static void sort(int[] numbers) {
        for(int i = 1; i < numbers.length; i++)
            for(int j = i; j > 0 && numbers[j - 1] > numbers[j]; j--)
                swap(numbers, j - 1, j);
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
                numbers[i] = -input.nextInt();
            }
            
            sort(numbers);
            for(int i = 0; i < numbers.length; i++) numbers[i] = -numbers[i];
            System.out.println("Result: " + toString(numbers));
        }
        input.close();
    }
    
}
