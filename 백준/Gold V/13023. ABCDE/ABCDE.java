import java.io.*;
import java.util.*;

public class Main {

    int n, m;
    List<List<Integer>> graph;
    boolean[] isHasFriend;
    boolean flag;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        init();
        System.out.println(solve());
    }

    public void init() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] strings = br.readLine().split(" ");
            n = Integer.parseInt(strings[0]);
            m = Integer.parseInt(strings[1]);
            graph = new ArrayList<>();
            for(int i=0; i<n; i++) graph.add(new ArrayList<>());
            for(int i=0; i<m; i++) {
                strings = br.readLine().split(" ");
                int u = Integer.parseInt(strings[0]);
                int v = Integer.parseInt(strings[1]);
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            isHasFriend = new boolean[n];
            flag = false;
        } catch (Exception e) {
            System.out.println("INPUT ERROR");
            throw new RuntimeException(e);
        }

    }

    public int solve() {
        for(int i=0; i<n; i++) {
            flag = false;
            boolean[] vis = new boolean[n];
            vis[i] = true;
            dfs(i, 0, vis);
            if(flag) return 1;
//            System.out.println("i : " + i + " flag : " + flag);
        }
        return 0;
    }

    public void dfs(int u, int k, boolean[] vis) {
        if(k >= 4) {
            flag = true;
            return ;
        }
        for(int adj : graph.get(u)) {
            if(vis[adj]) continue;
            vis[adj] = true;
            dfs(adj, k+1, vis);
            vis[adj] = false;
        }
    }



}