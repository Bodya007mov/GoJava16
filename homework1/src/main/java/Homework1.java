import java.util.Scanner;

public class Homework1 {
    public static void main(String[] args) {
        System.out.println("\t\t\t\t\t\t\t\tКак я провел лето" +
                "\n\tЭто лето началось с того, что я бегал по городу в поисках подходящего помолвочного кольца \n" +
                "чтобы сделать предложение своей будущей жене. Кольцо было куплено и 6 июня я сделал ей предложение - она \n" +
                "согласилась. Свадьбу мы наметили на конец сентября. В конце июня мы поехали в мини-отпуск на море, где, \n" +
                "как ни странно, мы купались в море, загорали на пляже, пиво с рыбкой на пляже и много занимались сексом... " +
                "\n\tПосле возвращения началось активое приготовление к свадьбе: вибирали кольца, платье, костюм, ведущего \n" +
                "и т.д., и т.п. В детали углубляться не буду, но это было с одной стороны волнующее, а с другой - очень \n" +
                "приятное времяпровождение. Вот так я и провел свое лето...");
        System.out.println("\n\n\n");

        /*-------Задание 2-------*/
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя");
        String name = sc.nextLine();
        System.out.println("Введите фамилию");
        String surname = sc.nextLine();
        System.out.println("Введите возраст");
        String age = sc.nextLine();
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

        /*-------Задание 3-------*/
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
