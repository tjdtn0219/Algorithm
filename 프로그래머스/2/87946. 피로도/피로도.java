import java.util.*;

class Solution {
    
    public int n;
    public int[] comb;
    public boolean[] vis;
    public int ans = -1;
    
    public int solution(int cur, int[][] dungeons) {
        
        n = dungeons.length;    
        comb = new int[n];
        vis = new boolean[n];
        
        btk(0, cur, dungeons, 0);
        
        return ans;
    }
    
    public void btk(int k, int cur, int[][] dungeons, int cnt) {
        if(k==n) {
            ans = Math.max(ans, cnt);
            return ;
        }
        
        for(int i=0; i<n; i++) {
            if(vis[i]) continue;
            
            comb[k] = i;
            vis[i] = true;
            if(cur < dungeons[i][0]) btk(k+1, cur, dungeons, cnt);
            else btk(k+1, cur-dungeons[i][1], dungeons, cnt+1);
            vis[i] = false;
        }
    }
}