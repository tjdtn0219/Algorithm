import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        HashMap<Integer, Integer> hmap = new HashMap<>();
        for(int num : tangerine) {
            int cnt = hmap.getOrDefault(num, 0);
            hmap.put(num, cnt+1);
        }
        
        int total = tangerine.length;
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2)->o1.cnt-o2.cnt);
        for(int key : hmap.keySet()) {
            pq.add(new Pair(key, hmap.get(key)));
        }
        
        // System.out.println(total);
        
        // while(!pq.isEmpty()) {
        //     System.out.println(pq.poll().cnt);
        // }
        
        while(!pq.isEmpty()) {
            Pair polled = pq.poll();
            // System.out.println("total : " + total + " , cnt : " + polled.cnt);
            if(total - polled.cnt <= k) {
                // pq.add(polled);
                if(total - polled.cnt < k) pq.add(polled);
                break;
            }
            else {
                total -= polled.cnt;
            }
        }        
        
        answer = pq.size();
        
        return answer;
    }
}

class Pair {
    int num;
    int cnt;
    public Pair(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}