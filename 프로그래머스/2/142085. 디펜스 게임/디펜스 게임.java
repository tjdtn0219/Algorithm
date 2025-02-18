import java.util.*;

class Solution {
    
    int n, k;
    int[] enemies;
    int maxRound;
    int[] dp;
    int ans;
    
    public int solution(int n, int k, int[] enemy) {
        init(n, k, enemy);
        solve();
        return ans;
    }
    
    public void solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i=0; i<maxRound; i++) {
            n -= enemies[i];
            pq.add(enemies[i]);
            if(n < 0) {
                if(k > 0) {
                    n += pq.poll();
                    k--;
                } else {
                    ans = i;
                    return ;
                }
            }
            ans = Math.max(ans, i+1);
            // System.out.println("ans : " + ans);
        }
    }
    
    public void init(int n, int k, int[] enemies) {
        this.n = n;
        this.k = k;
        this.enemies = enemies;
        this.maxRound = enemies.length;
        this.dp = new int[maxRound];
    }
    
}