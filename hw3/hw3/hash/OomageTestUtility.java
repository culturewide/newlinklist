package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class  OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] bucket = new int[M];
        int length = oomages.size();
        for(Oomage oomage : oomages) {
            int bucketNum = (oomage.hashCode() & 0x7FFFFFFF) % M;
            bucket[bucketNum]++;

        }
        for(int i = 0; i < M; i++) {
         if(bucket[i] > length / 2.5) {
             return false;
         }else{
             if(bucket[i] < length / 50){
                 return false;
             }
         }
        }
        return true;

        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */




    }
}
