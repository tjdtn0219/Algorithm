import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 10_000_000;

    int v, e;
    int[][] dis;
    List<List<Edge>> graph;


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
            String[] v_e = br.readLine().split(" ");
            v = Integer.parseInt(v_e[0]);
            e = Integer.parseInt(v_e[1]);

            dis = new int[v+1][v+1];
            graph = new ArrayList<>();
            for(int i=0; i<=v; i++) {
                Arrays.fill(dis[i], INF);
                graph.add(new ArrayList<>());
            }
            for(int i=0; i<e; i++) {
                String[] a_b_c = br.readLine().split(" ");
                int a = Integer.parseInt(a_b_c[0]);
                int b = Integer.parseInt(a_b_c[1]);
                int c = Integer.parseInt(a_b_c[2]);
                graph.get(a).add(new Edge(b, c));
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        for(int i=1; i<=v; i++) {
            setMinDis(i);
        }

        int ans = INF;
        for(int i=1; i<=v; i++) {
            for(int j=i; j<=v; j++) {
                // System.out.println("i -> j : " + i + " -> " + j);
                if(i == j) ans = Math.min(ans, dis[i][j]);
                else ans = Math.min(ans, dis[i][j] + dis[j][i]);
            }
        }
        if(ans == INF) {
            System.out.println(-1);
        }
        else {
            System.out.println(ans);
        }

    }

    public void setMinDis(int st) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Edge(st, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(cur.cost > dis[st][cur.node]) continue;
            for(Edge adj : graph.get(cur.node)) {
                if(dis[st][adj.node] > cur.cost + adj.cost) {
                    dis[st][adj.node] = cur.cost + adj.cost;
                    pq.add(new Edge(adj.node, dis[st][adj.node]));
                }
            }
        }
    }
}

class Edge {
    int node;
    int cost;
    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}