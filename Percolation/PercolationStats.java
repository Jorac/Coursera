
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private int num;
    private int t;
    private double mean;
    private double dev;
    private double[] a;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        mean = 0;
        dev = 0;
        t = trials;
        num = n;
        a = new double[t];
        int openSites;
        for (int i = 0; i < t; i++) {
            openSites = 0;
            Percolation perc = new Percolation(num);
            while (!perc.percolates()) {
                int x = StdRandom.uniform(1, num + 1);
                int y = StdRandom.uniform(1, num + 1);
                if (!perc.isOpen(x, y)) {
                    openSites++;
                }
                perc.open(x, y);
            }
            a[i] = (double) openSites / (num * num);
            mean = StdStats.mean(a);
            dev = StdStats.stddev(a);
        }
    }
    // sample mean of percolation threshold

    public double mean() {
        return mean;
    }
    // sample standard deviation of percolation threshold

    public double stddev() {
        return dev;
    }
    // low  endpoint of 95% confidence interval

    public double confidenceLo() {
        return mean - 1.96 * dev / Math.sqrt(t);
    }
    // high endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean + 1.96 * dev / Math.sqrt(t);
    }

    // test client (described below)
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new java.lang.IllegalArgumentException();
        }
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats pcst = new PercolationStats(n, t);
        StdOut.printf("mean\t\t\t = %.16f\nstddev\t\t\t = %.16f\n95%% confidence interval\t = %.16f, %16f\n", pcst.mean(), pcst.stddev(), pcst.confidenceLo(), pcst.confidenceHi());
    }
}
