import java.util.Scanner;

public class UserInfo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите Имя:");
        String name = sc.nextLine();
        System.out.println("Введите город:");
        String city = sc.nextLine();
        System.out.println("Введите возраст:");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Введите хобби:");
        String hobby = sc.nextLine();
    }
}
