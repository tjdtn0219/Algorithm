import java.io.*;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;

    int n, m;
    List<List<Edge>> graph;
    long[] dis;

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
            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
            for(int i=0; i<m; i++) {
                String[] a_b_c = br.readLine().split(" ");
                int a = Integer.parseInt(a_b_c[0]);
                int b = Integer.parseInt(a_b_c[1]);
                int c = Integer.parseInt(a_b_c[2]);
                graph.get(a).add(new Edge(b, c));
            }
            dis = new long[n+1];
            Arrays.fill(dis, INF);
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        dis[1] = 0;

        for(int i=0; i<n-1; i++) {
            for(int j=1; j<=n; j++) {
                for(Edge edge : graph.get(j)) {
                    if(dis[j] != INF && dis[edge.dest] > dis[j] + edge.cost) {
                        dis[edge.dest] = dis[j] + edge.cost;

                    }
                }
            }
        }

        boolean isCycle = false;

        for(int i=1; i<=n; i++) {
            for(Edge edge : graph.get(i)) {
                if(dis[i] != INF && dis[edge.dest] > dis[i] + edge.cost) {
                    isCycle = true;
                }
            }
        }

        if(isCycle) {
            System.out.println(-1);
            return ;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=n; i++) {
            if(dis[i] == INF) sb.append(-1).append("\n");
            else sb.append(dis[i]).append("\n");
        }
        System.out.println(sb);

    }

}

class Edge {
    int dest;
    int cost;

    public Edge(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }
}