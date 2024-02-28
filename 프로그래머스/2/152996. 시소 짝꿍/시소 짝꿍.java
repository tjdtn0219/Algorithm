import java.util.*;
class Solution {
    
    static final double[] multis = {1.0, 4.0/3.0, 1.5, 2.0};
    
    int n;
    int[] weights;
    
    public long solution(int[] weights) {
        long answer = 0;
        init(weights);
        // printWeights();
        return solve();
        // return answer;
    }
    
    public void printWeights() {
        for(int num : weights) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public long solve() {
        long cnt = 0;
        for(int i=0; i<n-1; i++) {
            int weight = weights[i];
            for(int j=0; j<4; j++) {
                double multiWeight = (double) weight * multis[j];
                // System.out.println("idx : " + i + ", target : " + multiWeight);
                int lowerIdx = lowerBound(i, multiWeight);
                int upperIdx = upperBound(i, multiWeight);
                // System.out.println("lowerIdx : " + lowerIdx + " upperIdx : " + upperIdx);
                if(lowerIdx < n && (double) weights[lowerIdx] == multiWeight) {
                    // System.out.println(weight + " " + weights[lowerIdx]);
                    cnt += upperIdx - lowerIdx;
                }
            }
        }
        return cnt;
    }
    
    public void init(int[] weights) {
        this.weights = weights.clone();
        Arrays.sort(this.weights);
        n = weights.length;
    }
    
    public int lowerBound(int idx, double tg) {
        int st = idx + 1;
        int en = n;
        
        while(st < en) {
            int mid = (st + en) / 2;
            if(tg <= (double) weights[mid]) en = mid;
            else st = mid + 1;
        }
        return st;
    }
    
    public int upperBound(int idx, double tg) {
        int st = idx + 1;
        int en = n;
        
        while(st < en) {
            int mid = (st + en) / 2;
            if(tg < (double) weights[mid]) en = mid;
            else st = mid + 1;
        }
        return st;
    }
}