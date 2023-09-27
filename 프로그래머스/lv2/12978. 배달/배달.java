import java.util.*;

class Solution {
    
    public int solution(int N, int[][] road, int K) {
        int ans = 0;
        
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i=0; i<road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }
        
        int[] dis = new int[N+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        pq.add(new Edge(1, 0));
        
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(cur.cost > dis[cur.dest]) continue;
            for(Edge adj : graph.get(cur.dest)) {
                if(dis[cur.dest] + adj.cost < dis[adj.dest]) {
                    dis[adj.dest] = dis[cur.dest] + adj.cost;
                    pq.add(new Edge(adj.dest, dis[adj.dest]));
                }
            }
        }
        
        for(int i=1; i<=N; i++) {
            System.out.println(dis[i] + " ");
            if(dis[i] <= K) ans++;
        }
        // System.out.println();

        return ans;
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