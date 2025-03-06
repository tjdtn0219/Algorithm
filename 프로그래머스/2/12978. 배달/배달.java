import java.util.*;

class Edge {
    int b;
    int c;
    public Edge(int b, int c) {
        this.b = b;
        this.c = c;
    }
}

class Solution {
    
    int n, k;
    List<List<Edge>> graph;
    int[] dis;
    int ans;
    
    public int solution(int N, int[][] road, int K) {

        init(N, K, road);
        solve();

        return ans;
    }
    
    public void solve() {
        dikjstra(1);
        for(int i=1; i<=n; i++) {
            if(dis[i] <= k) ans++;
        }
    }
    
    public void dikjstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.c - o2.c);
        dis[start] = 0;
        pq.add(new Edge(start, 0));
        
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(dis[cur.b] < cur.c) continue;
            // System.out.println("cur : " + cur.b);
            for(Edge nxt : graph.get(cur.b)) {
                // System.out.println("nxt : " + nxt.b + ", cost : " + nxt.c);
                if(dis[cur.b] + nxt.c < dis[nxt.b]) {
                    dis[nxt.b] = dis[cur.b] + nxt.c;
                    pq.add(new Edge(nxt.b, dis[nxt.b]));
                }
            }
        }
    }
    
    public void init(int n, int k, int[][] road) {
        this.n = n;
        this.k = k;
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : road) {
            int a = edge[0];
            int b = edge[1];
            int c = edge[2];
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }
        dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
    }
}