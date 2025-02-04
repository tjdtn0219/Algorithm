import java.util.*;

class Solution {
    
    int n, m;
    int[][] edges;
    int ans;
    
    public int solution(int n, int[][] wires) {
        init(n, wires);
        solve();
        return ans;
    }
    
    public void solve() {
        for(int removed=0; removed<m; removed++) {
            List<List<Integer>> graph = new ArrayList<>();
            int[] parents = new int[n+1];
            for(int i=0; i<=n; i++) {
                parents[i] = i;
            }
            
            // System.out.println("removed : " + edges[removed][0] + ", " + edges[removed][1]);
            for(int i=0; i<m; i++) {
                if(i == removed) continue;
                int u = edges[i][0];
                int v = edges[i][1];
                union(parents, u, v);
            }
            for(int i=1; i<=n; i++) {
                find(parents, i);
            }
            // printParents(parents);
            ans = Math.min(ans, getDiff(parents));
        }
    }
    
    public int getDiff(int[] parents) {
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        for(int i=1; i<=n; i++) {
            cntMap.put(parents[i], cntMap.getOrDefault(parents[i], 0) + 1);
        }
        boolean flag = false;
        int res = 0;
        if(cntMap.keySet().size() == 2) {
            for(int key : cntMap.keySet()) {
                if(!flag) {
                    res += cntMap.get(key);
                    flag = true;
                }
                else {
                    res -= cntMap.get(key); 
                } 
                // System.out.println("key : " + key + ", cnt : " + cntMap.get(key));
            }
        }
        // System.out.println("res : " + res);
        return Math.abs(res);
        
    }
    
    public void printParents(int[] parents) {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) {
            sb.append(parents[i]).append(" ");
        }
        System.out.println(sb);
    }
    
    public int find(int[] parents, int x) {
        if(x == parents[x]) return x;
        else return parents[x] = find(parents, parents[x]);
    }
    
    public void union(int[] parents, int u, int v) {
        u = find(parents, u);
        v = find(parents, v);
        if(u < v) {
            parents[v] = u;
        } else {
            parents[u] = v;
        }
    }
    
    public void init(int n, int[][] edges) {
        this.n = n;
        this.m = edges.length;
        this.ans = n;
        this.edges = edges;
    }
}