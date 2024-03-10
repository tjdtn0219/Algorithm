import java.util.*;

class Solution {
    
    List<List<Edge>> graph;
    int n, k;
    int[] dis;
    
    public int solution(int N, int[][] road, int K) {
        init(N, road, K);
        makeMinDistanceFromStart(1);
        // printDis();
        return solve();
    }
    
    public void printDis() {
        for(int i=1; i<=n; i++) {
            System.out.println("dis[" + i + "] = " + dis[i]);
        }
    }
    
    public void init(int n, int[][] road, int k) {
        this.n = n;
        this.k = k;
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : road) {
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
            graph.get(edge[1]).add(new Edge(edge[0], edge[2]));
        }
        dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
    }
    
    public void makeMinDistanceFromStart(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        dis[start] = 0;
        pq.add(new Edge(start, dis[start]));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(cur.cost > dis[cur.node]) continue;
            for(Edge nxt : graph.get(cur.node)) {
                if(nxt.cost + dis[cur.node] < dis[nxt.node]) {
                    dis[nxt.node] = nxt.cost + dis[cur.node];
                    pq.add(new Edge(nxt.node, dis[nxt.node]));
                }
            }
        }
    }
    
    public int solve() {
        int answer = 0;
        for(int i=1; i<=n; i++) {
            if(dis[i] <= k) answer++;
        }
        return answer;
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