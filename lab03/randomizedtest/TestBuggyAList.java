package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        int[] testVals = {1, 2, 3};
        AListNoResizing<Integer> expectedList = new AListNoResizing<>();
        BuggyAList<Integer> actualList = new BuggyAList<>();

        // add three values to lists we are testing
        for (int val : testVals) {
            expectedList.addLast(val);
            actualList.addLast(val);
        }

        // test if equal after every removeLast() call
        for (int i = 0; i < testVals.length; i++) {
            assertEquals(expectedList.removeLast(), actualList.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = correct.size();
                int brokenSize = broken.size();
                assertEquals(size, brokenSize);
            } else if (correct.size() > 0 && operationNumber == 2) {
                // getLast
                int last = correct.getLast();
                int brokenLast = broken.getLast();
                assertEquals(last, brokenLast);
            } else if (correct.size() > 0 && operationNumber == 3) {
                // removeLast
                int removed = correct.removeLast();
                int brokenRemoved = broken.removeLast();
                assertEquals(removed, brokenRemoved);
            }
        }
    }
}
