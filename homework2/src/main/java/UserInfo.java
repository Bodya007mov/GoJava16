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

        System.out.println("Введите " + '\u0022' + "табличный" + '\u0022' + " " + '\u0022' + "текстовый" + '\u0022' +
                " или " + '\u0022' + "иной" + '\u0022' + " для соответствующего отображения информации:");
        String displayOption = validateInput(sc);
        if (displayOption.equals("табличный")) {
            System.out.println("Имя:         " + name + "\n" +
                    "Город:       " + city + "\n" +
                    "Возраст:     " + age + "\n" +
                    "Хобби:       " + hobby);
        }
        else if (displayOption.equals("текстовый")) {
            System.out.println("Человек по имени " + name + " живет в городе " + city + ".\n" +
                    "Этому человеку " + age + " лет и любит он заниматься " + hobby + ".");
        }
        else if (displayOption.equals("иной")) {
            System.out.println(name + " - имя\n" + city + " - город\n" + age + " - возраст\n" + hobby + " - хобби");
        }

    }

    private static String validateInput (Scanner sc) {
        String validInput = sc.nextLine();
        while (!validInput.equals("табличный") && !validInput.equals("текстовый") && !validInput.equals("иной")){
            System.out.println("Неверный ввод! Повторите попытку: ");
            validInput = sc.nextLine();
        }
        return validInput;
    }
}
