package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        Stopwatch sw = new Stopwatch();
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        // generate list of N's to test
        int n = 1000;
        for (int i = 1; i <= 8; i++) {
            Ns.addLast(n);
            n = n * 2;
        }

        AList<Integer> list;

        // runs addLast method N times
        for (int i = 0; i < Ns.size(); i++) {
            list = new AList<>();
            int N = Ns.get(i);

            for (int j = 0; j < N; j++) {
                list.addLast(1);
            }
            // record time elapsed for N
            times.addLast(sw.elapsedTime());
            opCounts.addLast(N);
        }

        printTimingTable(Ns, times, opCounts);
    }
}
