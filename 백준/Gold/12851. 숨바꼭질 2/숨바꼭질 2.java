import java.io.*;
import java.util.*;

public class Main{

    static final int INF = 200_000;

    int n, k;
    int[] dp;
    int[] cntArr;
    boolean[] vis;
    int cnt;

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
            String[] N_K = br.readLine().split(" ");
            n = Integer.parseInt(N_K[0]);
            k = Integer.parseInt(N_K[1]);
            dp = new int[INF+1];
            cntArr = new int[INF+1];
            Arrays.fill(dp, INF);
            vis = new boolean[INF + 1];
            cnt = 0;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int min = bfs();
        System.out.println(min);
        System.out.println(cnt);
    }

    public int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        dp[n] = 0;
        int time = 0;
        int min = INF;

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                int cur = q.poll();
                vis[cur] = true;

                if(cur == k && time <= min) {
                    cnt++;
                    min = Math.min(min, time);
                    continue;
                }

                if(isInner(2*cur) && !vis[2*cur]) q.add(2*cur);
                if(isInner(cur-1) && !vis[cur-1]) q.add(cur-1);
                if(isInner(cur+1) && !vis[cur+1]) q.add(cur+1);
            }
            time++;
        }

        return min;
    }

    public boolean isInner(int x) {
        return 0<=x && x<=INF;
    }

}