import java.util.*;

class Solution {
    
    int n;
    int[] info;
    List<List<Integer>> graph;
    int max;
    boolean[] vis;
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        init(info, edges);
        solve();
        return max;
    }
    
    public void solve() {
        dfs(0, new HashSet<>(), 0, 0);
        System.out.println("ans : " + max);
    }
    
    public void dfs(int cur, HashSet<Integer> set, int sheeps, int wolves) {
        // System.out.println("cur : " + cur);
        if(info[cur] == 0) {
            sheeps++;
        } else {
            wolves++;
        }
        
        if(sheeps <= wolves) {
            return ;
        }
        
        max = Math.max(max, sheeps);
        
        HashSet<Integer> nxtSet = new HashSet<>(set);
        nxtSet.remove(cur);
        for(int nxt : graph.get(cur)) {
            // System.out.print(nxt + " ");
            nxtSet.add(nxt);
        }
        // System.out.println("==========");
        for(int nxt : nxtSet) {
            dfs(nxt, nxtSet, sheeps, wolves);
        }
        
    }
    
    public void init(int[] info, int[][] edges) {
        this.n = info.length;
        this.info = info;
        this.graph = new ArrayList<>();
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
        }
        this.max = 0;
        this.vis = new boolean[n];
    }
}