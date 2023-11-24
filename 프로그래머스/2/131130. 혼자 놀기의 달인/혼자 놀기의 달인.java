import java.util.*;

class Solution {
    
    public int[] parent;
    public boolean[] isCycle;
    public boolean[] vis;
    
    public int solution(int[] cards) {
        int answer = 0;
        
        int n = cards.length;
        parent = new int[n+1];
        isCycle = new boolean[n+1];
        vis = new boolean[n+1];

       for(int x=1; x<=n; x++) parent[x] = x;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->o2-o1);
        for(int i=1; i<=n; i++) {
            if(vis[i]) pq.add(0);
            else pq.add(getTeam(i, cards));
        }
        
        int cnt1 = pq.poll();
        int cnt2 = pq.poll();
        
        if(cnt1==n) return 0;
        
        // System.out.println(cnt1 + " , " + cnt2);
        answer = cnt1 * cnt2;
        return answer;
    }
    
    public int getTeam(int x, int[] cards) {
        int cnt = 1;
        while(true) {
            int nxt = cards[x-1];
            if(find(nxt) == find(x) || vis[x]) break;
            vis[x] = true;
            union(x, nxt);
            x = nxt;
            cnt++;
        }
        return cnt;
    }
    
    public int find(int x) {
        if(x==parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }
    
    public void union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u<v) parent[v] = u;
        else parent[u] = v;
    }
}