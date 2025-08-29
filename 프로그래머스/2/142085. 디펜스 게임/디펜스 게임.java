import java.util.*;

class Solution {
    
    int answer;
    int n;
    int k;
    int[] enemies;
    
    public int solution(int n, int k, int[] enemies) {
        init(n, k, enemies);
        solve();
        return answer;
    }
    
    public void solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i=0; i<enemies.length; i++) {
            int enemy = enemies[i];
            n -= enemy;
            pq.add(enemy);
            if(n < 0) {
                if(k > 0) {
                    n += pq.poll();
                    k--;
                } else {
                    answer = i;
                    break;
                }
            }
            answer = Math.max(answer, i+1);
        }
    }
    
    public void init(int n, int k, int[] enemies) {
        this.n = n;
        this.k = k;
        this.enemies = enemies;
    }
    
}