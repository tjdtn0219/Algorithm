import java.util.*;

class Solution {
    
    long answer;
    int w, h;
    
    public long solution(int w, int h) {

        init(w, h);
        solve();
        return answer;
    }
    
    public void solve() {
        
        for(int i=1; i<w; i++) {
            double dh = (double) h * i / w;
            answer += (long) dh;
        }
        
        answer *= 2L;
    }
    
    public void init(int w, int h) {
        this.w = w;
        this.h = h;
    }
    
}