import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите 1-е число: ");
        double a = scanner.nextDouble();
        System.out.print("Введите 2-е число: ");
        double b = scanner.nextDouble();
        System.out.print("Введите математическое действие \"+, -, *, /, %, ==, >, <\": ");
        String c = scanner.next();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        switch (c) {
            case "+":
                System.out.println(executor.submit(() -> a + b).get());
                executor.shutdown();
                break;
            case "-":
                System.out.println(executor.submit(() -> a - b).get());
                executor.shutdown();
                break;
            case "*":
                System.out.println(executor.submit(() -> a * b).get());
                executor.shutdown();
                break;
            case "/":
                System.out.println(executor.submit(() -> a / b).get());
                executor.shutdown();
                break;
            case "%":
                System.out.println(executor.submit(() -> a % b).get());
                executor.shutdown();
                break;
            case "==":
                System.out.println(executor.submit(() -> a == b).get());
                executor.shutdown();
                break;
            case ">":
                System.out.println(executor.submit(() -> a > b).get());
                executor.shutdown();
                break;
            case "<":
                System.out.println(executor.submit(() -> a < b).get());
                executor.shutdown();
                break;
        }
    }
}
