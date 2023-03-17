package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<E> {
    private MyDoublyLinkedList<E> queue;

    public MyQueue() {
        this.queue = new MyDoublyLinkedList<E>();
    }

    public boolean enqueue(E element) {
        if (element == null) {
            return false;
        }
        this.queue.addLast(element);
        return true;
    }

    public E dequeue() {
        if (this.queue.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.queue.removeFirst();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public void clear() {
        this.queue.clear();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MyQueue)) {
            return false;
        }
        MyQueue<E> x = (MyQueue<E>) obj;
        return this.queue.equals(x);
    }
}
