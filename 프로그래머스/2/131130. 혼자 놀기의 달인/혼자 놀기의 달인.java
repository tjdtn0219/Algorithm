import java.util.*;

class Solution {
    
    static final int MAX_LEN = 101;
    
    int n;
    int[] boxes;
    int[] parents;
    boolean[] isOpened;
    
    public int solution(int[] cards) {
        int answer = 0;
        init(cards);
        // int k = dfs(1, 0);
        // System.out.println("k : " + k);
        return solve();
        // return answer;
    }
    
    public void init(int[] cards) {
        n = cards.length;
        boxes = new int[n+1];
        for(int i=1; i<=n; i++) {
            boxes[i] = cards[i-1];
        }
        parents = new int[MAX_LEN];
        for(int i=1; i<MAX_LEN; i++) {
            parents[i] = i;
        }
        isOpened = new boolean[n+1];
        
    }
    
    public int solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i=1; i<=n; i++) {
            if(isOpened[i]) continue;
            int cnt = dfs(i, 0);
            // System.out.println("idx : " + i + " k : " + k);
            // printIsOpened();
            pq.add(cnt);
        }
        // System.out.println("size : " + pq.size());
        if(pq.size()==1) return 0;
        else {
            int n1 = pq.poll();
            int n2 = pq.poll();
            System.out.println("n1 : " + n1 + " n2 : " + n2);
            return n1*n2;
        }
    }
    
    public void printIsOpened() {
        for(int i=1; i<=n; i++) {
            System.out.print(isOpened[i] + " ");
        }
        System.out.println();
    }
    
    public int dfs(int idx, int k) {
        // System.out.println("card : " + card + " isOpened[card] : " + isOpened[card]);
        if(isOpened[idx]) {
            
            return k;
        }
        isOpened[idx] = true;
        int nxtIdx = boxes[idx];
        return dfs(nxtIdx, k+1);
        
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