import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class Node {
        public Item data;
        public Node next;
        public Node prev;
    }

    private Node head = null;
    private int n = 0;

    /**
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
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
        Node x = new Node();
        x.data = item;
        if (head == null) {
            head = x;
            x.prev = head;
            x.next = head;
        } else {
            Node oldNext = head.next;
            head.next = x;
            oldNext.prev = x;
            x.next = oldNext;
            x.prev = head;
        }
        n++;
    }

    /**
     * remove and return a random item
     * 
     * @return
     */
    public Item dequeue() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int rnd = StdRandom.uniform(n);
        Node x = head;
        while (rnd-- > 0) {
            x = x.next;
        }
        Item data = x.data;
        x.next.prev = x.prev;
        x.prev.next = x.next;
        n--;
        return data;
    }

    /**
     * return (but do not remove) a random item
     * 
     * @return
     */
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int rnd = StdRandom.uniform(n);
        Node x = head;
        while (rnd-- > 0) {
            x = x.next;
        }
        return x.data;
    }

    /**
     * return an independent iterator over items in random order
     * 
     * @return
     */
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {

        private Node current = head;
        private int count = n;

        public RandomizedIterator() {
            int x = StdRandom.uniform(n);
            while (x-- > 0) {
                current = current.next;
            }
        }

        public boolean hasNext() {
            if (head == null)
                return false;
            if (count > 0)
                return true;
            return false;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            Item x = current.data;
            current = current.next;
            count--;
            return x;
        }
    }

    private void print(Iterable<Item> a) {
        for (Item i : a)
            StdOut.print(i + " ");
        StdOut.println();
    }

    /**
     * unit testing
     * 
     * @param args
     */
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
        q.enqueue(1);
        StdOut.println("size = " + q.size());
        q.print(q);
        q.enqueue(2);
        StdOut.println("size = " + q.size());
        q.print(q);
        q.enqueue(3);
        StdOut.println("size = " + q.size());
        q.print(q);
        q.enqueue(4);
        StdOut.println("size = " + q.size());
        q.print(q);
        q.enqueue(5);
        StdOut.println("size = " + q.size());
        q.print(q);
        q.dequeue();
        StdOut.println("size = " + q.size());
        q.print(q);
        q.dequeue();
        StdOut.println("size = " + q.size());
        q.print(q);
    }
}
