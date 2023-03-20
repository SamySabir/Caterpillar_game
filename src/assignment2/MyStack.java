package assignment2;

import java.util.NoSuchElementException;

public class MyStack<E> {
    private MyDoublyLinkedList<E> stack;

    public MyStack() {
        this.stack = new MyDoublyLinkedList<E>();
    }

    public boolean push(E element){
        if (element == null){
            return false;
        }
        return this.stack.addFirst(element);
    }

    public E pop(){
        if (this.isEmpty()){
            throw new NoSuchElementException("The Stack is empty");
        }
        return this.stack.removeFirst();
    }

    public E peek(){
        if (this.isEmpty()){
            throw new NoSuchElementException("The Stack is empty");
        }
        return this.stack.peekFirst();
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public void clear(){
        this.stack.clear();
    }

    public int getSize() {
        return this.stack.size;
    }

    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<String>();
        stack.stack.add("2");
        stack.stack.add("2");
        stack.push("3");
        System.out.println(stack.getSize());
    }
}
