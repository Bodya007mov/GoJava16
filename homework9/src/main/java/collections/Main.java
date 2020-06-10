package collections;

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

//        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
//        myLinkedList.add("str1");
//        myLinkedList.add("str2");
//        myLinkedList.add("str3");
//        myLinkedList.add("str4");
//        myLinkedList.add("str5");
//        System.out.println(myLinkedList.size());
//        System.out.println(myLinkedList);
//
//        myLinkedList.remove(3);
//        System.out.println(myLinkedList.size());
//        System.out.println(myLinkedList);
//
//        System.out.println(myLinkedList.get(3));
//
//        myLinkedList.clear();
//        System.out.println(myLinkedList.size());
//        System.out.println(myLinkedList);

//        MyQueue<String> myQueue = new MyQueue<>();
//        myQueue.add("str1");
//        myQueue.add("str2");
//        myQueue.add("str3");
//        myQueue.add("str4");
//        myQueue.add("str5");
//        System.out.println(myQueue.size());
//        System.out.println(myQueue);
//
//        System.out.println(myQueue.peek());
//        System.out.println(myQueue);
//
//        System.out.println(myQueue.poll());
//        System.out.println(myQueue);
//        System.out.println(myQueue.poll());
//        System.out.println(myQueue);

//        MyStack<String> myStack = new MyStack<>();
//        myStack.add("str1");
//        myStack.add("str2");
//        myStack.add("str3");
//        myStack.add("str4");
//        myStack.add("str5");
//        System.out.println(myStack.size());
//        System.out.println(myStack);
//
//        System.out.println(myStack.peek());
//        System.out.println(myStack);
//
//
//        System.out.println(myStack.pop());
//        System.out.println(myStack);
//        System.out.println(myStack.pop());
//        System.out.println(myStack);

        MyHashMap<Integer, String> hashMap = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put(i, "str" + i);
        }
        hashMap.print();
        System.out.println("size: " + hashMap.size());

        hashMap.remove(2);
        hashMap.print();
        System.out.println("size: " + hashMap.size());

        System.out.println(hashMap.get(5));

        hashMap.clear();
        hashMap.print();
        System.out.println("size: " + hashMap.size());
    }
}
