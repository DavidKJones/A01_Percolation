import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation 
{
	public enum PercolationState { FULL, BLOCK, OPEN };
	
	private PercolationState pState = PercolationState.BLOCK;
	
	//create N-by-N grid, with all sites blocked
	public Percolation(int N)
	{
		WeightedQuickUnionUF wQU = new WeightedQuickUnionUF(N);
		//wQU.union(p, q);
		//wQU.connected(p, q);
	}
	
	//open site (row i, column j) if it is not open already
	public void open(int i, int j)
	{
		
	}
	
	//is site (row i, column j) open?
	public boolean isOpen(int i, int j)
	{
		return pState == PercolationState.OPEN;
	}
	
	//is site (row i, column j) full?
	public boolean isFull(int i, int j)
	{
		return pState == PercolationState.FULL;
	}
	
	//does the system percolate?
	public boolean percolates()
	{
		return false;
	}
	
	//get the unique id for the site
	private int get2Dto1D()
	{
		return 0;
	}
}
