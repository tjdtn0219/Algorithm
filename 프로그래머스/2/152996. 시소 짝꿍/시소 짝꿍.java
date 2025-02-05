import java.util.*;

class Solution {
    
    int n;
    int[] weights;
    HashMap<Double, Integer> cntMap;
    long ans;
    
    public long solution(int[] weights) {
        init(n, weights);
        solve();
        return ans;
    }
    
    public void solve() {
        Arrays.sort(weights);
        //100 100 150 150 180 270 360
        int last = -1;
        for(int w : weights) {
            double w1 = w * 1.0;
            double w2 = w * 1.5;
            double w3 = w * (4.0/3.0);
            double w4 = w * (2.0);
            // System.out.println(w2 + ", " + w3 + ", " + w4);
            if(w == last) {
                ans += cntMap.getOrDefault(w2, 0);
                ans += cntMap.getOrDefault(w3, 0);
                ans += cntMap.getOrDefault(w4, 0);
            } else {
                long sameCnt = cntMap.getOrDefault(w1, 0);
                if(sameCnt > 1) {
                    ans += sameCnt * (sameCnt-1) / 2;
                }
                ans += cntMap.getOrDefault(w2, 0);
                ans += cntMap.getOrDefault(w3, 0);
                ans += cntMap.getOrDefault(w4, 0);
            }
            // System.out.println("w : " + w + ", ans : " + ans + "\n");
            last = w;
        }
    }
    
    public void init(int n, int[] weights) {
        this.n = weights.length;
        this.weights = weights;
        this.cntMap = new HashMap<>();
        for(int w : weights) {
            double dw = (double) w;
            cntMap.put(dw, cntMap.getOrDefault(dw, 0) + 1);
        }
    }
}