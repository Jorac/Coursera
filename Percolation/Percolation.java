
public class Percolation {
    int lattice[][];
    int openSites;
    int num;
    UF quickUnion;
    
    /**
     * convert row and column indexes to 1-dimensional union find arr
     */
    private int xyTo1D(int x, int y){
        return 1+x*num+y;
    }
    
    /**
     * check if indexes is out of bound then throw.
     */
    private void validateIdx(int x, int y){
       if (x <= 0 || x > num ) throw new IndexOutOfBoundsException("row index out of bounds");
       if (y <= 0 || y > num ) throw new IndexOutOfBoundsException("col index out of bounds");
    }
    /**
     * create n-by-n grid, with all sites blocked
     */
    public Percolation(int n)
   {  
       if(n<=0)
           throw (new java.lang.IllegalArgumentException());
       quickUnion = new UF(n*n+2);   // create UF for all sites + 2 nodes at top 
                                                          //& bot
       lattice = new int[n][n];
       for(int i = 0; i<n; i++){
           for(int j = 0; j<n; j++){
               lattice[i][j] = 0;
           }
       }
       num = n;
       openSites = 0;
   }
    
   /**
     * open site (row, col) if it is not open already
     */
   public void open(int row, int col)      
   {
       validateIdx(row, col);
       if(isOpen(row, col))
           return;
       int i = row-1;
       int j = col-1;
       lattice[i][j] = 1;
       if(i >0){
           if(isOpen(i, col)){
               quickUnion.union(xyTo1D(i,j), xyTo1D(i-1,j));
           }
       }
       else if(i == 0){
           //connect top layer of sites with top of UF
           quickUnion.union(0, j+1);
       }
       if(j>0){
           if(isOpen(row, j)){
               quickUnion.union(xyTo1D(i,j), xyTo1D(i,j-1));
           }
       }
       if(i<num-1){
           if(isOpen(row+1, col)){
               quickUnion.union(xyTo1D(i,j), xyTo1D(i+1,j));
           }
       }
       else if(i == num-1){
           // and bottom layer to bottom of UF
           quickUnion.union(num*num+1, xyTo1D(i,j));
       }
       if(j<num-1){
           if(isOpen(row, col+1)){
               quickUnion.union(xyTo1D(i,j), xyTo1D(i,j+1));
           }
       }
       openSites++;
   }
   
   public int getOpenSites(){
       return openSites;
   }
   
   /**
    * is site (row, col) open?
    */
   public boolean isOpen(int row, int col)
   {
       validateIdx(row, col);
       return (lattice[row-1][col-1]>0)?true:false;
   }
   
   /**
    * is site (row, col) full?
    */
   public boolean isFull(int row, int col)
   {
       validateIdx(row, col);
       return quickUnion.connected(0, xyTo1D(row-1, col-1));
   }
   
   /**
    * does the system percolate?
    */
   public boolean percolates(){
       return quickUnion.connected(0, num*num+1);
   }
   
      
   /**
    * test client (optional)
    */
   public static void main(String[] args){
       int n = 20;
       Percolation p = new Percolation(n);
       while(!p.percolates()){
//           int x = StdRandom.uniform(1,n+1);
//           int y = StdRandom.uniform(1,n+1);
//           p.open(x,y);
       }
//       p.print();
//       StdOut.printf("OpenSites = %d, n*n = %d Fraction = %f\n",p.getOpenSites(), n*n, ((double)(p.getOpenSites())/(n*n)));
   }
}
