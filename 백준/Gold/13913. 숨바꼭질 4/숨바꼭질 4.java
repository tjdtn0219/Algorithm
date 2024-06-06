import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 100_000;
    static final int[] DX = {-1, 1};

    int[] dp;
    int[] pre;
    int a;
    int b;

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
            String[] tmp = br.readLine().split(" ");
            a = Integer.parseInt(tmp[0]);
            b = Integer.parseInt(tmp[1]);
            dp = new int[MAX + 5];
            pre = new int[MAX + 5];
            Arrays.fill(dp, Integer.MAX_VALUE);
            Arrays.fill(pre, -1);
            dp[a] = 0;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int cnt = bfs(a);
        System.out.println(cnt);
        printPre(b);
//        for(int i=5; i<=b; i++) {
//            System.out.print(pre[i] + " ");
//        }
//        System.out.println();
    }

    public void printPre(int x) {
        List<Integer> list = new ArrayList<>();
        while(x > -1) {
            list.add(x);
            x = pre[x];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=list.size()-1; i>=0; i--) {
            sb.append(list.get(i) + " ");
        }
        System.out.println(sb);
    }

    public int bfs(int st) {
        Queue<Integer> q = new LinkedList<>();
        q.add(st);
        int cnt = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int x = q.poll();
//                System.out.println("x : " + x);
                if(x == b) {
                    return cnt;
                }
                for (int dir = 0; dir < 2; dir++) {
                    int nx = x + DX[dir];
//                    System.out.println("nx : " + nx);
//                    System.out.println("dp[nx] : " + dp[nx] + ", dp[x] + 1" + (dp[x] + 1));
                    if (isInner(nx) && dp[nx] > dp[x] + 1) {
                        q.add(nx);
                        dp[nx] = dp[x] + 1;
                        pre[nx] = x;
                    }
                }
                int nx = 2 * x;
                if (isInner(nx) && dp[nx] > dp[x] + 1) {
                    q.add(nx);
                    dp[nx] = dp[x] + 1;
                    pre[nx] = x;
                }
            }
            cnt++;
        }
        return -1;
    }

    public boolean isInner(int x) {
        return 0<=x && x<=MAX;
    }
}