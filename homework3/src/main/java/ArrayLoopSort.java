import java.util.Arrays;
import java.util.Scanner;

public class ArrayLoopSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int arrSize = sc.nextInt();
        int [] arr = new int[arrSize];
        System.out.print("Enter array elements: ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        int min = arr[0];
        int max = arr[0];
        int count = 0;
        for (int i: arr) {
            if (i < min) min = i;
            if (i > max) max = i;
            if (i == 5) count++;
        }
        int countMin = arr.length;
        int countMax = 0;
        for (int i = 0; i < arr.length; i++) {
            int counter = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    counter++;
                }
            }
            if (countMax < counter) {
                countMax = counter;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int counter = 1;
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j] && i!=j) {
                    counter++;
                }
            }
            if (countMin > counter) {
                countMin = counter;
            }
        }

        System.out.println("Min element: " + min);
        System.out.println("Max element: " + max);
        System.out.println("Iterations of 5: " + count);

        System.out.println("Max iterations: " + countMax);
        System.out.println("Min iterations: " + countMin);

        boolean isSorted = false;
        int temp;
        while (!isSorted){
            isSorted = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i+1]){
                    isSorted = false;
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
