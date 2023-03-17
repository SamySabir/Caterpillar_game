package assignment2;

import java.util.NoSuchElementException;

public class MyStack<E> extends MyDoublyLinkedList {
    private MyDoublyLinkedList<E> stack;

    public MyStack() {
        this.stack = new MyDoublyLinkedList<E>();
    }

    public boolean push(E element){
        if (element == null){
            return false;
        }
        return this.addFirst(element);
    }

    public E pop(){
        if (this.isEmpty()){
            throw new NoSuchElementException("The Stack is empty");
        }
        return (E) this.removeFirst();
    }

    public E peek(){
        if (this.isEmpty()){
            throw new NoSuchElementException("The Stack is empty");
        }
        return (E) this.peekFirst();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public void clear(){
        this.stack.clear();
    }

    public int getSize() {
        return this.stack.size;
    }
}
