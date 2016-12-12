/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

/**
 *
 * @author Egor
 */
public class DequeTest {

    public DequeTest() {
    }


    /**
     * Test of isEmpty method after inserting and deleting elements, of class
     * Deque.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("Deque:isEmpty()");
        Deque queue = new Deque();
        boolean expResult = true;

        queue.addFirst(1);
        queue.addFirst(2);
        queue.addFirst(3);
        queue.addFirst(4);
        queue.addLast(9);
        queue.addLast(8);
        queue.addLast(7);
        queue.addLast(6);
        queue.removeFirst();
        queue.removeFirst();
        queue.removeFirst();
        queue.removeFirst();
        queue.removeFirst();
        queue.removeFirst();
        queue.removeFirst();
        queue.removeFirst();
        boolean result = queue.isEmpty();

        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class Deque.
     */
    @Test
    public void testSize() {
        System.out.println("Deque:size()");
        Deque instance = new Deque();
        int expResult = 0;

        int result = instance.size();

        assertEquals(expResult, result);
    }

    /**
     * Test of addFirst method, of class Deque.
     */
    @Test
    public void testAddFirst() {
        System.out.println("Deque:addFirst()");
        Object item = 3123;
        Deque instance = new Deque();

        instance.addFirst(item);
        Object actual = instance.removeFirst();
        System.out.println("actual = " + actual);

        assertEquals(item, actual);
    }

    /**
     * Test of addLast method, of class Deque.
     */
    @Test
    public void testAddLast() {
        System.out.println("Deque:addLast()");
        Object item = 598;
        Deque instance = new Deque();

        instance.addLast(item);
        Object actual = instance.removeLast();
        System.out.println("actual = " + actual);

        assertEquals(item, actual);
    }

    /**
     * Test of removeFirst method, of class Deque.
     */
    @Test
    public void testRemoveFirst() {
        System.out.println("Deque:removeFirst()");
        Deque instance = new Deque();
        Object expResult = 0;

        instance.addFirst(1);
        instance.addFirst(expResult);
        instance.addLast(2);
        Object result = instance.removeFirst();

        assertEquals(expResult, result);
    }

    /**
     * Test of removeLast method, of class Deque.
     */
    @Test
    public void testRemoveLast() {
        System.out.println("Deque:removeLast()");
        Deque instance = new Deque();
        Object expResult = 555;

        instance.addFirst(expResult);
        instance.addFirst(1);
        instance.addFirst(2);
        Object result = instance.removeLast();

        assertEquals(expResult, result);
    }

    /**
     * Test of iterator method, of class Deque.
     */
    @Test
    public void testIterator() {
        System.out.println("Deque:iterator()");
        Deque instance = new Deque();
        Iterator expResult = null;

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

//        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class Deque.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Deque.main(args);

    }

}
