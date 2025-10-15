import java.util.*;

class Solution {
    
    int answer;
    int n;
    List<List<Integer>> graph;
    int[][] dp;
    boolean[] vis;
    //dp[i][0]: i번째 등대가 꺼져있는 경우 불 켜진 최소 등대 수
    //dp[i][1]: i번째 등대가 켜져있는 경우 불 켜진 최소 등대 수
    
    public int solution(int n, int[][] lighthouse) {
        init(n, lighthouse);
        solve();
        return answer;
    }
    
    public void solve() {
        int[] res = dfs(1);
        answer = Math.min(res[0], res[1]);
    }
    
    public int[] dfs(int cur) {
        vis[cur] = true;
        dp[cur][0] = 0;
        dp[cur][1] = 1;
        
        int max = Integer.MAX_VALUE;
        for(int nxt : graph.get(cur)) {
            if(vis[nxt]) continue;
            int[] tmp = dfs(nxt);
            dp[cur][0] += tmp[1];
            dp[cur][1] += Math.min(tmp[0], tmp[1]);
        }
        
        return dp[cur];
    }
    
    public void init(int n, int[][] edges) {
        this.n = n;
        this.dp = new int[n+1][2];
        for(int i=0; i<=n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        this.vis = new boolean[n+1];
        this.graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
    }
}