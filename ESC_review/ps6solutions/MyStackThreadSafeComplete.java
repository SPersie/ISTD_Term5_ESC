package Week10;

public class MyStackThreadSafeComplete {
	private final int maxSize;
	private long[] stackArray;
	private int top; //invariant: top < stackArray.length && top >= -1	
	
	//pre-condition: s > 0
	//post-condition: maxSize == s && top == -1 && stackArray != null
	public MyStackThreadSafeComplete(int s) { //Do we need "synchronized" for the constructor?
		maxSize = s;
	    stackArray = new long[maxSize];
	    top = -1;
	}
	
	//pre-condition: top+1 < maxSize
	//post-condition: the top element is j; j is in the array
	public synchronized void push(long j) {
		while (isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		stackArray[++top] = j;
		notifyAll();	
	}

	//pre-condition: top >= 0
	//post-condition: the top element is removed
	public synchronized long pop() {
		long toReturn; 
		
		while (isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		toReturn = stackArray[top--];
		notifyAll();			
	    return toReturn;
	}
	
	//pre-condition: top >= 0
	//post-condition: the elements are un-changed.
	public synchronized long peek() {
	    return stackArray[top];
	}

	//pre-condition: true
	//post-condition: the elements are un-changed. the return value is true iff the stack is empty.
	public synchronized boolean isEmpty() {
		return (top == -1);
	}
	
	//pre-condition: true
	//post-condition: the elements are un-changed. the return value is true iff the stack is full.
	public synchronized boolean isFull() {
		return (top == maxSize - 1);
	}
}