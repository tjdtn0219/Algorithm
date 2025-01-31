import java.util.*;

class Node {
    int b;
    int c;
    public Node(int b, int c) {
        this.b = b;
        this.c = c;
    }
}

class Solution {
    
    int n;
    int k;
    List<List<Node>> graph;
    int[] dis;
    int ans;
    
    public int solution(int N, int[][] road, int K) {
        init(N, road, K);
        solve();
        return ans;
    }
    
    public void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.c - o2.c;
        });
        pq.add(new Node(1, 0));
        dis[1] = 0;
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.c > dis[cur.b]) continue;
            for(Node nxt : graph.get(cur.b)) {
                if(dis[cur.b] + nxt.c < dis[nxt.b]) {
                    dis[nxt.b] = dis[cur.b] + nxt.c;
                    pq.add(new Node(nxt.b, dis[nxt.b]));
                }
            }
        }
        for(int i=1; i<=n; i++) {
            if(dis[i] <= k) ans++;
        }
    }
    
    public void init(int n, int[][] road, int k) {
        this.n = n;
        this.k = k;
        this.dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : road) {
            int a = edge[0];
            int b = edge[1];
            int c = edge[2];
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
    }
}