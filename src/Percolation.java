import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation 
{
	public enum SiteState { FULL, BLOCK, OPEN };
	
	private int[][] sites;
	private SiteState[][] sitesState;
	private int rowLength = 0;
	private WeightedQuickUnionUF wQU;
	private int N;
	
	//create N-by-N grid, with all sites blocked
	public Percolation(int N)
	{
		if (N <= 0) throw new IllegalArgumentException("Grid size " + N + " must be greater than 0");
		
		sites = new int[N][N];
		sitesState = new SiteState[N][N];
		rowLength = N;
		
		for( int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				sites[i][j] = get2Dto1D(i,j);
				sitesState[i][j] = SiteState.BLOCK;
			}
		}
		
		wQU = new WeightedQuickUnionUF(N);
	}
	
	//open site (row i, column j) if it is not open already
	public void open(int i, int j)
	{
		if(!isOpen(i,j))
		{
			sitesState[i][j] = SiteState.OPEN;
			
			//check the site on the left
			if(isOpen(i - 1, j))
			{
				wQU.union(sites[i][j], sites[i-1][j]);
			}
			//check the site on the right
			if(isOpen(i + 1, j))
			{
				wQU.union(sites[i][j], sites[i+1][j]);
			}
			//check the site above
			if(isOpen(i, j - 1))
			{
				wQU.union(sites[i][j], sites[i][j-1]);
			}
			//check the site below
			if(isOpen(i, j + 1))
			{
				wQU.union(sites[i][j], sites[i-1][j+1]);
			}
		}
	}
	
	//is site (row i, column j) open?
	public boolean isOpen(int i, int j)
	{
		if (i < 0 || i >= N) throw new IndexOutOfBoundsException("row index " + i + " must be between 0 and " + (N-1));
		if (j < 0 || j >= N) throw new IndexOutOfBoundsException("row index " + j + " must be between 0 and " + (N-1));
		return sitesState[i][j] != SiteState.BLOCK;
	}
	
	//is site (row i, column j) full?
	public boolean isFull(int i, int j)
	{
		if (i < 0 || i >= N) throw new IndexOutOfBoundsException("row index " + i + " must be between 0 and " + (N-1));
		if (j < 0 || j >= N) throw new IndexOutOfBoundsException("row index " + j + " must be between 0 and " + (N-1));
		return sitesState[i][j] == SiteState.FULL;
	}
	
	//does the system percolate?
	public boolean percolates()
	{
		return false;
	}
	
	//get the unique id for the site
	private int get2Dto1D(int i, int j)
	{
		int oneD = 0;
		oneD = (i * N) + j;
		return oneD;

	}
	
	//checks to see if the indices are valid
	private void validateSite(int i, int j)
	{
		if(i < 0 || i >= rowLength)
		{
			throw new IndexOutOfBoundsException("");
		}
	}
	
	public int numberOfOpenSites()
	{
		return 0;
	}
	
	public static void main (String[] args)
	{
	}
}
