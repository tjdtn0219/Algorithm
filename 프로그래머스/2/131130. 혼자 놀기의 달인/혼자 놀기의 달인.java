import java.util.*;

class Solution {
    
    int n;
    int[] cards;
    int[] parents;
    int ans;
    boolean[] vis;
    
    public int solution(int[] cards) {
        init(cards);
        solve();
        return ans;
    }
    
    public void solve() {
        for(int i=0; i<n; i++) {
            if(vis[cards[i]]) continue;
            dfs(cards[i]);
        }
        for(int i=1; i<=n; i++) {
            find(i);
        }
        
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        for(int i=1; i<=n; i++) {
            // System.out.println("x : " + i + ", parents[x] : " + parents[i]);
            cntMap.put(parents[i], cntMap.getOrDefault(parents[i], 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int key : cntMap.keySet()) {
            // System.out.println("key : " + key + ", val : " + cntMap.get(key));
            pq.add(cntMap.get(key));
        }
        if(pq.size() == 1) ans = 0;
        else ans = pq.poll() * pq.poll();
    }
    
    public void dfs(int cur) {
        int nxt = cards[cur-1];
        // System.out.println("curCard : " + cur + ", nxtCard : " + nxt);
        if(vis[nxt]) {
            return ;
        } else {
            vis[nxt] = true;
            union(cur, nxt);
            dfs(nxt);   
        }
    }
    
    public int find(int x) {
        if(parents[x] == x) return x;
        else return parents[x] = find(parents[x]);
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
    
    public void init(int[] cards) {
        this.n = cards.length;
        this.cards = cards;
        this.parents = new int[n+1];
        this.vis = new boolean[n+1];
        for(int i=0; i<=n; i++) {
            parents[i] = i;
        }
    }
}