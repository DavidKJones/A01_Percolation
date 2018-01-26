import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

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
		
		Percolation percGrid = new Percolation(N);
		
		int testCount = 0;
		int openCount = 0;
		while(testCount <= T)
		{
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
			testCount++;
			double percThreshold = openCount / Math.pow(N, 2);
			percThresholds[testCount] = percThreshold;
		}
		
	}
	
	
	//sample mean of percolation threshold
	public double mean()
	{
		double percMean = StdStats.mean(percThresholds);
		return percMean;
	}
	
//	public static double mean(double[] a) {
//        validateNotNull(a);
//
//        if (a.length == 0) return Double.NaN;
//        double sum = sum(a);
//        return sum / a.length;
//    }
	
	//sample standard deviation of percolation threshold
	public double stddev()
	{
		double stddev = StdStats.stddev(percThresholds);
		return stddev;
	}
	
//	 public static double stddev(double[] a) {
//	        validateNotNull(a);
//	        return Math.sqrt(var(a));
//	    }

	
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
}
