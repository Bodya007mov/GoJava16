package library;

import java.util.concurrent.Semaphore;

public class Person implements Runnable{

    public static Semaphore semaphore;
    private final int PERSON_NUMBER;

    public Person(int personNumber) {
        this.PERSON_NUMBER = personNumber;
    }

    public void run() {
        System.out.printf("Посетитель %d пришел ко входу в библиотеку \n", PERSON_NUMBER);
        try {
            if(semaphore.availablePermits() == 0) {
                System.out.printf("Посетитель %d ЖДЕТ входа в библиотеку \n", PERSON_NUMBER);
            }
            semaphore.acquire();
            System.out.printf("Посетитель %d вошел в библиотеку \n", PERSON_NUMBER);
            Thread.sleep(1000);
            System.out.printf("Посетитель %d читает книгу \n", PERSON_NUMBER);
            Thread.sleep((long) (Math.random() * 5000 + 1000));
            semaphore.release();
            System.out.printf("Посетитель %d покинул библиотеку \n", PERSON_NUMBER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
