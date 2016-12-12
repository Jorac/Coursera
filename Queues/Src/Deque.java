
import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private Item[] a;
    private int n = 0;
    private int head = 0;
    private int tail = 1;

    /**
     * construct an empty deque
     */
    public Deque() {
        a = (Item[]) new Object[3];
    }

    /**
     * is the deque empty?
     * 
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * @return the number of items on the deque
     */
    public int size() {
        return n;
    }

    /**
     * add the item to the front
     * 
     * @param item
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        // if array almost filled with items - grow array;
        if (n == a.length - 1) {
            resize(a.length * 2);
        }
        a[head--] = item;
        n++;
        // if head-- goes to -1 then point head to the last index in array(cycle
        // array)
        if (head < 0) {
            head = a.length - 1;
        }
    }

    /**
     * add the item to the end
     * 
     * @param item
     */
    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        if (n == a.length - 1) {
            resize(a.length * 2);
        }
        a[tail++] = item;
        n++;
        if (tail >= a.length) {
            tail = 0;
        }
    }

    /**
     * remove and return the item from the front
     * 
     * @return
     */
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        head++;
        if (head >= a.length) {
            head = 0;
        }
        n--;
        Item x = a[head];
        if (n == 0) {
            resize(0);
        } else if (n <= a.length / 4) {
            resize(a.length / 2);
        }
        return x;
    }

    /**
     * remove and return the item from the end
     * 
     * @return
     */
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        tail--;
        if (tail < 0) {
            tail = a.length - 1;
        }
        n--;
        Item x = a[tail];
        if (n == 0) {
            resize(0);
        } else if (n <= a.length / 4) {
            resize(a.length / 2);
        }
        return x;
    }

    private void resize(int len) {
        if (len == 0) {
            Item[] b = (Item[]) new Object[3];
            a = b;
            head = 0;
            tail = 1;
            return;
        }
        Item[] b = (Item[]) new Object[len];
        int i = (head + 1 >= a.length) ? 0 : head + 1;
        int j = 1;
        do {
            b[j++] = a[i];
            i = (++i >= a.length) ? 0 : i;
        } while (i != tail);
        a = b;
        head = 0;
        tail = head + n + 1;
    }

    /**
     * return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int current = (head + 1 >= a.length) ? 0 : head + 1;

        public boolean hasNext() {
            return current != tail;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item x = a[current];
            current = (++current >= a.length) ? 0 : current;
            return x;
        }
    }

    private void print(Iterable<Item> arr) {
        for (Item i : arr)
            StdOut.print(i + " ");
        StdOut.println();
    }

    /**
     * unit testing
     * 
     * @param args
     */
    public static void main(String[] args) {
        Deque<Integer> q = new Deque<Integer>();
//        q.addFirst(1);
//        StdOut.println("size = " + q.size());
//        q.print(q);
//        q.addFirst(2);
//        StdOut.println("size = " + q.size());
//        q.print(q);
//        q.removeFirst();
//        StdOut.println("size = " + q.size());
//        q.print(q);
//        q.removeLast();
//        StdOut.println("size = " + q.size());
//        q.print(q);
        // addFirst + removeLast
        q.addFirst(1);
        q.addLast(2);
        StdOut.println("removed = " + q.removeFirst());
        q.addLast(5);
        q.addLast(6);
        StdOut.println("size = " + q.size());
        q.print(q);
//        q.addFirst(0);
//        q.isEmpty();
//        StdOut.println("size = " + q.size()+ " removed = "+q.removeFirst());
//        q.print(q);
    }

}
