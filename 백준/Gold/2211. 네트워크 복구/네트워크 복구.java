import java.io.*;
import java.util.*;


public class Main {

    public static final int INF = Integer.MAX_VALUE;

    int N, M;
    List<List<Edge>> graph;
    int[] dis;
    int[] connect;

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
            String[] N_M = br.readLine().split(" ");
            N = Integer.parseInt(N_M[0]);
            M = Integer.parseInt(N_M[1]);

            graph = new ArrayList<>();
            for (int i=0; i<=N; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i=0; i<M; i++) {
                int[] a_b_c = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int a = a_b_c[0];
                int b = a_b_c[1];
                int c = a_b_c[2];
                graph.get(a).add(new Edge(b, c));
                graph.get(b).add(new Edge(a, c));
            }
            dis = new int[N+1];
            connect = new int[N+1];
            Arrays.fill(dis, INF);
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }


    public void solve() {
        dijkstra(1);
        int cnt = 0;

        StringBuilder sb = new StringBuilder();
        for(int i=2;i<=N;i++){
            if(connect[i] == 0) continue;
            cnt++;
            sb.append(i).append(" ").append(connect[i]).append("\n");
        }
        System.out.println(cnt);
        System.out.println(sb.toString());
    }

    public void dijkstra(int st) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.c - o2.c);
        pq.add(new Edge(st, 0));
        dis[st] = 0;

        while (!pq.isEmpty()) {
            Edge polled = pq.poll();
            int cur = polled.b;
            int curCost = polled.c;
            if (curCost > dis[cur]) continue;

            for (Edge adj : graph.get(cur)) {
                if(dis[cur] + adj.c < dis[adj.b]) {
                    dis[adj.b] = dis[cur] + adj.c;
                    connect[adj.b] = cur;
                    pq.add(new Edge(adj.b, dis[adj.b]));
                }
            }
        }
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