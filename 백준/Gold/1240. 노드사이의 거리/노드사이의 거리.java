import java.util.*;
import java.io.*;

public class Main {

    public static int[] parent;
    public static boolean[] vis;
    public static int ans = Integer.MAX_VALUE;

    public static List<List<Edge>> tree = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        for(int i=0; i<=n; i++) tree.add(new ArrayList<>());

        parent = new int[n+1];
        vis = new boolean[n+1];

        for(int i=0; i<n-1; i++) {
            strings = br.readLine().split(" ");
            int a = Integer.parseInt(strings[0]);
            int b = Integer.parseInt(strings[1]);
            int c = Integer.parseInt(strings[2]);

            if(find(a) != find(b)) union(a,b);
            tree.get(a).add(new Edge(b, c));
            tree.get(b).add(new Edge(a, c));
        }

        for(int i=0; i<m; i++) {
            strings = br.readLine().split(" ");
            int st = Integer.parseInt(strings[0]);
            int en = Integer.parseInt(strings[1]);

            ans = Integer.MAX_VALUE;
            Arrays.fill(vis, false);
            dfs(st, en, 0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);

    }

    public static void dfs(int u, int v, int k) {
        if(u==v) {
            ans = Math.min(ans, k);
            return ;
        }

        for(Edge adj : tree.get(u)) {
            if(vis[adj.b]) continue;
            vis[adj.b] = true;
            dfs(adj.b, v, k+adj.c);
            vis[adj.b] = false;
        }


    }

    public static int find(int x) {
        if(parent[x]!=x) return x;
        else return parent[x] = find(parent[x]);
    }

    public static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u<v) parent[v] = u;
        else parent[u] = v;
    }

}

class Edge {
    int b;
    int c;
    public Edge(int b, int c) {
        this.b = b;
        this.c = c;
    }
}
