package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E> { 

	private DNode head;
	private DNode tail;

	@Override
	public boolean add(E e) {
		DNode newNode = new DNode();
		newNode.element = e;
		if (e == null) {
			return false;
		} else if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		return true;
	}

	@Override
	public E remove() {
		if (this.size == 0){
			throw new NoSuchElementException("The list has no elements");
		}
		DNode temp = tail;
		if (this.size == 1) {
			head = null;
		} else {
			tail.prev.next = null;
		}
		tail = tail.prev;
		temp.prev = null;
		this.size--;
		return temp.element;
	}

	public boolean addFirst(E element) {
		if (element == null){
			return false;
		}
		DNode newNode = new DNode();
		newNode.element = element;
		newNode.next = head;
		head.prev = newNode;
		if (head == null) {
			tail = newNode;
			head.prev = null;
		}
		head = newNode;
		this.size++;
		return true;
	}

	public boolean addLast(E element){
		return add(element);
	}

	public E removeFirst(){
		if (this.size == 0) {
			throw new NoSuchElementException("The list is empty");
		}
		DNode temp = head;
		if (this.size == 1) {
			tail = null;
		} else {
			head.next.prev = null;
		}
		head = head.next;
		temp.next = null;
		this.size--;
		return temp.element;
	}

	public E removeLast() {
		return remove();
	}

	public E peekFirst() {
		return head.element;
	}

	public E peekLast() {
		return tail.element;
	}

	@Override
	public void clear() {
		while (this.size != 0) {
			this.removeLast();
			this.size--;
		}
	}

	private class DNode {
		private E element;
		private DNode next;
		private DNode prev;
	}
	
	public Iterator<E> iterator() {
		return new DLLIterator();
	}
	
	private class DLLIterator implements Iterator<E> {
		DNode curr;

		public DLLIterator() {
			this.curr = head;
		}

		public boolean hasNext() {
			return this.curr != null;
		}

		public E next() {
			if (!this.hasNext())
				throw new NoSuchElementException();

			E element = this.curr.element;
			this.curr = this.curr.next;
			return element;
		}
	}
}
