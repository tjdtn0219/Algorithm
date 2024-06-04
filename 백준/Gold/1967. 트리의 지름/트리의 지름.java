import java.io.*;
import java.util.*;

public class Main {


    int n;
    List<List<Node>> graph;
    int[] parents;
    List<Integer> leafs;
    int max;
    boolean[] leafVis;

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
            Arrays.fill(parents, -1);
            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i=0; i<n-1; i++) {
                String[] tmp = br.readLine().split(" ");
                int parent = Integer.parseInt(tmp[0]);
                int child = Integer.parseInt(tmp[1]);
                int cost = Integer.parseInt(tmp[2]);
                parents[child] = parent;
                graph.get(parent).add(new Node(child, cost));
                graph.get(child).add(new Node(parent, cost));
            }
            leafs = new ArrayList<>();
            max = 0;
            leafVis = new boolean[n+1];
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        findLeafs();
        getMaxDistance();
        System.out.println(max);
    }

    public void getMaxDistance() {
        for(int leaf : leafs) {
            boolean[] vis = new boolean[n+1];
            vis[leaf] = true;
            dfs(leaf, vis, 0);
        }
    }

    public void dfs(int cur, boolean[] vis, int sum) {
        boolean flag = false;
        for(Node nxt : graph.get(cur)) {
            if(vis[nxt.v]) continue;
            vis[nxt.v] = true;
            dfs(nxt.v, vis, sum+nxt.cost);
            flag = true;
        }

        if(!flag) {
            max = Math.max(max, sum);
        }
    }

    public void findLeafs() {
        int root = findRoot();
        boolean[] vis = new boolean[n+1];
        vis[root] = true;
        leafsDFS(root, vis);
    }

    public void leafsDFS(int cur, boolean[] vis) {
        boolean flag = false;
        for(Node nxt : graph.get(cur)) {
            if(vis[nxt.v]) continue;
            vis[nxt.v] = true;
            leafsDFS(nxt.v, vis);
            flag = true;
        }

        if(!flag) leafs.add(cur);

    }

    public int findRoot() {
        for(int i=1; i<=n; i++) {
            if(parents[i] == -1) {
                return i;
            }
        }
        return -1;
    }

}

class Node {
    int v;
    int cost;
    public Node(int v, int cost) {
        this.v = v;
        this.cost = cost;
    }
}