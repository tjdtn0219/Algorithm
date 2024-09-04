import java.util.*;

class Solution {
    public int solution(int n, int m, int[] section) {
        int ans = 0;
        
        boolean[] isPaint = new boolean[n+1];
        Arrays.fill(isPaint, true);
        
        for(int num : section) {
            isPaint[num] = false;
        }
        
        for(int num : section) {
            if(isPaint[num]) continue;
            boolean flag = false;
            for(int i=num; i<Math.min(num+m,n+1); i++) {
                if(!isPaint[i]) {
                    isPaint[i] = true;
                    flag = true;
                }
            }
            if(flag) ans++;
        }
        
        return ans;
    }
}