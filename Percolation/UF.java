import edu.princeton.cs.algs4.StdOut;
import java.lang.Math;
/**
 * Auto Generated Java Class.
 */
public class UF {
    int id[];
    int sz[];
    static int count;
    public UF(int n) { 
        id = new int[n];
        sz = new int[n];
        for(int i = 0; i < n; i++){
            id[i] = i;
        }
    }
    
    /**
     * find root of component
     */
    int root(int i){
        while(id[i] != i){
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    
    /**
     * add connection berween p and q
     */
    void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if(i == j) return; 
        if(sz[i]<sz[j]){
            id[i] = j; 
            sz[j]+=sz[i]; 
        }
        else{
            id[j] = i; 
            sz[i]+=sz[j]; 
        }
        //print();
    }
    
    /**
     * are p and q in the same component?
     */
    boolean connected(int p, int q){
        return root(p) == root(q);
    }
    
    /**
     * componenet id for p
     */
    int find(int p){
        return id[p];
    }
    
    /**
     * number of components
     */
    int count(){
        return count;
    }
    
    void print(){
        for(int i = 0; i<id.length; i++){
            StdOut.printf("%4d ", id[i]);
            if(i%(Math.sqrt(id.length-2)) == 0)
                   StdOut.println();
        }
        StdOut.println();
    }
}
