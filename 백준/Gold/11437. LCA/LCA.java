import java.io.*;
import java.util.*;

public class Main {

    public static List<List<Integer>> tree = new ArrayList<>();
    public static int[] parent;
    public static int[] depth;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<=n; i++) {
            tree.add(new ArrayList<>());
        }
        parent = new int[n+1];
        depth = new int[n+1];

        for(int i=1; i<n; i++) {
            String[] strings = br.readLine().split(" ");
            int u = Integer.parseInt(strings[0]);
            int v = Integer.parseInt(strings[1]);

            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        bfs(1);

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++) {
            String[] strings = br.readLine().split(" ");

            int u = Integer.parseInt(strings[0]);
            int v = Integer.parseInt(strings[1]);

            sb.append(lca(u, v)).append("\n");
        }

        System.out.println(sb);

    }

    public static int lca(int u, int v) {

        while(depth[u] > depth[v]) {
            u = parent[u];
        }

        while(depth[u] < depth[v]) {
            v = parent[v];
        }

        while(u!=v) {
            u = parent[u];
            v = parent[v];
        }

        return u;

    }

    public static void bfs(int root) {
        int curDep = 1;

        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        depth[root] = curDep++;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                int polled = q.poll();
                for(int adj : tree.get(polled)) {
                    if(depth[adj] > 0) continue;
                    q.add(adj);
                    depth[adj] = curDep;
                    parent[adj] = polled;
                }
            }
            curDep++;
        }
    }
}
