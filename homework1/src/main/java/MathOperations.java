import java.util.Scanner;

public class MathOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число a");
        int a = sc.nextInt();
        System.out.println("Введите число b");
        int b = sc.nextInt();

        System.out.println("a + b = " + (a + b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a % b = " + (a % b));
        if (a<b) {
            System.out.println("Число a меньше числа b");
        }
        else if (a>b) {
            System.out.println("Число a больше числа b");
        }
        else
            System.out.println("Числа равны");
    }
}
