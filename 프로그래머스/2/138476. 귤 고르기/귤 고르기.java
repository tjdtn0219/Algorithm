import java.util.*;

class Solution {
    
    int k;
    int[] tangerines;
    int answer;
    
    public int solution(int k, int[] tangerine) {
        init(k, tangerine);
        solve();
        return answer;
    }
    
    public void solve() {
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        for(int num : tangerines) {
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int key : cntMap.keySet()) {
            pq.add(cntMap.get(key));
        }
        
        while(!pq.isEmpty()) {
            int polled = pq.poll();
            if(polled >= k) {
                answer++;
                break;
            } else {
                k -= polled;
                answer++;
            }
        }
    }
    
    public void init(int k, int[] tangerines) {
        this.k = k;
        this.tangerines = tangerines;
    }
}