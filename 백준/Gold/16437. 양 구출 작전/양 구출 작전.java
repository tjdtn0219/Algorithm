import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static List<List<Integer>> graph;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    public static void solve() {
        dfs(1, -1);
        System.out.println(dp[1]);
    }

    static void dfs(int idx, int pa) {
        for(int nxt : graph.get(idx)) {
            dfs(nxt, idx);
        }

        if(pa != -1) {
            if(dp[idx] > 0) {
                dp[pa] += dp[idx];
            }
        }
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) {
                graph.add(new ArrayList<>());
            }
            dp = new long[n+1];

            for(int i=2; i<=n; i++) {
                String[] tmp = br.readLine().split(" ");
                char c = tmp[0].charAt(0);
                int a = Integer.parseInt(tmp[1]);
                int p = Integer.parseInt(tmp[2]);

                graph.get(p).add(i);
                if(c == 'W') {
                    a *= -1;
                }
                dp[i] = a;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}
