package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E> { 

	private DNode head;
	private DNode tail;
	private DNode dummyHead;
	private DNode dummyTail;

	public MyDoublyLinkedList() {
		dummyHead = new DNode();
		dummyTail = new DNode();
		dummyHead.next = dummyTail;
		dummyTail.prev = dummyHead;
	}

	public DNode getNode(int i) {
		DNode node;
		if (i < size/2) {
			node = dummyHead.next;
			for (int k = 0; k < i; k++) {
				node = node.next;
			}
		} else {
			node = dummyTail.prev;
			for (int k = size-1; k > i; k--) {
				node = node.prev;
			}
		}
		return node;
	}

	public E get(int i) {
		DNode node = getNode(i);
		return node.element;
	}

	@Override
	public boolean add(E element) {
		DNode newNode = new DNode();
		newNode.element = element;
		newNode.next = null;
		newNode.prev = this.tail;
		if (element == null) {
			return false;
		} else if (this.tail != null) {
			this.tail.next = newNode;
		} else {
			this.head = newNode;
		}
		this.tail = newNode;
		this.size++;
		return true;
	}

	@Override
	public E remove() {
		if (this.size == 0){
			throw new NoSuchElementException("The list has no elements");
		}
		DNode temp = tail;
		if (this.size == 1) {
			this.head = null;
		} else {
			tail.prev.next = null;
		}
		this.tail = tail.prev;
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
		newNode.prev = null;
		if (head == null) {
			this.tail = newNode;
		} else {
			head.prev = newNode;
		}
		this.head = newNode;
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
			this.tail = null;
		} else {
			head.next.prev = null;
		}
		this.head = head.next;
		temp.next = null;
		temp.prev = null;
		this.size--;
		return temp.element;
	}

	public E removeLast() {
		return remove();
	}

	public E peekFirst() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.head.element;
	}

	public E peekLast() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.tail.element;
	}

	@Override
	public void clear() {
		while (this.size != 0) {
			this.removeLast();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MyDoublyLinkedList)) {
			return false;
		}
		MyDoublyLinkedList<E> x = (MyDoublyLinkedList<E>) obj;
		if (this.isEmpty() && x.isEmpty()) {
			return true;
		}
		DNode thisNode = head;
		DNode otherNode = x.head;
		if (this.getSize() == x.getSize()) {
			Iterator<E> iter1 = this.iterator();
			Iterator<E> iter2 = x.iterator();
			while (iter1.hasNext()) {
				if(!(thisNode.element.equals(otherNode.element))) {
					return false;
				}
				thisNode.element = iter1.next();
				otherNode.element = iter2.next();
			} return true;
		}
		return false;
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
