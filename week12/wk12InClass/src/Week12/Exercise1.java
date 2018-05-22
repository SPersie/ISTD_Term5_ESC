package Week12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * Apply SPMD (Single Program, Multiple Data) design pattern for concurrent programming to parallelize the program which 
 * approximates $\pi$ by integrating the following formula $4/(1+x^2 )$. Hint: In the SPMD design pattern, all threads 
 * run the same program, operating on different data.
 */
public class Exercise1 {
    public static double result =0;
	public static void main(String[] args) throws Exception {
		int NTHREADS = 5;
		ExecutorService exec = Executors.newFixedThreadPool(NTHREADS - 1);
		// todo: complete the program by writing your code below.
		Runnable task1 =new Runnable() {
            @Override
            public void run() {
                integrate(0, 0.25);
            }
        };

		Runnable task2 =new Runnable() {
            @Override
            public void run() {
                integrate(0.25, 0.5);
            }
        };

		Runnable task3 =new Runnable() {
            @Override
            public void run() {
                integrate(0.5, 0.75);
            }
        };

		Runnable task4 =new Runnable() {
            @Override
            public void run() {
                integrate(0.75, 1);
            }
        };

		exec.execute(task1);
		exec.execute(task2);
		exec.execute(task3);
		exec.execute(task4);

		//shutdown will not terminate the ThreadPool immediately. It will terminate util all the tasks finish.
		exec.shutdown();
		exec.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println(result);

    }

	public static double f(double x) {
		return 4.0 / (1 + x * x);
	}

	// the following does numerical integration using Trapezoidal rule.
	public static double integrate(double a, double b) {
		int N = 10000; // preciseness parameter
		double h = (b - a) / (N - 1); // step size
		double sum = 1.0 / 2.0 * (f(a) + f(b)); // 1/2 terms

		for (int i = 1; i < N - 1; i++) {
			double x = a + h * i;
			sum += f(x);
		}
		result +=sum *h;

		return sum * h;
	}
}
