package collections;

import java.util.Queue;

public class Main {

    public static void main(String[] args) {
//        MyArrayList<String> myArrayList = new MyArrayList<>();
//        myArrayList.add("str1");
//        myArrayList.add("str2");
//        myArrayList.add("str3");
//        myArrayList.add("str4");
//        myArrayList.add("str5");
//        myArrayList.add("str6");
//        myArrayList.add("str7");
//        myArrayList.add("str8");
//        myArrayList.add("str9");
//        myArrayList.add("str10");
//        myArrayList.add("str11");
//        System.out.println(myArrayList.size());
//        System.out.println(myArrayList);
//
//        myArrayList.remove(5);
//        System.out.println(myArrayList.size());
//        System.out.println(myArrayList);
//
//        System.out.println(myArrayList.get(2));
//
//        myArrayList.clear();
//        System.out.println(myArrayList.size());
//        System.out.println(myArrayList);

        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("str1");
        myLinkedList.add("str2");
        myLinkedList.add("str3");
        myLinkedList.add("str4");
        myLinkedList.add("str5");
        System.out.println(myLinkedList.size());
        System.out.println(myLinkedList);

        myLinkedList.remove(3);
        System.out.println(myLinkedList.size());
        System.out.println(myLinkedList);

        System.out.println(myLinkedList.get(3));

        myLinkedList.clear();
        System.out.println(myLinkedList.size());
        System.out.println(myLinkedList);


    }
}
