import java.util.*;

class Node {
    int idx;
    int val;
    public Node(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
}

class Solution {
    
    public int[] stones;
    int k;
    int n;
    int answer;
    
    public int solution(int[] stones, int k) {
        init(stones, k);
        solve();
        return answer;
    }
    
    public void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.val - o1.val);
        for(int i=0; i<k; i++) {
            pq.add(new Node(i, stones[i]));
        }
        
        int min = pq.peek().val;
        
        for(int i=k; i<n; i++) {
            pq.add(new Node(i, stones[i]));
            while(!pq.isEmpty() && pq.peek().idx < i - (k-1)) {
                pq.poll();
            }
            // System.out.println("pq.size : " + pq.size());
            // System.out.println((i - k+1) + " : " + pq.peek().val);
            min = Math.min(min, pq.peek().val);
        }
        
        answer = min;
    }
    
    public void init(int[] stones, int k) {
        this.stones = stones;
        this.k = k;
        this.n = stones.length;
    }
    
}