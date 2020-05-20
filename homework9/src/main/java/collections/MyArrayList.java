package collections;

import java.util.Arrays;

public class MyArrayList<T> {

    private T[] elementData;
    private int size;

    public MyArrayList() {
        this.elementData = (T[]) new Object[10];
    }

    public MyArrayList(int capacity) {
        this.elementData = (T[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if(size + 1 > elementData.length) {
            T[] oldElementData = Arrays.copyOf(elementData, size);
            elementData = (T[]) new Object[elementData.length * 3 / 2 + 1];
            System.arraycopy(oldElementData, 0, elementData, 0, size);
        }
    }

    public void add(T element) {
        ensureCapacity();
        elementData[size++] = element;
    }

    public void remove(int index) {
        if(index < size) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
            elementData[--size] = null;
        } else {
            System.out.println("Element doesn't exist!");
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    public T get(int index) {
        return elementData[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
