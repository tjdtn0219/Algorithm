import java.util.*;

class Solution {
    
    int n;
    List<List<Integer>> graph;
    int ans;
    int[][] dp;
    // dp[n][0] : n번째 등대가 불이 꺼져있는 경우 최소 불 켜진 등대 개수
    // dp[n][1] : n번째 등대가 불이 켜져있는 경우 최소 불 켜진 등대 개수
    
    public int solution(int n, int[][] lighthouse) {
        init(n, lighthouse);
        solve();
        return ans;
    }
    
    public void solve() {
        dfs(1, new boolean[n+1]);
        ans = Math.min(dp[1][0], dp[1][1]);
    }
    
    public void dfs(int cur, boolean[] vis) {
        vis[cur] = true;
        dp[cur][0] = 0; //cur 번째 등대가 불이 꺼진 경우
        dp[cur][1] = 1; //cur 번째 등대가 불이 켜진 경우
        
        int max = Integer.MAX_VALUE;
        for(int nxt : graph.get(cur)) {
            if(vis[nxt]) continue;
            dfs(nxt, vis);
            dp[cur][0] += dp[nxt][1];
            dp[cur][1] += Math.min(dp[nxt][0], dp[nxt][1]);
        }
    }
    
    public void init(int n, int[][] lighthouse) {
        this.n = n;
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : lighthouse) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        this.dp = new int[n+1][2];
        for(int i=0; i<=n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
    }
}