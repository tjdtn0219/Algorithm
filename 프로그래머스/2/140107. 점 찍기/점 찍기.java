import java.util.*;

class Solution {
    
    long ans;
    int k, d;
    
    public long solution(int k, int d) {
        init(k, d);
        solve();
        return ans;
    }
    
    public void solve() {
        for(int y=0; y<=d; y+=k) {
            int maxX = (int) Math.sqrt(Math.pow(d, 2) - Math.pow(y, 2));
            // System.out.println("maxX : " + maxX + ", y : " + y);
            long cnt = maxX / k + 1;
            // System.out.println("cnt : " + cnt);
            ans += cnt;
        }
    }
    
    public void init(int k, int d) {
        this.k = k;
        this.d = d;
    }
}