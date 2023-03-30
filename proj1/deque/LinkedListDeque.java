package deque;

import edu.princeton.cs.algs4.StdOut;

public class LinkedListDeque<T> {
    class genericNode {
        T item;
        genericNode next;
        genericNode prev;

        genericNode(T item, genericNode prev, genericNode next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

    }

    genericNode sentinelStart;
    genericNode sentinelEnd;
    int size = 0;

    public LinkedListDeque() {
        sentinelStart = new genericNode(null, null, null);
        sentinelEnd = new genericNode(null, sentinelStart, null);
        sentinelStart.next = sentinelEnd;
    }

    /** Adds item to the front of the deque */
    public void addFirst(T item) {
        genericNode newItem = new genericNode(item, sentinelStart, sentinelStart.next);
        sentinelStart.next.prev = newItem;
        sentinelStart.next = newItem;
        size++;
    }
    /** Returns the item at index. The first item is at index 0 */
    public T get(int index) {
        genericNode p = sentinelStart.next;

        if (index >= size)
            return null;

        while (index != 0) {
            p = p.next;
            index--;
        }

        return p.item;
    }

    public void addLast(T item) {
        genericNode newItem = new genericNode(item, sentinelEnd.prev, sentinelEnd);
        sentinelEnd.prev.next = newItem;
        sentinelEnd.prev = newItem;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StringBuilder deque = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            deque.append(this.get(i)).append(" ");
        }

        if (this.size > 0)
            deque.deleteCharAt(deque.length() - 1);

        System.out.println(deque.toString());
    }

    public T removeFirst() {
        if (size == 0)
            return null;

        genericNode first = sentinelStart.next;
        genericNode second = first.next;
        sentinelStart.next = second;
        second.prev = sentinelStart;
        size--;
        return first.item;
    }

    public T removeLast() {
        if (size == 0)
            return null;

        genericNode last = sentinelEnd.prev;
        genericNode newLast = last.prev;
        newLast.next = sentinelEnd;
        sentinelEnd.prev = newLast;
        size--;
        return last.item;
    }

    public boolean equals(Object a) {
        if (!(a instanceof LinkedListDeque<?>))
            return false;

        if (((LinkedListDeque<?>) a).size() != this.size)
            return false;

        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(((LinkedListDeque<?>) a).get(i))) {
                return false;
            }
        }

        return true;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinelStart.next);
    }

    private T getRecursiveHelper(int index, genericNode curr) {
        if (index == 0)
            return curr.item;
        return getRecursiveHelper(index - 1, curr.next);
    }





}
