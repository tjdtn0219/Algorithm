import java.util.*;

class Solution {
    
    int k;
    int n;
    int[][] dungeons;
    boolean[] vis;
    int ans;
    
    public int solution(int k, int[][] dungeons) {
        init(k, dungeons);
        solve();
        return ans;
    }
    
    public void solve() {
        dfs(k, 0);
    }
    
    public void dfs(int curK, int dep) {
        ans = Math.max(ans, dep);
        if(dep == n) {
            return ;
        }
        
        for(int i=0; i<n; i++) {
            if(vis[i]) continue;
            if(dungeons[i][0] <= curK) {
                vis[i] = true;
                dfs(curK - dungeons[i][1], dep+1);
                vis[i] = false;
            }
        }
    }
    
    public void init(int k, int[][] dungeons) {
        this.k = k;
        this.n = dungeons.length;
        this.dungeons = dungeons;
        this.vis = new boolean[n];
    }
}