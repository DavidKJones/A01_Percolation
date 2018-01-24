import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation 
{
	public enum SiteState { FULL, BLOCK, OPEN };
	
	private int[][] sites;
	private SiteState[][] sitesState;
	private int rowLength = 0;
	
	//create N-by-N grid, with all sites blocked
	public Percolation(int N)
	{
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
		
		//WeightedQuickUnionUF wQU = new WeightedQuickUnionUF(N);
		//QuickFindUF qf = new QuickFindUF(N);
		//wQU.union(p, q);
		//wQU.connected(p, q);
	}
	
	//open site (row i, column j) if it is not open already
	public void open(int i, int j)
	{
		if(sitesState[i][j] == SiteState.BLOCK)
		{
			//check neighbors if they are open
		}
	}
	
	//is site (row i, column j) open?
	public boolean isOpen(int i, int j)
	{
		return sitesState[i][j] != SiteState.BLOCK;
	}
	
	//is site (row i, column j) full?
	public boolean isFull(int i, int j)
	{
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
		return 0;
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
}
