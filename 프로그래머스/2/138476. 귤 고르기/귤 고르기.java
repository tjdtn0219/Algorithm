import java.util.*;

class Solution {
    
    HashMap<Integer, Integer> cntMap;
    PriorityQueue<Tangerine> pq;
    
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        init(tangerine);
        
        answer = solve(tangerine.length, k);
        
        return answer;
    }
    
    public void init(int[] tangerine) {
        cntMap = new HashMap<>();
        for(int n : tangerine) {
            int cnt = cntMap.getOrDefault(n, 0);
            cntMap.put(n, cnt+1);
        }
        pq = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        for(int size : cntMap.keySet()) {
            pq.add(new Tangerine(size, cntMap.get(size)));
        }
    }
    
    public int solve(int total, int k) {
        while(total > k) {
            total -= pq.poll().cnt;
        }
        if(total < k) {
            return pq.size()+1;
        }
        return pq.size();
    }
}

class Tangerine {
    int size;
    int cnt;
    public Tangerine(int size, int cnt) {
        this.size = size;
        this.cnt = cnt;
    }
}