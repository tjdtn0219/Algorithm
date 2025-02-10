import java.util.*;

class Solution {
    
    Queue<Integer> q1;
    Queue<Integer> q2;
    long sum1;
    long sum2;    
    
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        init(queue1, queue2);
        return solve();
    }
    
    public int solve() {
        int len = q1.size() * 4;
        
        for(int i=0; i<len; i++) {
            if(sum1 == sum2) {
                return i;
            }
            if(sum1 > sum2) {
                int polled = q1.poll();
                q2.add(polled);
                sum1 -= polled;
                sum2 += polled;
            } else {
                int polled = q2.poll();
                q1.add(polled);
                sum1 += polled;
                sum2 -= polled;
            }
        }
        return -1;
    }
    
    public void init(int[] queue1, int[] queue2) {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
        for(int n : queue1) {
            q1.add(n);
            sum1 += n;
        }
        for(int n : queue2) {
            q2.add(n);
            sum2 += n;
        }
    }
}