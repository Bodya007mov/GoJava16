package library;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите вместимость библиотеки: ");
        int maxCount = scanner.nextInt();
        System.out.print("Введите количество человек: ");
        int peopleCount = scanner.nextInt();
        Person.semaphore = new Semaphore(maxCount);

        for (int i = 1; i <= peopleCount; i++) {
            new Thread(new Person(i)).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
