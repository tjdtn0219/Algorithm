import java.util.*;

class Solution {
    
    int answer;
    int[][] targets;
    
    public int solution(int[][] targets) {
        
        init(targets);
        solve();
        return answer;
    }
    
    public void solve() {
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        
        int lastE = -1;
        int cnt = 0;
        
        for(int[] tg : targets) {
            int s = tg[0];
            int e = tg[1];
            if(lastE <= s) {
                lastE = e;
                cnt++;
            }
        }
        answer = cnt;
    }
    
    public void init(int[][] targets) {
        this.targets = targets;
    }
}