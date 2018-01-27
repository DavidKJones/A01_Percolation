
import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
	
	int N;
	int T;
	double[] percThresholds;
	
	
	//perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T)
	{
		if (N <= 0) throw new IllegalArgumentException("Grid size " + N + " must be greater than 0");
		if (T <= 0) throw new IllegalArgumentException("Number of experiments " + T + " must be greater than 0");
		
		this.N = N;
		this.T = T;
				
		percThresholds = new double[T];
		
		int testCount = 0;
		
		while(testCount < T)
		{
			Percolation percGrid = new Percolation(N);
			int openCount = 0;
			
			while(percGrid.percolates() == false)
			{
				int i = StdRandom.uniform(N);
				int j = StdRandom.uniform(N);
												
				if (percGrid.isOpen(i, j) == false)
				{
					percGrid.open(i, j);
					openCount++;
				} 
			}
						
			double percThreshold = openCount / Math.pow(N, 2);  //percolation threshold for 1 test
			percThresholds[testCount] = percThreshold;  //thresholds for all tests up to this point
			testCount++;
		}
//		mean();
//		stddev();
//		confidenceLow();
//		confidenceHigh();
		
	}
		
	//sample mean of percolation threshold
	public double mean()
	{
		double percMean = StdStats.mean(percThresholds);
		return percMean;
	}
	
	//sample standard deviation of percolation threshold
	public double stddev()
	{
		double stddev = StdStats.stddev(percThresholds);
		return stddev;
	}
		
	//low endpoint of 95% confidence interval
	public double confidenceLow()
	{
		double confidenceLow = mean() - (1.96 * stddev()) / (Math.sqrt(T));
		return confidenceLow;
	}
	
	//high endpoint of 95% confidence interval
	public double confidenceHigh()
	{
		double confidenceHigh = mean() + (1.96 * stddev()) / (Math.sqrt(T));
		return confidenceHigh;
	}
	
	public static void main(String[] args)
	{
		PercolationStats stats = new PercolationStats(200, 100);
		System.out.println("\nPercolationStats (200, 100)");
		System.out.println("mean() = " + stats.mean());
		System.out.println("stddev() = " + stats.stddev());
		System.out.println("confidenceLow() = " + stats.confidenceLow());
		System.out.println("confidenceHigh() = " + stats.confidenceHigh());

		stats = new PercolationStats(200, 100);
		System.out.println("\nPercolationStats (200, 100)");
		System.out.println("mean() = " + stats.mean());
		System.out.println("stddev() = " + stats.stddev());
		System.out.println("confidenceLow() = " + stats.confidenceLow());
		System.out.println("confidenceHigh() = " + stats.confidenceHigh());
		
		stats = new PercolationStats(2, 100000);
		System.out.println("\nPercolationStats (2, 100000)");
		System.out.println("mean() = " + stats.mean());
		System.out.println("stddev() = " + stats.stddev());
		System.out.println("confidenceLow() = " + stats.confidenceLow());
		System.out.println("confidenceHigh() = " + stats.confidenceHigh());
		
		}
	
	}

