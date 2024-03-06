import java.util.*;

class Solution {
    
    int n;
    int k;
    int[] enemy;
    
    public int solution(int n, int k, int[] enemy) {        
        init(n, k, enemy);
        return solve();
    }
    
    public void init(int n, int k, int[] enemy) {
        this.n = n;
        this.k = k;
        this.enemy = enemy;
    }
    
    
    public int solve() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int answer = 0;
        for (int i = 0; i < enemy.length; i++) {
			n -= enemy[i];
			pq.add(enemy[i]);

			if (n < 0) {
				if (k > 0) {
					n += pq.poll();
					k--;
				} else {
					break;
				}
			}
			answer++;
		}
		return answer;
    }
}