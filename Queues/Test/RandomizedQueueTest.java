/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Egor
 */
public class RandomizedQueueTest {
    
    public RandomizedQueueTest() {
    }

    @Test
    public void testIsEmpty() {
        System.out.println("RandomizedQueue:isEmpty()");
        RandomizedQueue queue = new RandomizedQueue();
        boolean expResult = true;

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(9);
        queue.enqueue(8);
        queue.enqueue(7);
        queue.enqueue(6);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        boolean result = queue.isEmpty();

        assertEquals(expResult, result);
    }

    @Test
    public void testSize() {
        System.out.println("RandomizedQueue:size()");
        RandomizedQueue instance = new RandomizedQueue();
        int expResult = 0;

        int result = instance.size();

        assertEquals(expResult, result);
    }

    @Test
    public void testEnqueue() {
        System.out.println("RandomizedQueue:enqueue()");
        Object item = 3123;
        RandomizedQueue instance = new RandomizedQueue();

        instance.enqueue(item);
        Object actual = instance.sample();
        System.out.println("actual = " + actual);

        assertEquals(item, actual);
    }

    @Test
    public void testDequeue() {
        System.out.println("RandomizedQueue:dequeue()");
        RandomizedQueue instance = new RandomizedQueue();

        instance.enqueue(1);
        instance.enqueue(0);
        instance.enqueue(2);
        Object result = instance.dequeue();

        assertTrue(result.equals(0) || result.equals(1) || result.equals(2));
    }

    @Test
    public void testSample() {
        System.out.println("RandomizedQueue:sample()");
        RandomizedQueue instance = new RandomizedQueue();

        instance.enqueue(1);
        instance.enqueue(0);
        instance.enqueue(2);
        Object result = instance.sample();

        assertTrue(result.equals(0) || result.equals(1) || result.equals(2));
    }

    @Test
    public void testIterator() {
        System.out.println("RandomizedQueue:iterator()");
        RandomizedQueue instance = new RandomizedQueue();

        Iterator testObject = instance.iterator();
        while (testObject.hasNext()) {
            // Assert condition #3 for hasNext() == true:
            //
            for (int repeat = 0; repeat < 10; repeat++) {
                assertTrue(testObject.hasNext());
            }

            // A violation of condition #1 will be 
            // automatically reported by JUnit: 
            //
            testObject.next();
        }

        // Assert condition #2:
        //
        String caught = null;
        try {
            testObject.next();
        } catch (Throwable throwable) {
            caught = throwable.getClass().getName();
        }
        assertEquals(
                NoSuchElementException.class.getName(),
                caught);

        // Assert condition #4:
        //
        for (int repeat = 0; repeat < 10; repeat++) {
            assertFalse(testObject.hasNext());
        }

        // Assert condition #5:
        //
        for (int repeat = 0; repeat < 10; repeat++) {
            caught = null;
            try {
                testObject.next();
            } catch (Throwable throwable) {
                caught = throwable.getClass().getName();
            }
            assertEquals(
                    NoSuchElementException.class.getName(),
                    caught);
        }
    }

    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        RandomizedQueue.main(args);
    }
    
}
