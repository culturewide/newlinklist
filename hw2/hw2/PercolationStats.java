package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
        private Percolation pl;
        private int n;
        private double o;
//        private int t;
        private int openSite;
        private int trailTime;
        private double u;
        private double t;
        private double LowEndPoint;
        private double HighEndPoint;
        private PercolationFactory PF;
        private double[] x;
        public PercolationStats(int N, int T,PercolationFactory pf) {
            if (N <= 0 || T <= 0) {
                throw new IllegalArgumentException("N and T must be positive");
            }
            PF = pf;
            x = new double[T];
//         出问题了   pl = pf.make(N);
            pl = pf.make(N);
            openSite = 0;
            n = N;
            t = T;
            trailTime = 0;
            int i = 0;
            while(i < T){
                randomOpen(pl);
                i++;
            }
            u = mean();
            o = stddev();
            LowEndPoint = confidenceLow();
            HighEndPoint = confidenceHigh();
        }
        private boolean checkIfPercolate(Percolation pl){
            if(pl.percolates()){
                return true;
            }
            return false;
        }
       /* private void randomOpen(Percolation pl) {
               int  randomRow = StdRandom.uniform(0,n);
               int  randomCol = StdRandom.uniform(0,n);
               if(!pl.isOpen(randomRow,randomCol)){
                       pl.open(randomRow,randomCol);
                       openSite++;
               }else{
                       randomOpen(pl);
               }
               if(checkIfPercolate(pl)){
                    x[trailTime] = (double) openSite/(n*n) ;
                    trailTime++;
                    resetPercolation();
               }else{
                   randomOpen(pl);
               }

        }*/
       private void randomOpen(Percolation pl) {
//           pl = PF.make(n);
           while (!checkIfPercolate(pl)) {  // 直到系统渗透
               int randomRow = StdRandom.uniform(0, n);
               int randomCol = StdRandom.uniform(0, n);

               if (!pl.isOpen(randomRow, randomCol)) {
                   pl.open(randomRow, randomCol);
                   openSite++;
               }
               // 如果格子已打开，继续循环，尝试下一个随机格子
           }

           // 记录结果并重置
           x[trailTime] = (double) openSite / (n * n);
           trailTime++;
           resetPercolation();
       }
        private void resetPercolation() {
           if(trailTime<t){
               openSite = 0;
               pl = PF.make(n);
           }

        }
        //calculate the mean value of percolate
        public double mean() {
            double sum = 0;
            double meanNum = 0;
            for(int i=0;i < trailTime;i++){
                sum += x[i];
            }
            meanNum = (double) sum / trailTime;
            return meanNum;
        }
        public double stddev() {
            double sum = 0;
            for(int i=0;i < trailTime;i++){
                sum += Math.pow((x[i]-u),2);
            }
            return Math.sqrt(sum / (trailTime - 1));
        }
        public double confidenceLow() {
            return u-(1.96 * o) / Math.sqrt(trailTime);
        }
        public double confidenceHigh() {
            return u+(1.96 * o) / Math.sqrt(trailTime);
        }
        public static void  main(String[] args) {
            PercolationFactory pf = new PercolationFactory();
            PercolationStats ps = new PercolationStats(20,10,pf);
            System.out.println(ps.LowEndPoint);
            System.out.println(ps.HighEndPoint);
        }
}
