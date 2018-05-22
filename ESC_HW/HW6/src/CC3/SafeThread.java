package CC3;

import java.util.Stack;

public class SafeThread<E> {
    private final Stack<E> stack;

    public SafeThread() {
        this.stack = new Stack<>();
    }

    public synchronized E push(E item) {
        return stack.push(item);
    }

    public synchronized E pop() {
        return stack.pop();
    }

    public synchronized E peek() {
        return stack.peek();
    }

    public synchronized boolean empty() {
        return stack.empty();
    }

    public synchronized int search(Object o) {
        return stack.search(o);
    }
}
