import java.util.Scanner;

public class decending {

    public static void swap(int[] numbers, int a, int b) {
        int t = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = t;
    }

    public static void sort(int[] numbers) {
        for(int i = 1; i < numbers.length; i++)
            for(int j = i; j > 0 && numbers[j - 1] > numbers[j]; j--)
                swap(numbers, j - 1, j);
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
