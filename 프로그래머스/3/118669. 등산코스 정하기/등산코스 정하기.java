import java.util.*;

class Solution {
    
    static final int INF = Integer.MAX_VALUE;
    
    int n;
    List<List<Node>> graph;
    HashSet<Integer> gateSet;
    HashSet<Integer> summitSet;
    int[] dp;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        init(n, paths, gates, summits);
        return solve(summits);

    }
    
    public int[] solve(int[] summits) {
        Queue<Node> q = new LinkedList<>();
        int[] intensity = new int[n + 1];

        Arrays.fill(intensity, Integer.MAX_VALUE);

        for (int gate : gateSet) {
            q.add(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.c > intensity[cur.b]) continue;
            for (Node nxt : graph.get(cur.b)) {

                int dis = Math.max(intensity[cur.b], nxt.c);
                if (intensity[nxt.b] > dis) {
                    intensity[nxt.b] = dis;
                    q.add(new Node(nxt.b, dis));
                }
            }
        }

        int[] ans = new int[2];
        ans[0] = Integer.MAX_VALUE;
        ans[1] = Integer.MAX_VALUE;

        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < ans[1]) {
                ans[0] = summit;
                ans[1] = intensity[summit];
            }
        }

        return ans;
    }
    
    public void init(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        this.summitSet = new HashSet<>();
        this.gateSet = new HashSet<>();
        for(int summit : summits) {
            summitSet.add(summit);
        }
        for(int gate : gates) {
            gateSet.add(gate);
        }
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int c = path[2];
            if(gateSet.contains(a) || summitSet.contains(b)) {
                graph.get(a).add(new Node(b, c));
            } else if(gateSet.contains(b) || summitSet.contains(a)) {
                graph.get(b).add(new Node(a, c));
            } else {
                graph.get(a).add(new Node(b, c));
                graph.get(b).add(new Node(a, c));   
            }
        }
        dp = new int[n+1];
        Arrays.fill(dp, INF);
    }
}

class Node {
    int b, c;
    public Node(int b, int c) {
        this.b = b;
        this.c = c;
    }
}