package collections;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("str1");
        list.add("str2");
        list.add("str3");
        list.add("str4");
        list.add("str5");
        list.add("str6");
        list.add("str7");
        list.add("str8");
        list.add("str9");
        list.add("str10");
        list.add("str11");
        System.out.println(list.size());
        System.out.println(list);

        list.remove(5);
        System.out.println(list.size());
        System.out.println(list);

        System.out.println(list.get(2));

        list.clear();
        System.out.println(list.size());
        System.out.println(list);
    }
}
