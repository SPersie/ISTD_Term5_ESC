package Week12;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack <E> {
	AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

	public void push(E item) {
		Node<E> newHead = new Node<E>(item);
		Node<E> oldHead;
		do {
			oldHead = top.get();
			newHead.next = oldHead;
		} while (!top.compareAndSet(oldHead, newHead)); //commitment here
	}

	public E pop() {
		Node<E> oldHead;
		Node<E> newHead;
		do {
			oldHead = top.get();
			if (oldHead == null)
				return null;
			newHead = oldHead.next;
		} while (!top.compareAndSet(oldHead, newHead)); //commitment here
		return oldHead.item;
	}

	private static class Node <E> {
		public final E item;
		public Node<E> next;

		public Node(E item) {
			this.item = item;
		}
	}
}
