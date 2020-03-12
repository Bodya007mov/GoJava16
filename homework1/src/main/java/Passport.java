import java.util.Scanner;

public class Passport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя");
        String name = sc.nextLine();
        System.out.println("Введите фамилию");
        String surname = sc.nextLine();
        System.out.println("Введите возраст");
        byte age = sc.nextByte();
        sc.nextLine();
        System.out.println("Введите город рождения");
        String borntown = sc.nextLine();
        System.out.println("Введите город проживания");
        String livetown = sc.nextLine();
        System.out.println("Введите пол");
        String gender = sc.nextLine();

        System.out.println("--------------------PASSPORT--------------------\n" +
                "------------------------------------------------\n" +
                "-----\n" +
                "----- Person: " + name + " " + surname + "\n" +
                "-----\n" +
                "----- Gender: " + gender + ", age: " + age + "\n" +
                "-----\n" +
                "----- Location: from - " + borntown + ", current - " + livetown + "\n" +
                "-----\n" +
                "------------------------------------------------\n" +
                "------------------------------------------------\n");
    }
}
