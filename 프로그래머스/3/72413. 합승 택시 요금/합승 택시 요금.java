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
    
    int answer;
    List<List<Edge>> graph;
    int n, s, a, b;
    int[][] dis;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        init(n, s, a, b, fares);
        solve();
        return answer;
    }
    
    public void solve() {
        //4 1 5 | 5 6 | 5 3 2
        for(int i=1; i<=n; i++) {
            dijkstra(i, dis[i]);
        }
        
        int min = dis[s][a] + dis[s][b];
        for(int k=1; k<=n; k++) {
            int cost = dis[s][k] + dis[k][a] + dis[k][b];
            min = Math.min(min, cost);
        }
        answer = min;
    }
    
    public void dijkstra(int st, int[] dis) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.c - o2.c);
        pq.add(new Edge(st, 0));
        dis[st] = 0;
        
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(cur.c > dis[cur.b]) continue;
            for(Edge nxt : graph.get(cur.b)) {
                if(dis[cur.b] + nxt.c < dis[nxt.b]) {
                    dis[nxt.b] = dis[cur.b] + nxt.c;
                    pq.add(new Edge(nxt.b, dis[nxt.b]));
                }
            }
        }
    }
    
    public void init(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        this.s = s;
        this.a = a;
        this.b = b;
        this.dis = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int c = fare[2];
            graph.get(u).add(new Edge(v, c));
            graph.get(v).add(new Edge(u, c));
        }
    }
}