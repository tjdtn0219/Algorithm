import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        int n = priorities.length;
        
        boolean[] vis = new boolean[n];
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1,o2)->o2.prior-o1.prior);
        Queue<Pair> q = new LinkedList<>();
        for(int i=0; i<n; i++) {
            q.add(new Pair(i, priorities[i]));
            pq.add(new Pair(i, priorities[i]));
        }
        
        int ans = 1;
        while(!q.isEmpty()) {
            Pair poll = q.poll();
            if(poll.prior < pq.peek().prior) {
                int highPrior = pq.poll().prior;
                q.add(poll);
                while(q.peek().prior!=highPrior) {
                    q.add(q.poll());
                }
                poll = q.poll();
                // if(q.poll().idx==location) break;
            } else {
                pq.poll();
            }
            System.out.println(poll.idx);
            if(poll.idx == location) break;
            ans++;
        }
        
        
        return ans;
    }
}

class Pair {
    int idx;
    int prior;
    public Pair(int idx, int prior) {
        this.idx = idx;
        this.prior = prior;
    }
}