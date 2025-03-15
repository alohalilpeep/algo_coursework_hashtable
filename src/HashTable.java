import java.util.*;

public class HashTable<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private MyArrayList<T>[] table;
    private int size;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int initialCapacity) {
        table = new MyArrayList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            table[i] = new MyArrayList<>();
        }
        size = 0;
    }

    private int getIndex(T key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode % table.length);
    }

    public void add(T element) {
        if (element == null) {
            throw new NullPointerException("Null elements are not allowed");
        }

        int index = getIndex(element);
        MyArrayList<T> bucket = table[index];

        if (!bucket.contains(element)) {
            bucket.add(element);
            size++;

            if ((double) size / table.length > LOAD_FACTOR) {
                rehash();
            }
        }
    }

    public boolean remove(T element) {
        if (element == null) {
            return false;
        }

        int index = getIndex(element);
        MyArrayList<T> bucket = table[index];

        if (bucket.remove(element)) {
            size--;
            return true;
        }
        return false;
    }

    public boolean contains(T element) {
        if (element == null) {
            return false;
        }

        int index = getIndex(element);
        MyArrayList<T> bucket = table[index];

        return bucket.contains(element);
    }

    private void rehash() {
        MyArrayList<T>[] oldTable = table;
        table = new MyArrayList[oldTable.length * 2];

        for (int i = 0; i < table.length; i++) {
            table[i] = new MyArrayList<>();
        }

        size = 0;

        for (MyArrayList<T> bucket : oldTable) {
            for (T element : bucket) {
                add(element);
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<T> {
        private int currentBucketIndex = 0;
        private Iterator<T> currentBucketIterator = table[0].iterator();

        @Override
        public boolean hasNext() {
            if (currentBucketIterator.hasNext()) {
                return true;
            } else {
                while (++currentBucketIndex < table.length) {
                    currentBucketIterator = table[currentBucketIndex].iterator();
                    if (currentBucketIterator.hasNext()) {
                        return true;
                    }
                }
                return false;
            }
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the HashTable");
            }
            return currentBucketIterator.next();
        }
    }

    public void printTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.print("Bucket " + i + ": ");
            for (T element : table[i]) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}