import java.util.*;

class Solution {
    
    int[][] targets;
    
    public int solution(int[][] targets) {
        int answer = 0;
        init(targets);
        return solve();
    }
    
    public int solve() {
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        int lastE = 0;
        int cnt = 0;
        
        for(int[] tg : targets) {
            int s = tg[0];
            int e = tg[1];
            if(lastE <= s) {
                lastE = e;
                cnt++;
            }
        }
        return cnt;
    }
    
    public void init(int[][] targets) {
        this.targets = targets;
    }
}