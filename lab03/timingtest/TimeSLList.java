package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
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

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        SLList<Integer> list = new SLList<>();

        // Generate list of N's starting at 1000 and doubling until 8
        int n = 1000;
        for (int i = 1; i <= 4; i++) {
            Ns.addLast(n);
            n = n * 2;
        }

        int operations = 1000;
        // for every N
        for (int i = 0; i < Ns.size(); i++) {
            addNItemsToList(list, Ns.get(i));
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < operations; j++) {
                list.getLast();
            }
            times.addLast(sw.elapsedTime());
            opCounts.addLast(operations);
        }

        printTimingTable(Ns, times, opCounts);
    }

    private static void addNItemsToList(SLList<Integer> list, int N) {
        for (int i = 0; i < N; i++) {
            list.addLast(1);
        }
    }

}
