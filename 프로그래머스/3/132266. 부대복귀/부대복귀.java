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

    int n;
    int[] sources;
    int dest;
    int[] answer;
    int[] dis;
    List<List<Edge>> graph;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        init(n, roads, sources, destination);
        solve();
        return answer;
    }
    
    public void solve() {
        djkstra();
        int idx = 0;
        for(int source : sources) {
            if(dis[source] == Integer.MAX_VALUE) {
                answer[idx++] = -1;
            } else {
                answer[idx++] = dis[source];
            }
        }
    }
    
    public void djkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.c - o2.c;
        });
        dis[dest] = 0;
        pq.add(new Edge(dest, 0));
        
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(dis[cur.b] > cur.c) continue;
            for(Edge nxt : graph.get(cur.b)) {
                if(dis[cur.b] + nxt.c < dis[nxt.b]) {
                    dis[nxt.b] = dis[cur.b] + nxt.c;
                    pq.add(new Edge(nxt.b, dis[nxt.b]));
                }
            }
        }
    }
    
    public void init(int n, int[][] roads, int[] sources, int dest) {
        this.n = n;
        this.sources = sources;
        this.dest = dest;
        this.answer = new int[sources.length];
        this.dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        this.graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] road : roads) {
            int a = road[0];
            int b = road[1];
            graph.get(a).add(new Edge(b, 1));
            graph.get(b).add(new Edge(a, 1));
        }
    }
}