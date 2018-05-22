package Week9;

import java.util.ArrayList;
import java.util.List;

public class CachedFactorizerThreadSafe {
	private int lastNumber;
	private List<Integer> lastFactors; //the last factors must be the factors of the last number
	private long count;
	private long hits; //the hits must be the number of hits among all invocation of the service.
	
	public synchronized double getCacheHitRatio () {
		return (double) hits/ (double) count;
	}
	
	public List<Integer> service (int input) {
		List<Integer> factors = null;
		
		synchronized (this) {
			++count; // can I use a separate synchronized block for ++count only? It depends.
		
			if (input == lastNumber) {
				++hits;
				factors = new ArrayList<Integer>(lastFactors);
			}
		}
		
		if (factors == null) {
			factors = factor(input);
			synchronized (this) {
				lastNumber = input;
				lastFactors = new ArrayList<Integer>(factors);
			}
		}
		
		return factors;
	}
	
	public List<Integer> factor(int n) {		
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
		        factors.add(i);
		        n /= i;
		    }
		}
		
		return factors;
	}
}