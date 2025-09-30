import java.util.*;

class Solution {
    
    final int N = 1_000_000;
    
    int[] answer;
    int[] nodes;
    int[][] edges;
    HashMap<Integer, Set<Integer>> graph;
    HashMap<Integer, Set<Integer>> groupMap;
    int[] parents;
    int[] states;   //0: 짝수, 1: 홀수, 2: 역짝수, 3: 역홀수
    
    public int[] solution(int[] nodes, int[][] edges) {
        init(nodes, edges);
        solve();
        return answer;
    }
    
    public void solve() {
        initStates();
        int cnt1 = 0;
        int cnt2 = 0;
        for(int key : groupMap.keySet()) {
            boolean flag1 = false;
            boolean flag2 = false;
            // System.out.println("key : " + key);
            for(int root : groupMap.get(key)) {
                // System.out.println("root : " + root + ", isTree[root] : " + isTree(root));
                // System.out.println("--------");
                int isTree = isTree(root);
                if(isTree == 1) {
                    flag1 = true;
                } else if(isTree == 2) {
                    flag2 = true;
                }
            }
            if(flag1) {
                cnt1++;
            }
            if(flag2) {
                cnt2++;
            }
        }
        answer = new int[2];
        answer[0] = cnt1;
        answer[1] = cnt2;
    }
    
    public int isTree(int root) {
        int rootState = states[root];
        int parent = parents[root];
        if(rootState < 2) {
            // 루트는 홀짝
            for(int node : groupMap.get(parent)) {
                if(node == root) continue;
                // System.out.println("node : " + node + ", states[node] : " + states[node]);
                if(states[node] < 2) return 0;
            }
            return 1;
        } else {
            // 루트는 역홀짝
            for(int node : groupMap.get(parent)) {
                if(node == root) continue;
                if(states[node] >= 2) return 0;
            }
            return 2;
        }
    }
    
    public void initStates() {
        //0: 짝수, 1: 홀수, 2: 역짝수, 3: 역홀수
        this.states = new int[N+1];
        for(int node : nodes) {
            if(node % 2 == 0) {
                // 짝수
                if(graph.get(node).size() % 2 == 0) {
                    // 간선 개수 짝수
                    states[node] = 0;
                } else {
                    // 간선 개수 홀수
                    states[node] = 2;
                }
            } else {
                // 홀수
                if(graph.get(node).size() % 2 == 0) {
                    // 간선 개수 짝수
                    states[node] = 3;
                } else {
                    // 간선 개수 홀수
                    states[node] = 1;
                }
            }
        }
    }
    
    public void init(int[] nodes, int[][] edges) {
        this.nodes = nodes;
        parents = new int[N+1];
        for(int i=0; i<=N; i++) {
            parents[i] = i;
        }
        
        graph = new HashMap<>();
        
        for(int node : nodes) {
            graph.put(node, new HashSet<>());
        }
        
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
            union(u, v);
        }
        
        for(int node : nodes) {
            find(node);
        }
        
        initGroupMap(graph);
        
        // for(int key : groupMap.keySet()) {
        //     System.out.println(key + " : " + groupMap.get(key));
        // }   
    }
    
    public void initGroupMap(Map<Integer, Set<Integer>> graph) {
        groupMap = new HashMap<>();
        boolean[] vis = new boolean[N+1];
        
        for(int u : graph.keySet()) {
            if(vis[u]) continue;
            int parent = parents[u];
            // System.out.println("u : " + u + ", parent : " + parent);
            Set<Integer> set = groupMap.getOrDefault(parent, new HashSet<>());
            bfs(u, set, vis);
            groupMap.put(parent, set);
        }
    }
    
    public void bfs(int node, Set<Integer> set, boolean[] vis) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        vis[node] = true;
        
        while(!q.isEmpty()) {
            int cur = q.poll();
            set.add(cur);
            for(int nxt : graph.get(cur)) {
                if(vis[nxt]) continue;
                vis[nxt] = true;
                q.add(nxt);
            }
        }
    }
    
    public int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
    
    public void union(int u, int v) {
        u = find(u);
        v = find(v);
        
        if(u < v) {
            parents[v] = u;
        } else {
            parents[u] = v;
        }
    }
    
}