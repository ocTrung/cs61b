package deque;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

public class ArrayDequeTest {
    /* Helper function to quickly add N items to ArrayDeque.
       Starts at 1.
     */
    public void addNItems(ArrayDeque<Integer> l, int N) {
        for(int i = 1; i <= N; i++) {
            l.addLast(i);
        }
    }

    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        final int N = 9;

        for (int i = 1; i < N; i++) {
            ad.addFirst(i);
        }
        System.out.println("before resize");
        ad.printDeque();

        ad.addFirst(N);
        System.out.println("after resize");
        ad.printDeque();
    }

    @Test
    /* Adds items using addLast then checks for correct order using printDeque
     */
    public void testAddLast() {
        ArrayDeque<Integer> adl = new ArrayDeque<>();
        int N = 8;
        for(int i = 1; i <= N; i++) {
            adl.addLast(i);
        }
        System.out.println("before resize");
        adl.printDeque();
        adl.addLast(N + 1);
        System.out.println("after resize");
        adl.printDeque();
    }

    @Test
    public void testResize() {
        ArrayDeque<Integer> adl = new ArrayDeque<>();
        int N = 32;
        addNItems(adl, N);
        int M = N / 4 * 3 + 1;
        for(int i = 0; i < M; i++) {
            adl.removeFirst();
        }
        assertEquals(N - M, adl.size());
        System.out.println("After reducing usage to < %25");
        adl.printDeque();
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> adl = new ArrayDeque<>();
        int N = 8;
        addNItems(adl, N);
        int item;
        for(int i = 0; i < N; i++) {
            item = adl.get(i);
            assertEquals(i + 1, item);
        }
        adl.printDeque();
    }

    @Test
    public void testRemove() {
        ArrayDeque<Integer> adl = new ArrayDeque<>();
        int N = 8;
        for(int i = 1; i <= N; i++) {
            adl.addLast(i);
        }
        System.out.println("Before removeFirst");
        adl.printDeque();

        assertEquals(1, (int) adl.removeFirst());
        assertEquals(2, (int) adl.removeFirst());
        assertEquals(3, (int) adl.removeFirst());
        assertEquals(4, (int) adl.removeFirst());

        System.out.println("After 4 removeFirst calls");
        adl.printDeque();

        assertEquals(8, (int) adl.removeLast());
        assertEquals(7, (int) adl.removeLast());
        assertEquals(6, (int) adl.removeLast());

        System.out.println("After 3 removeLast calls");
        adl.printDeque();
    }

    @Test
    public void testEquals() {
        ArrayDeque<Integer> adl = new ArrayDeque<>();
        addNItems(adl, 4);
        assertTrue(adl.equals(adl));

        ArrayDeque<Integer> same = new ArrayDeque<>();
        addNItems(same, 4);
        assertTrue(adl.equals(same));

        ArrayDeque<Integer> longer = new ArrayDeque<>();
        addNItems(adl, 20);
        assertFalse(adl.equals(longer));

        ArrayDeque<Integer> diff = new ArrayDeque<>();
        diff.addLast(8);
        diff.addLast(9);
        diff.addLast(10);
        diff.addLast(11);
        assertFalse(adl.equals(diff));
    }

}
