import java.util.*;

class Solution {
    
    static final double[] MULTIS = {1.0, 1.5, 2.0, 4.0/3.0};
    
    long answer;
    int[] weights;
    
    public long solution(int[] weights) {
        init(weights);
        solve();
        return answer;
    }
    
    public void solve() {
        // 10 000 000 000
        HashMap<Double, Integer> cntMap = new HashMap<>();
        for(int w : weights) {
            cntMap.put((double) w, cntMap.getOrDefault((double) w, 0) + 1);
        }
        for(double w : cntMap.keySet()) {
            for(double multi : MULTIS) {
                if(multi == 1.0) {
                    long cnt = cntMap.get(w);
                    answer += cnt * (cnt-1) / 2;
                } else {
                    double mw = multi * w;
                    long cnt1 = cntMap.get(w);
                    long cnt2 = cntMap.getOrDefault(mw, 0);
                    answer += cnt1 * cnt2;
                }
            }
        }
    }
    
    public void init(int[] weights) {
        this.weights = weights;   
    }
}