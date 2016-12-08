import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    int lattice[][];
    int openSites;
    int num;
    UF quickUnion;
    
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
       if(isOpen(row, col))
           return;
       int i = row-1;
       int j = col-1;
       lattice[i][j] = 1;
       if(i >0){
           if(isOpen(i, col)){
               quickUnion.union(i*num+j+1,(i-1)*num +j+1);
           }
       }
       else if(i == 0){
           //connect top layer of sites with top of UF
           quickUnion.union(0, j);
       }
       if(j>0){
           if(isOpen(row, j)){
               quickUnion.union(i*num+j+1,i*num +(j-1)+1);
           }
       }
       if(i<num-1){
           if(isOpen(row+1, col)){
               quickUnion.union(i*num+j+1,(i+1)*num +j+1);
           }
       }
       else if(i == num-1){
           // and bottom layer to bottom of UF
           quickUnion.union(num*num+1, num*(num-1)+j+1);
       }
       if(j<num-1){
           if(isOpen(row, col+1)){
               quickUnion.union(i*num+j+1,i*num +(j+1)+1);
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
       return (lattice[row-1][col-1]>0)?true:false;
   }
   
   /**
    * is site (row, col) full?
    */
   public boolean isFull(int row, int col)
   {
       return (openSites<num)?true:false;
   }
   
   public void print()
   {
       for(int i = 0; i<num; i++){
           for(int j = 0; j<num; j++){
               if(lattice[i][j] == 1)
                   StdOut.printf("o ");
               else
                   StdOut.printf("# ");
           }
           StdOut.println();
       }
   }
   
   /**
    * does the system percolate?
    */
   public boolean percolates(){
       return quickUnion.connected(0, num*num+1);
   }
   
   UF get(){
       return quickUnion;
   }
      
   /**
    * test client (optional)
    */
   public static void main(String[] args)   
   {
       int n = 20;
       Percolation p = new Percolation(n);
       UF t = p.get();
       while(!p.percolates()){
           int x = StdRandom.uniform(1,n+1);
           int y = StdRandom.uniform(1,n+1);
           p.open(x,y);
       }
       t.print();
       p.print();
       StdOut.printf("OpenSites = %d, n*n = %d Fraction = %f\n",p.getOpenSites(), n*n, ((double)(p.getOpenSites())/(n*n)));
   }
}
