package collections;

import java.util.Arrays;

public class MyQueue<T>{

    private int size;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {

        private T element;
        private Node<T> next;
        private Node<T> prev;

        private Node(T element, Node<T> next, Node<T> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

    }

    public int size() {
        return size;
    }

    public void add(T element) {
        Node<T> tempLast = last;
        Node<T> node = new Node<>(element, null, tempLast);
        last = node;
        if (tempLast == null) {
            first = node;
        } else {
            tempLast.next = node;
        }
        size++;

    }

    private Node<T> node(int index) {
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void removeLinks(Node<T> x) {
        Node<T> next = x.next;
        Node<T> prev = x.prev;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.element = null;
        size--;
    }

    public void remove(int index) {
        removeLinks(node(index));
    }

    public void clear() {
        Node<T> node = first;
        while(node != null) {
            Node<T> next = node.next;
            node.element = null;
            node.next = null;
            node.prev = null;
            node = next;
        }
        first = null;
        last = null;
        size = 0;
    }

    public T peek() {
        return first.element;
    }

    public T poll() {
        if(first != null) {
            T element = first.element;
            remove(0);
            return element;
        } else {
            return null;
        }

    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<T> node = first; node != null; node = node.next)
            result[i++] = node.element;
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
