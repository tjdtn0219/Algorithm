import java.io.*;
import java.util.*;

public class Main {

    int T, n, m;
    int[] coins;
    int tg;
    int[] dp;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
    }

    public void printDp() {
        for(int i=1; i<=tg; i++) {
            System.out.println("i : " + i + " , dp[i] : " + dp[i]);
        }
        System.out.println("==================");
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            T = Integer.parseInt(br.readLine());
            for(int t=0; t<T; t++) {
                n = Integer.parseInt(br.readLine());
                coins = new int[n];
                String[] tmp = br.readLine().split(" ");
                for (int i = 0; i < n; i++) {
                    coins[i] = Integer.parseInt(tmp[i]);
                }
                tg = Integer.parseInt(br.readLine());
                dp = new int[tg + 1];
                solve();
//                printDp();
                System.out.println(dp[tg]);
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        dpSolve();
    }

    public void dpSolve() {
        for(int coin : coins) {
            dp[coin]++;
            for (int i = coin; i <= tg; i++) {
                dp[i] += dp[i-coin];
            }
//            printDp();
        }
    }

    public void bfs() {
        Queue<Integer> q = new LinkedList<>();
        for(int coin : coins) {
            dp[coin]++;
            q.add(coin);
        }
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                int cur = q.poll();
                for(int coin : coins) {
                    int nxt = cur + coin;
                    if(nxt > tg) continue;
                    dp[nxt] += dp[cur];
                    q.add(nxt);
                }
            }
        }
    }

}