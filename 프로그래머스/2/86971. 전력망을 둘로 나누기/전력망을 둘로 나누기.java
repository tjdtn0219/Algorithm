import java.util.*;

class Solution {
    
    int n;
    int[][] wires;
    int ans;
    
    public int solution(int n, int[][] wires) {
        init(n, wires);
        solve();
        return ans;
    }
    
    public void solve() {
        for(int i=0; i<n-1; i++) {
            List<List<Integer>> graph = new ArrayList<>();
            for(int j=0; j<=n; j++) {
                // init Graph
                graph.add(new ArrayList<>());
            }
            for(int j=0; j<n-1; j++) {
                // set Graph
                if(i == j) continue;
                int u = wires[j][0];
                int v = wires[j][1];
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
            int diff = getCntDiff(graph);
            // System.out.println("diff  " + diff);
            ans = Math.min(ans, diff);
        }
    }
    
    public int getCntDiff(List<List<Integer>> graph) {
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        boolean[] vis = new boolean[n+1];
        for(int i=1; i<=n; i++) {
            if(vis[i]) continue;
            // System.out.println("ok i : " + i);
            int cnt = bfs(i, graph, vis);
            cntMap.put(i, cnt);
        }
        int diff = 0;
        boolean flag = false;
        for(int key : cntMap.keySet()) {
            // System.out.println("key : " + key + ", cnt : " + cntMap.get(key));
            if(!flag) {
                diff = cntMap.get(key);
                flag = true;
            } else {
                diff = Math.abs(diff - cntMap.get(key));
            }
        }
        return diff;
    }
    
    public int bfs(int st, List<List<Integer>> graph, boolean[] vis) {
        Queue<Integer> q = new LinkedList<>();
        q.add(st);
        vis[st] = true;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            cnt++;
            for(int nxt : graph.get(cur)) {
                if(vis[nxt]) continue;
                q.add(nxt);
                vis[nxt] = true;
            }
        }
        return cnt;
    }
    
    public void init(int n, int[][] wires) {
        this.ans = n;
        this.n = n;
        this.wires = wires;
    }
}