
import java.util.EmptyStackException;
import java.util.Vector;

public class BadCommentedStack<E> extends Vector<E> {
	/**
	 * Creates an empty Stack.
	 */
	public BadCommentedStack() {
	}

	//precondition: true
	//postcondition: the top element in the stack is item and all the remaining elements remain unchanged. if the capacity of stack equals to the size of the stack before pushing, increase the capacity of the stack. 
	public E push(E item) {
		addElement(item);

		return item;
	}

	//precondition: true
	//postcondition: if the stack is empty, throws an exception; otherwise, remove the top element in the stack and leave the rest unchanged.
	public synchronized E pop() {
		E obj;
		int len = size();

		obj = peek();
		removeElementAt(len - 1);

		return obj;
	}

	/**
	 * Looks at the object at the top of this stack without removing it from the
	 * stack.
	 *
	 * @return the object at the top of this stack (the last item of the
	 *         <tt>Vector</tt> object).
	 * @exception EmptyStackException
	 *                if this stack is empty.
	 */
	public synchronized E peek() {
		int len = size();

		if (len == 0)
			throw new EmptyStackException();
		return elementAt(len - 1);
	}

	/**
	 * Tests if this stack is empty.
	 *
	 * @return <code>true</code> if and only if this stack contains no items;
	 *         <code>false</code> otherwise.
	 */
	public boolean empty() {
		return size() == 0;
	}

	/**
	 * Returns the 1-based position where an object is on this stack. If the
	 * object <tt>o</tt> occurs as an item in this stack, this method returns
	 * the distance from the top of the stack of the occurrence nearest the top
	 * of the stack; the topmost item on the stack is considered to be at
	 * distance <tt>1</tt>. The <tt>equals</tt> method is used to compare
	 * <tt>o</tt> to the items in this stack.
	 *
	 * @param o
	 *            the desired object.
	 * @return the 1-based position from the top of the stack where the object
	 *         is located; the return value <code>-1</code> indicates that the
	 *         object is not on the stack.
	 */
	public synchronized int search(Object o) {
		int i = lastIndexOf(o);

		if (i >= 0) {
			return size() - i;
		}
		return -1;
	}

	/* returns a string representation of the stack */
	/**** Do NOT comment like the following ****/
	/* if you change this function, then you need to change the "checkEmptyByString" function */
	public String toString() {  
	    StringBuffer buf = new StringBuffer ("{");
	    for (int i = size()-1; i >= 0; i--) {
	        if (i < (size()-1)) {
	            buf.append (", ");
	        }
	        
	        buf.append (elementAt(i).toString());
	    }
	      
	    buf.append ("}");
	    return buf.toString();
	}

	/* check empty stack string */
	/**** Do NOT comment like the following ****/
	/* if you change this function, then you need to change the "toString" function */
  public String checkEmptyByString() {
		if (this.toString().equals("{}"))
			return "empty";
		else
			return "non empty";
	}

	public static void main(String args[]) {
		BadCommentedStack stack = new BadCommentedStack();
	}
	
	private static final long serialVersionUID = 1224463164541339165L;
}
