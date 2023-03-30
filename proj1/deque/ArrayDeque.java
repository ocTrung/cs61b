package deque;

import java.util.Arrays;

public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static double usageThreshold = .25;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
    }

    public void addFirst(Item item) {
        if (this.isEmpty()) {
            this.nextFirst = 0;
            this.nextLast = 1;
        } else if (size + 1 > items.length)
            resize(size * 2);

        items[nextFirst] = item;
        this.nextFirst = this.nextFirst == 0 ? items.length - 1 : this.nextFirst - 1;
        size++;
    }

    public void addLast(Item item) {
        if (this.isEmpty()) {
            this.nextFirst = 0;
            this.nextLast = 1;
        }
        else if (size + 1 > items.length)
            resize(size * 2);

        this.items[nextLast] = item;
        this.nextLast = this.nextLast == items.length - 1 ? 0 : this.nextLast + 1;
        size++;
    }

    private void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity];
        int j = getFirstIndex();
        for (int i = 0; i < this.size; i++) {
            newItems[i] = items[j];
            j = j == items.length - 1 ? 0 : j + 1;
        }
        this.nextLast = items.length;
        this.nextFirst = newItems.length - 1;
        items = newItems;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void printDeque() {
        System.out.println(Arrays.toString(items));
    }

    public int size() {
        return this.size;
    }

    public Item get(int index) {
        // the actual index is calculated as an offset from FIRST
        return this.items[actualIndex(index)];
    }

    public Item removeFirst() {
        return removeAt(0);
    }

    public Item removeLast() {
        return removeAt(this.size - 1);
    }

    private Item removeAt(int index) {
        if (this.size == 0)
            return null;
        // get  item
        int actualIndex = actualIndex(index);
        Item item = this.items[actualIndex];
        // clear unused reference
        this.items[actualIndex] = null;

        if (actualIndex == getFirstIndex())
            this.nextFirst = actualIndex;
        else
            this.nextLast = getLastIndex();

        this.size--;

        if (items.length > 16 && usageFactor() < usageThreshold) {
            resize(this.items.length / 2);
        }

        return item;
    }

    private int getFirstIndex() {
        return actualIndex(0);
    }

    private int getLastIndex() {
        return actualIndex(size - 1);
    }

    // Assumes virtual index starts at 0
    private int actualIndex(int index) {
        int first = (this.nextFirst + 1) % this.items.length;
        return (first + index) % this.items.length;
    }

    private float usageFactor() {
        return (float)this.size / this.items.length;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque<?>))
            return false;

        if (this.size != ((ArrayDeque<?>) o).size())
            return false;

        for (int i = 0; i < this.size; i++) {
            if (!this.get(i).equals(((ArrayDeque<?>) o).get(i)))
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
    }
}
