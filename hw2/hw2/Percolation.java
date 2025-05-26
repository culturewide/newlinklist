package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int[] flatGrid;
    private int num;//total open number
    private  int N;
    private WeightedQuickUnionUF uf ;

    /**
     *Represents the top and bottom of grid
     * @param n creat a n by n grid,with all blocked
     */

    public Percolation(int n ){
        N = n;
        if(N <= 0){
            throw new IndexOutOfBoundsException();
        }
        flatGrid = new int[N*N];
        uf = new WeightedQuickUnionUF(N*N+2 );
        grid = new int[N][N];
        for(int i = 0; i <N; i++){
            for(int j = 0; j < N; j++){
                grid[i][j] = -2;
                xyTo1D(i, j);
            }
        }
        num = 0;
        for(int i =0 ; i < N ; i++){
            uf.union(i, N*N);
            uf.union((N-1)*N+i, N*N+1);
        }

    }
    // open the site (row, col) if it is not open already
    //首先将row，col转换为一个数，
    public void open(int row, int col){
        int total = xyTo1D(row, col);
        grid[row][col] = 1;
        num++;
        if(row-1 >=0 && grid[row-1][col] == 1){
            uf.union(xyTo1D(row-1,col), xyTo1D(row,col));
        }
        if(row+1 < grid.length && grid[row+1][col] == 1){
            uf.union(xyTo1D(row+1,col), xyTo1D(row,col));
        }
        if(col-1 >=0 && grid[row][col-1] == 1){
            uf.union(xyTo1D(row,col-1), xyTo1D(row,col));
        }
        if(col+1 < grid[0].length && grid[row][col+1] == 1){
            uf.union(xyTo1D(row,col+1), xyTo1D(row,col));
        }
    }
    public boolean isOpen(int row, int col){
        return grid[row][col] == 1;
    }
    public boolean isFull(int row, int col){
        if(isOpen(row,col)){
            return uf.connected(xyTo1D(row, col), N*N);
        }
        return false;
    }
    public int numberOfOpenSites(){
        return num;
    }
    public boolean percolates(){
        if(uf.connected(N*N, N*N+1)){
            return true;
        }else{
            return  false;
        }
    }
    private int xyTo1D(int row, int col){
        int rowCalculate = (row)*grid.length;
        int colCalculate = col;
        int total = rowCalculate + colCalculate;
//        flatGrid[rowCalculate + colCalculate] = grid[row][col];
        return total;
    }
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(0,0);
//        p.open(1,2);
//        p.open(0,2);
//        p.open(3,2);
//        if(p.percolates()){
//            System.out.println("yesohmygod");
//        }else System.out.println("no");
        if(p.isFull(0,2)){
            System.out.println("FUCK");
        }

    }


}
