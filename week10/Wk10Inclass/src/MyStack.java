public class MyStack {
	//use final as long as you can
	private final int maxSize;

	//final long[] means the point does not change, not the elements cannot change
	private long[] stackArray;
	private int top; 
	
	public MyStack(int s) { 
		maxSize = s;
	    stackArray = new long[maxSize];
	    top = -1;
	}

	//pre-condition: top <maxSize
    //post-condition: an element is added to the array
	public synchronized void push(long j) {
	    while (isFull()) {
	        try {
	            wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

		stackArray[++top] = j;
	    notifyAll();
	}

    //pre-condition: top >= 0
    //post-condition: the top element is removed
	public synchronized long pop() {
	    while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        notifyAll();
        return stackArray[top--];
	}

	//pre-condition: top >=0
    //post-condition: return the top value
	public synchronized long peek() {
	    while (isEmpty()) {
	        try {
	            wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        notifyAll();
	    return stackArray[top];
    }

    //pre-condition: true
    //post-condition: the elements are un-changed. the return value is true iff the stack is empty.
	public boolean isEmpty() {
		return (top == -1);
	}

	//pre-condition: true
    //post-condition: the elements are un-changed. the return value is true iff the stack is full.
	public boolean isFull() {
		return (top == maxSize - 1);
	}

}