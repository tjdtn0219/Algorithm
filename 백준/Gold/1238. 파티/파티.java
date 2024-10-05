import java.io.*;
import java.util.*;

public class Main {
	
    int n, m, x;
    List<List<Edge>> graph;
    List<List<Edge>> reverseGraph;

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
            String[] n_m_k = br.readLine().split(" ");
            n = Integer.parseInt(n_m_k[0]);
            m = Integer.parseInt(n_m_k[1]);
            x = Integer.parseInt(n_m_k[2]);
            graph = new ArrayList<>();
            reverseGraph = new ArrayList<>();
            for(int i=0; i<=n; i++) {
                graph.add(new ArrayList<>());
                reverseGraph.add(new ArrayList<>());
            }
            for(int i=0; i<m; i++) {
                String[] a_b_c = br.readLine().split(" ");
                int a = Integer.parseInt(a_b_c[0]);
                int b = Integer.parseInt(a_b_c[1]);
                int c = Integer.parseInt(a_b_c[2]);
                graph.get(a).add(new Edge(b, c));
                reverseGraph.get(b).add(new Edge(a, c));
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int[] dis1 = dijkstra(graph);
        int[] dis2 = dijkstra(reverseGraph);

        int ans = 0;
        for(int i=1; i<=n; i++) {
            ans = Math.max(ans, dis1[i] + dis2[i]);
        }

        System.out.println(ans);

    }

    public int[] dijkstra(List<List<Edge>> graph1) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.offer(new Edge(x, 0));
        
        boolean[] check = new boolean[n + 1];
        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[x] = 0;
 
        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            int cur = curEdge.node;
            
            if(curEdge.cost > dis[cur]) continue;
 
            for (Edge nxt : graph1.get(cur)) {
                if (dis[nxt.node] > dis[cur] + nxt.cost) {
                    dis[nxt.node] = dis[cur] + nxt.cost;
                    pq.add(new Edge(nxt.node, dis[nxt.node]));
                }
            }
        }
        return dis;
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