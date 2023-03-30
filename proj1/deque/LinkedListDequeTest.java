package deque;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.Assert.*;
import edu.princeton.cs.algs4.Stopwatch;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /* Adds one item to the front, checking addFirst() and get() are correct.
     */
    public void addFirstTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        Integer expected = 1;
        lld.addFirst(1);
        Integer actual = lld.get(0);
        assertEquals(expected, actual);
    }
    @Test
    /* Adds three items and checks index 0,1,2 for the correct item
    */
    public void getTest() {
        LinkedListDeque<String> lld = new LinkedListDeque<>();
        lld.addFirst("c");
        lld.addFirst("b");
        lld.addFirst("a");

        assertEquals("a", lld.get(0));
        assertEquals("b", lld.get(1));
        assertEquals("c", lld.get(2));
    }

    @Test
    /* Adds three items using addLast() then checks that the order of items are correct.
    */
    public void addLastTest() {
        LinkedListDeque<String> lld = new LinkedListDeque<>();
        lld.addLast("a");

        assertEquals("a", lld.get(0));
    }


    @Test
    /* Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    public void printDequeTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.printDeque();
        System.out.println("");

        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);
        lld.printDeque();
        System.out.println("1 2 3");

    }

    @Test
    /* Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {
        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertNull("Should return null when removeFirst is called on an empty Deque,", lld1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void testEquals() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(1);
        lld.addLast(2);
        lld.addLast(3);
        assertTrue(lld.equals(lld));

        LinkedListDeque<Integer> shorter = new LinkedListDeque<>();
        shorter.addLast(1);
        shorter.addLast(2);
        assertFalse(lld.equals(shorter));

        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        lld2.addLast(1);
        lld2.addLast(2);
        lld2.addLast(2);
        assertFalse(lld.equals(lld2));

        LinkedList<Integer> javall = new LinkedList<>();
        javall.addLast(1);
        javall.addLast(2);
        javall.addLast(3);
        assertFalse("Should return false if object is not of same type", lld.equals(javall));
    }

    @Test
    public void testRemoveLast() {
        LinkedListDeque<String> lld = new LinkedListDeque<>();
        lld.addLast("a");
        lld.addLast("b");
        lld.addLast("c");

        assertEquals("c", lld.removeLast());
        assertEquals("b", lld.get(lld.size() - 1));
    }

    /* Adds three items to the linked list and calls getRecursive on all three items
     */
    @Test
    public void testGetRecursive() {
        LinkedListDeque<String> lld = new LinkedListDeque<>();
        lld.addLast("a");
        lld.addLast("b");
        lld.addLast("c");

        assertEquals("a", lld.getRecursive(0));
        assertEquals("b", lld.getRecursive(1));
        assertEquals("c", lld.getRecursive(2));
    }

    /* prints formatted table of results from timing test */
    private static void printTimingTable(ArrayList<Integer> Ns, ArrayList<Double> times, ArrayList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void timingTestAdd() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        ArrayList<Double> times = new ArrayList<>();
        ArrayList<Integer> Ns = new ArrayList<>();
        // generate list of N's to test
        int numItems = 1000;
        for (int i = 1; i <= 8; i++) {
            Ns.add(numItems);
            numItems = numItems * 2;
        }

        for (Integer N : Ns) {
            Stopwatch sw = new Stopwatch();
            for(int i = 0; i < N; i++) {
                lld.addFirst(1);
            }
            times.add(sw.elapsedTime());
        }

        printTimingTable(Ns, times, Ns);
    }


    public static void main(String[] args) {
        timingTestAdd();
    }
}
