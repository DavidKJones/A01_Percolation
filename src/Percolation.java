import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Percolation class
 * @author David Jones and Lisa Hammond
 * A01_Percolation
 */

public class Percolation 
{
	private boolean[][] siteIsOpen; 
	private int virtualTopSite;
	private int virtualBttmSite;
	private WeightedQuickUnionUF wQU;
	private WeightedQuickUnionUF wQU2;
	private int rLength;
	private int numOpenSites;
	
	//create N-by-N grid, with all sites blocked
	public Percolation(int N)
	{
		if (N <= 0) throw new IllegalArgumentException("Grid size " + N + " must be greater than 0");
		
		siteIsOpen = new boolean[N][N];
		//quick union where we check for percolation
		wQU = new WeightedQuickUnionUF(N*N + 2);
		//quick union where we check for isFull
		wQU2 = new WeightedQuickUnionUF(N*N + 1);
		
		virtualTopSite = N*N;
		virtualBttmSite = N*N + 1;
		rLength = N;
		numOpenSites = 0;
		
		for( int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				int id = get2Dto1D(i,j);
				siteIsOpen[i][j] = false;
				
				if(i==0)
				{
					wQU.union(virtualTopSite, id);
					wQU2.union(virtualTopSite, id);
				}
				
				if(i == rLength - 1)
				{
					wQU.union(virtualBttmSite, id);
				}
			}
		}
	}
	
	//open site (row i, column j) if it is not open already
	public void open(int i, int j)
	{
		validateSite(i,j);
		
		if(!isOpen(i,j))
		{			
			//set the site to open
			siteIsOpen[i][j] = true;
			
			//check the neighbor above
			if(validNeighbor(i-1,j))
			{
				if(siteIsOpen[i-1][j])
				{
					wQU.union( get2Dto1D(i,j), get2Dto1D(i-1,j));
					wQU2.union( get2Dto1D(i,j), get2Dto1D(i-1,j));
				}
			}
			
			//check the neighbor on the left
			if(validNeighbor(i,j-1))
			{
				if(siteIsOpen[i][j-1])
				{
					wQU.union( get2Dto1D(i,j), get2Dto1D(i,j-1));
					wQU2.union( get2Dto1D(i,j), get2Dto1D(i,j-1));
				}
			}
			
			//check the neighbor on the right
			if(validNeighbor(i,j+1))
			{
				if(siteIsOpen[i][j+1])
				{
					wQU.union( get2Dto1D(i,j), get2Dto1D(i,j+1));
					wQU2.union( get2Dto1D(i,j), get2Dto1D(i,j+1));
				}
			}
			
			//check the neighbor below
			if(validNeighbor(i+1,j))
			{
				if(siteIsOpen[i+1][j])
				{
					wQU.union( get2Dto1D(i,j), get2Dto1D(i+1,j));
					wQU2.union( get2Dto1D(i,j), get2Dto1D(i+1,j));
				}
			}
			
			numOpenSites++;
		}
	}
	
	//is site (row i, column j) open?
	public boolean isOpen(int i, int j)
	{
		validateSite(i,j);
		return siteIsOpen[i][j] == true;
	}
	
	//is site (row i, column j) full?
	public boolean isFull(int i, int j)
	{
		validateSite(i,j);
		return wQU2.connected(get2Dto1D(i,j), virtualTopSite)  && isOpen(i,j);
	}
	
	//does the system percolate?
	public boolean percolates()
	{
		return wQU.connected( virtualBttmSite, virtualTopSite);
	}
	
	//get the unique id for the site
	private int get2Dto1D(int i, int j)
	{
		int oneD = 0;
		oneD = (i * rLength) + j;
		return oneD;

	}
	
	//checks to see if the indices are valid
	private void validateSite(int i, int j) throws IndexOutOfBoundsException
	{
		if(i < 0 || i >= rLength)
		{
			throw new IndexOutOfBoundsException("I");
		}
		
		if(j < 0 || j >= rLength)
		{
			throw new IndexOutOfBoundsException("J");
		}
	}
	
	//check to see if the neighbor is within the 2d array
	private boolean validNeighbor( int i, int j)
	{
		if(i < 0 || i >= rLength || j < 0 || j>= rLength)
		{
			return false;
		}
		
		return true;
	}
	
	//Get the number of sites that are open
	public int numberOfOpenSites()
	{
		return numOpenSites;
	}
	
	//main function for testing
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
