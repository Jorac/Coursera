
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] lattice;
    private int num;
    private UF quickUnion;
    private static final int BLOCKED = 0;
    private static final int OPEN = 1;
    private static final int BOTCON = 2;
    private static final int TOPCON = 4;
    private int[] status;

    /**
     * create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw (new java.lang.IllegalArgumentException());
        }
        quickUnion = new UF(n * n);   // create UF for all sites + 2 nodes at top 
        //& bot
        status = new int[n * n];    // determine if this site is filled to avoid backwash
//        lattice = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                lattice[i][j] = 0;
                status[i * n + j] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            status[i] = 3;
            status[xyTo1D(num - 1, i)] = 4;
        }
        num = n;
    }

    /**
     * convert row and column indexes to 1-dimensional union find arr
     */
    private int xyTo1D(int x, int y) {
        return x * num + y;
    }

    /**
     * check if indexes is out of bound then throw.
     */
    private void validateIdx(int x, int y) {
        if (x <= 0 || x > num) {
            throw new IndexOutOfBoundsException("row index out of bounds");
        }
        if (y <= 0 || y > num) {
            throw new IndexOutOfBoundsException("col index out of bounds");
        }
    }

    /**
     * open site (row, col) if it is not open already
     */
    public void open(int row, int col) {
        validateIdx(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int i = row - 1;
        int j = col - 1;
//        lattice[i][j] = 1;
        if (i > 0) {
            if (isOpen(i, col)) {
                if (status[quickUnion.root(xyTo1D(i - 1, j))] == 5) {
                    status[xyTo1D(i, j)] |= 5;
                }
//                if ((status[(i - 1) * num + j] & 2) > 0) {
//                    status[i * num + j] = 3;
//                }
                // Union with top site if its open
                quickUnion.union(xyTo1D(i, j), xyTo1D(i - 1, j));
            }
        }
//            else if (i == 0) {
//            //connect top layer of sites with top of UF
//            quickUnion.union(0, j + 1);
//        }
        if (j > 0) {
            if (isOpen(row, j)) {
                if (status[quickUnion.root(xyTo1D(i, j + 1))] == 5) {
                    status[xyTo1D(i, j)] |= 5;
                }
//                if ((status[i * num + j - 1] & 2) > 0) {
//                    status[i * num + j] = 3;
//                }
                // Union with left site if its open
                quickUnion.union(xyTo1D(i, j), xyTo1D(i, j - 1));
            }
        }
        if (i < num - 1) {
            if (isOpen(row + 1, col)) {
                if (status[quickUnion.root(xyTo1D(i + 1, j))] == 5) {
                    status[xyTo1D(i, j)] |= 5;
                }
//                if ((status[(i + 1) * num + j] & 2) > 0) {
//                    status[i * num + j] = 3;
//                }
                // Union with bottom site if its open
                quickUnion.union(xyTo1D(i, j), xyTo1D(i + 1, j));
            }
        }
//            else if (i == num - 1) {
//            // and bottom layer to bottom of UF
//            quickUnion.union(num * num + 1, xyTo1D(i, j));
//        }
        if (j < num - 1) {
            if (isOpen(row, col + 1)) {
                if (status[quickUnion.root(xyTo1D(i, j + 1))] == 5) {
                    status[xyTo1D(i, j)] |= 5;
                }
//                if ((status[i * num + j + 1] & 2) > 0) {
//                    status[i * num + j] = 3;
//                }
                // Union with right site if its open
                quickUnion.union(xyTo1D(i, j), xyTo1D(i, j + 1));
            }
        }
    }

    /**
     * is site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        validateIdx(row, col);
        return (status[xyTo1D(row-1, col-1)] & OPEN) > 0;
//        return lattice[row - 1][col - 1] > 0;
    }

    /**
     * is site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        validateIdx(row, col);
        return (status[xyTo1D(row - 1, col - 1)] & 5) > 0;
//        return quickUnion.connected(0, xyTo1D(row - 1, col - 1));
    }

    /**
     * does the system percolate?
     */
    public boolean percolates() {
        return true;
//        return quickUnion.connected(0, num * num + 1);
    }

    public int getParent(int id) {
        return quickUnion.root(id);
    }

    /**
     * test client (optional)
     */
    public static void main(String[] args) {
//        int n = 20;
//        Percolation p = new Percolation(n);
//        while (!p.percolates()) {
//           int x = StdRandom.uniform(1,n+1);
//           int y = StdRandom.uniform(1,n+1);
//           p.open(x,y);
//        }
//       p.print();
//       StdOut.printf("OpenSites = %d, n*n = %d Fraction = %f\n",p.getOpenSites(), n*n, ((double)(p.getOpenSites())/(n*n)));
    }
}
