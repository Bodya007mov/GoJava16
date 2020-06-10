package collections;

import java.util.Arrays;

public class MyHashMap<K,V>{

    private Node<K,V>[] table;
    private int size;

    private static class Node<K,V> {

        private final K key;
        private V value;
        private final int hash;
        private Node<K,V> next;

        private Node(K key, V value, int hash, Node<K,V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public String toString() {
            return "hash=" + hash + ", key=" + key + ", value=" + value + " ; ";
        }
    }

    public MyHashMap() {
        this.table = (Node<K,V>[]) new Node[16];
    }

    public int size() {
        return size;
    }

    private int hash(K key) {
        return key.hashCode();
    }

    private int indexFor(int hash, int tableLength) {
        return hash & (tableLength - 1);
    }

    private void transfer(Node<K,V>[] newTable) {
        for (Node<K, V> tableNode : table) {
            if (tableNode != null) {
                int hash = tableNode.hash;
                int index = indexFor(hash, newTable.length);
                newTable[index] = tableNode;
            }
        }
    }

    private void resize() {
        double loadFactor = 0.75;
        int threshold = (int)(table.length * loadFactor);
        if (size > threshold) {
            Node<K,V>[] newTable = (Node<K,V>[]) new Node[table.length << 1];
            transfer(newTable);
            table = newTable;
        }
    }

    private void addNode(K key, V value, int hash, int index) {
        if (table[index] != null) {
            for (Node<K, V> node = table[index]; node != null; node = node.next) {
                if (node.hash == hash && (node.key == key || key.equals(node.key))) {
                    node.value = value;
                    return;
                }
            }
            Node<K, V> node = table[index];
            table[index] = new Node<>(key, value, hash, node);
        } else {
            table[index] = new Node<>(key, value, hash, null);
        }
    }

    public void put(K key, V value) {
        resize();
        if (key != null) {
            int hash = hash(key);
            int index = indexFor(hash, table.length);
            addNode(key, value, hash, index);
            size++;
        }
    }

    public void remove(K key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        Node<K, V> nextNode;
        Node<K, V> node = table[index];
        if (node != null) {
            if (node.hash == hash && (node.key == key || key.equals(node.key))) {
                table[index] = node.next;
                size--;
            } else if ((nextNode = node.next) != null) {
                do {
                    if (nextNode.hash == hash && (nextNode.key == key || key.equals(nextNode.key))) {
                        node.next = nextNode.next;
                        size--;
                        break;
                    }
                    node = nextNode;
                } while ((nextNode = nextNode.next) != null);
            }
        }
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public V get(K key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        Node<K, V> node = table[index];
        if (node.hash == hash && (node.key == key || key.equals(node.key))) {
            return node.value;
        } else if (node.next != null) {
            for (Node<K, V> linkNode = node; linkNode != null; linkNode = linkNode.next) {
                if (linkNode.hash == hash && (linkNode.key == key || key.equals(linkNode.key))) {
                    return linkNode.value;
                }
            }
        }
        return null;
    }

    public void print() {
        int i = 0;
        for (Node<K, V> tableNode : table) {
            System.out.print(i + " : ");
            if (tableNode != null) {
                for (Node<K, V> linkNode = tableNode; linkNode != null; linkNode = linkNode.next) {
                    System.out.print(linkNode);
                }
            } else {
                System.out.print("null");
            }
            i++;
            System.out.println();
        }
    }
}
