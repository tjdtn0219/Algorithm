import java.io.*;
import java.util.*;

public class Main {

    int n;
    List<List<Integer>> graph;
    boolean[] vis;
    int[][] dp;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
        
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
            for(int i=0; i<n-1; i++) {
                String[] u_v = br.readLine().split(" ");
                int u = Integer.parseInt(u_v[0]);
                int v = Integer.parseInt(u_v[1]);
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            vis = new boolean[n+1];
            dp = new int[n+1][2];
            // dp[n][0] : n번째 노드가 얼리어댑터가 아닌 경우의 최소 얼리어댑터 개수
            // dp[n][1] : n번째 노드가 얼리어댑터인 경우의 최소 얼리어댑터 개수
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public void dfs(int cur) {
        vis[cur] = true;
        dp[cur][0] = 0;   //해당 cur이 얼리어댑터가 아닌 경우
        dp[cur][1] = 1;   //해당 cur이 얼리어댑터인 경우

        for(int child : graph.get(cur)) {
            if(vis[child]) continue;
            dfs(child);
            dp[cur][0] += dp[child][1];
            dp[cur][1] += Math.min(dp[child][0], dp[child][1]);
        }

    }
}