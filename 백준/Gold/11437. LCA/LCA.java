import java.io.*;
import java.util.*;

public class Main {

    int n, m;
    int[] parents;
    List<List<Integer>> graph;
    int[][] inputs;
    int[] depth;

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
            parents = new int[n+1];
            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) {
                parents[i] = i;
                graph.add(new ArrayList<>());
            }
            for(int i=0; i<n-1; i++) {
                String[] tmp = br.readLine().split(" ");
                int u = Integer.parseInt(tmp[0]);
                int v = Integer.parseInt(tmp[1]);
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            m = Integer.parseInt(br.readLine());
            inputs = new int[m][2];
            for(int i=0; i<m; i++) {
                inputs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            depth = new int[n+1];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void initParents() {
        int root = 1;
        boolean[] vis = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        vis[root] = true;
        int dep = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                int cur = q.poll();
                for(int child : graph.get(cur)) {
                    if(vis[child]) continue;
                    q.add(child);
                    parents[child] = cur;
                    vis[child] = true;
                    depth[child] = dep+1;
                }
            }
            dep++;
        }
    }

    public void solve() {
        initParents();
//        printDepth();
        for(int[] arr : inputs) {
            int u = arr[0];
            int v = arr[1];
            System.out.println(lca(u, v));
        }
    }

    public int lca(int u, int v) {

        while(depth[u] > depth[v]) {
            u = parents[u];
        }

        while(depth[u] < depth[v]) {
            v = parents[v];
        }

        while(u!=v) {
            u = parents[u];
            v = parents[v];
        }

        return u;

    }

}
