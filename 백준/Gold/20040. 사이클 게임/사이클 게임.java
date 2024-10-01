import java.io.*;
import java.util.*;

public class Main {

    int n, m;
    List<List<Integer>> graph;
    int[] parents;
    int[][] nodes;

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
            String[] n_m = br.readLine().split(" ");
            n = Integer.parseInt(n_m[0]);    
            m = Integer.parseInt(n_m[1]);
            parents = new int[n];
            for(int i=0; i<n; i++) {
                parents[i] = i;
                // graph.add(new ArrayList<>());
            }
            nodes = new int[m][2];
            for(int i=0; i<m; i++) {
                String[] u_v = br.readLine().split(" ");
                nodes[i][0] = Integer.parseInt(u_v[0]);
                nodes[i][1] = Integer.parseInt(u_v[1]);
            }

        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    public boolean union(int u, int v) {
        u = find(u);
        v = find(v);

        if(u == v) return true;

        if(u < v) {
            parents[v] = u;
        } else {
            parents[u] = v;
        }
        return false;
    }

    public void solve() {
        int ans = 0;
        for(int i=0; i<m; i++) {
            int u = nodes[i][0];
            int v = nodes[i][1];
            boolean isCycle = union(u, v);
            // System.out.println("isCycle : " + isCycle);
            if(isCycle) {
                ans = i+1;
                break;
            }
        }

        System.out.println(ans);
    }


}
