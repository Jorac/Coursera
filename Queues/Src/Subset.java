import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

    public static void main(String[] args) {
        int k = 0;
        RandomizedQueue<String> que = new RandomizedQueue<String>();
        if (args.length > 0) {
            k = Integer.parseInt(args[0]);
        }
        while (!StdIn.isEmpty()) {
            que.enqueue(StdIn.readString());
        }
        while (k-- > 0) {
            StdOut.print(que.dequeue() + " ");
        }
    }

}
