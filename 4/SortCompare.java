import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class SortCompare {

    public static void sortQuick(int[] numbers) {
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) swap(numbers, i, random.nextInt(numbers.length - 1));
        sortQuick(numbers, 0, numbers.length - 1);
    }

    private static void sortQuick(int[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        // System.out.println(toString(a));
        sortQuick(a, lo, j-1);
        sortQuick(a, j+1, hi);
    }
    
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
    
    private static void sortMerge(int[] nums, int[] cpy, int lowIndex, int highIndex) {
        if (highIndex <= lowIndex) return;
        int middle = (highIndex - lowIndex) / 2 + lowIndex;
        sortMerge(nums, cpy, lowIndex, middle);
        sortMerge(nums, cpy, middle + 1, highIndex);
        merge(nums, cpy, lowIndex, middle, highIndex);
    }

    public static void sortMerge(int[] nums) {
        int[] cpy = new int[nums.length];
        sortMerge(nums, cpy, 0, nums.length - 1);
    }

    public static void sortInsertion(int[] numbers) {
        for(int i = 1; i < numbers.length; i++)
            for(int j = i - 1; j >= 0 && numbers[j] > numbers[j + 1]; j--)
                swap(numbers, j, j + 1);
        
    }

    public static void swap(int[] numbers, int a, int b) {
        int t = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = t;
    }

    public static String toString(int[] numbers) {
        StringBuilder s = new StringBuilder("[");
        for(int i = 0; i < numbers.length; i++) {
            s.append(numbers[i]);
            if(i < numbers.length - 1) s.append(", ");
        }
        return s.append("]").toString();
    }
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
