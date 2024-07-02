package telran.interviews.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.interviews.MyStackInt;

class MyStackIntTest {
    MyStackInt stack;

    @BeforeEach
    void setUp() {
        stack = new MyStackInt();
    }

    @Test
    void pushTest() {
        stack.push(10);
        assertFalse(stack.isEmpty());
    }

    @Test
    void popTest() {
        stack.push(20);
        assertEquals(20, stack.pop());
        assertTrue(stack.isEmpty());
        assertThrows(RuntimeException.class, () -> stack.pop());
    }

    @Test
    void peekTest() {
        stack.push(30);
        assertEquals(30, stack.peek());
        assertFalse(stack.isEmpty());
        stack.pop();
        assertThrows(RuntimeException.class, () -> stack.peek());
    }

    @Test
    void isEmptyTest() {
        assertTrue(stack.isEmpty());
        stack.push(40);
        assertFalse(stack.isEmpty());
    }

    @Test
    void getMaxElementTest() {
        stack.push(50);
        stack.push(60);
        stack.push(70);
        assertEquals(70, stack.getMaxElement());
        stack.pop();
        assertEquals(60, stack.getMaxElement());
        stack.pop();
        stack.pop();
        assertThrows(RuntimeException.class, () -> stack.getMaxElement());
    }
}
