import java.io.*;
import java.util.*;

public class Main {

    int n, m;
    List<List<Integer>> out;
    List<List<Integer>> in;
    int[] depths;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
//        printIn(1);
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = br.readLine().split(" ");
            out = new ArrayList<>();
            in = new ArrayList<>();
            n = Integer.parseInt(tmp[0]);
            m = Integer.parseInt(tmp[1]);
            for (int i = 0; i <= n; i++) {
                out.add(new ArrayList<>());
                in.add(new ArrayList<>());
            }
            depths = new int[n + 1];
            for (int i = 0; i < m; i++) {
                tmp = br.readLine().split(" ");
                int u = Integer.parseInt(tmp[0]);
                int v = Integer.parseInt(tmp[1]);
                out.get(u).add(v);
                in.get(v).add(u);
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int answer = 0;
        for(int i=1; i<=n; i++) {
            int cnt = getSmallerNodeCnt(i) + getLargerNodeCnt(i);
//            System.out.println("i : " + i + " ,cnt : " + cnt);
            if(cnt == n-1) answer++;
        }
        System.out.println(answer);
    }

    public int getSmallerNodeCnt(int node) {
        int cnt = 0;
        boolean[] vis = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                int cur = q.poll();
                for(int nxt : in.get(cur)) {
                    if(vis[nxt]) continue;
                    vis[nxt] = true;
                    q.add(nxt);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public int getLargerNodeCnt(int node) {
        int cnt = 0;
        boolean[] vis = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                int cur = q.poll();
                for(int nxt : out.get(cur)) {
                    if(vis[nxt]) continue;
                    vis[nxt] = true;
                    q.add(nxt);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}