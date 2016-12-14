import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int n = 0;
    private int top = 0;

    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
        a = (Item[]) new Object[1];
    }

    /**
     * is the queue empty?
     * 
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * return the number of items on the queue
     * 
     * @return
     */
    public int size() {
        return n;
    }

    /**
     * add the item
     * 
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();
        a[top++] = item;
        n++;
        if (n >= a.length) {
            resize(a.length * 2);
        }
    }

    /**
     * remove and return a random item
     * 
     * @return
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int rnd = StdRandom.uniform(0, n);
        Item x = a[rnd];
        a[rnd] = a[--top];
        a[top] = null;
        n--;
        if (n <= a.length / 4) {
            resize(a.length / 2);
        }
        return x;
    }

    /**
     * return (but do not remove) a random item
     * 
     * @return
     */
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int rnd = StdRandom.uniform(0, n);
        return a[rnd];
    }

    private void resize(int len) {
        Item[] b = (Item[]) new Object[len];
        for (int i = 0; i < n; i++) {
            b[i] = a[i];
        }
        a = b;
    }

    /**
     * return an independent iterator over items in random order
     * 
     * @return
     */
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int current;

        public ArrayIterator() {
            if (!isEmpty()) {
                StdRandom.shuffle(a, 0, n - 1);
            }
            current = top - 1;
        }

        public boolean hasNext() {
            return current >= 0;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item x = a[current--];
            return x;
        }
    }

    private void print(Iterable<Item> arr) {
        for (Item i : arr)
            StdOut.println(i);
    }

    /**
     * unit testing
     * 
     * @param args
     */
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        for (int i = 0; i < 64; i++) {
            q.enqueue(i);
            // StdOut.println("size = " + q.size());
        }
        q.print(q);
    }
}
