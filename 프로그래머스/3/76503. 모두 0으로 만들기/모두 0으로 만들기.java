import java.util.*;

class Solution {
    
    int n;
    List<List<Integer>> graph;
    long answer;
    boolean[] vis;
    long[] a;
    int[] inDegree;
    
    public long solution(int[] a, int[][] edges) {
        init(a, edges);
        solve();
        return answer;
    }
    
    public void solve() {
        long cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<n; i++) {
            if(inDegree[i] == 1) {
                q.add(i);
            }
        }
        
        while(!q.isEmpty()) {
            int size = q.size();
            // printA(a);
            for(int i=0; i<size; i++) {
                int cur = q.poll();
                vis[cur] = true;
                // System.out.println("cur : " + cur);
                for(int nxt : graph.get(cur)) {
                    if(vis[nxt]) continue;
                    inDegree[nxt]--;
                    a[nxt] += a[cur];
                    cnt += (long) Math.abs(a[cur]);
                    a[cur] = 0;
                    if(inDegree[nxt] == 1) {
                        q.add(nxt);
                    }
                }
            }
        }
        boolean flag = true;
        for(long num : a) {
            if(num != 0) {
                flag = false;
                break;
            }
        }
        if(flag) answer = cnt;
        // printA(a);
        // System.out.println("cnt : " + cnt);
    }
    
    private void printA(int[] a) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            sb.append(a[i]).append(" ");
        }
        System.out.println(sb);
    }
    
    public void init(int[] a, int[][] edges) {
        this.n = a.length;
        this.answer = -1L;
        graph = new ArrayList<>();
        for(int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }
        this.vis = new boolean[n];
        this.inDegree = new int[n];
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
            inDegree[u]++;
            inDegree[v]++;
        }
        this.a = new long[n];
        for(int i=0; i<n; i++) {
            this.a[i] = (long) a[i];
        }
    }
    
}