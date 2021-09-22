import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class medianOfThree {

    public static void sortQuick(int[] numbers) {
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) swap(numbers, i, random.nextInt(numbers.length - 1));
        sortQuick(numbers, 0, numbers.length - 1);
    }

    private static void sortQuick(int[] a, int lo, int hi) { 
        if (hi <= lo) return;

        int m = median3(a, lo, (hi - lo) / 2 + lo, hi);
        swap(a, m, lo);
        
        int j = partition(a, lo, hi);
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


    private static int median3(int[] a, int i, int j, int k) {
        return (a[i] < a[j] ?
               (a[j] < a[k] ? j : a[i] < a[k] ? k : i) :
               (a[k] < a[j] ? j : a[k] < a[i] ? k : i));
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
