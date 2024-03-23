import java.util.*;

class Solution {
    
    int[][] targets;
    
    public int solution(int[][] targets) {
        init(targets);
        return solve();
    }
    
    public void init(int[][] targets) {
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        this.targets = targets;
    }
    
    public int solve() {
        int preE = -1;
        int cnt = 0;
        
        for(int[] tg : targets) {
            if(preE <= tg[0]) {
                cnt++;
                preE = tg[1];
            }
        }
        return cnt;
    }
}