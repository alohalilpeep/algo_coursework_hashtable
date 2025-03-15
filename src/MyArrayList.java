import java.util.*;

public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10; // Начальная ёмкость массива
    private T[] elements; // Массив для хранения элементов
    private int size = 0; // Текущий размер списка

    // Конструктор по умолчанию
    public MyArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    // Конструктор с указанной начальной ёмкостью
    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть больше чем 0");
        }
        elements = (T[]) new Object[initialCapacity];
    }

    // Метод для добавления элемента в список
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    // Метод для вставки элемента в список по индексу
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    // Метод для получения элемента по индексу
    public T get(int index) {
        checkIndex(index);
        return elements[index];
    }

    // Удаление элемента по значению
    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                removeAt(i); // Удаляем элемент по индексу
                return true;
            }
        }
        return false; // Элемент не найден
    }


    // Удаление элемента по индексу
    private void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        // Сдвигаем элементы влево
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null; // Очищаем последний элемент
    }

    // Метод для получения текущего размера списка
    public int size() {
        return size;
    }

    // Метод для получения текущей емкости массива
    public int capacity() {
        return elements.length;
    }

    // Метод для проверки индекса на корректность
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Метод для проверки индекса на корректность при добавлении
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Метод для увеличения ёмкости массива при необходимости
    private void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    // Метод для печати элементов списка (для отладки)
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    // Метод для сортировки списка
    public void sort(Comparator<? super T> comparator) {
        // Реализация пузырьковой сортировки
        boolean swapped;
        for (int i = 0; i < size - 1; i++) {
            swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (comparator.compare(elements[j], elements[j + 1]) > 0) {
                    // Меняем элементы местами
                    T temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                    swapped = true;
                }
            }
            // Если не было обменов, список уже отсортирован
            if (!swapped) break;
        }
    }

    // Метод для проверки наличия элемента в списке
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }

    // Метод для получения итератора
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[currentIndex++];
            }
        };
    }
}
