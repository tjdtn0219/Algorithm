import java.util.*;

class Solution {
    
    int answer;
    Queue<Long> q1;
    Queue<Long> q2;
    long sum1;
    long sum2;
    
    public int solution(int[] queue1, int[] queue2) {
        
        init(queue1, queue2);
        solve();
        return answer;
    }
    
    public void solve() {
        // 3 2 7 2 -> 14
        // 4 6 5 1 -> 16
        
        // 3 2 7 2 4 -> 18
        // 6 5 1 -> 12
        
        // 2 7 2 4 -> 15
        // 6 5 1 3 -> 15
        if((sum1 + sum2) % 2 == 1) {
            answer = -1;
            return ;
        }
        System.out.println(q1.peek());
        long tg = (sum1 + sum2) / 2;
        int n = q1.size();
        for(int i=0; i<4*n; i++) {
            // System.out.println("sum1 : " + sum1 + ", sum2 : " + sum2);
            // printQ(q1, 1);
            // printQ(q2, 2);
            if(sum1 < sum2) {
                sum1 += q2.peek();
                sum2 -= q2.peek();
                q1.add(q2.poll());
            } else if(sum1 > sum2) {
                sum2 += q1.peek();
                sum1 -= q1.peek();
                q2.add(q1.poll());
            } else {
                answer = i;
                return ;
            }
        }
        answer = -1;
    }
    
    public void printQ(Queue<Long> q, int idx) {
        StringBuilder sb = new StringBuilder();
        sb.append("q").append(idx).append(" : ");
        for(long n : q) {
            sb.append(n + " ");
        }
        System.out.println(sb);
    }
    
    public void init(int[] q1, int[] q2) {
        this.q1 = new LinkedList<>();
        this.q2 = new LinkedList<>();
        for(int num : q1) {
            this.q1.add((long) num);
            sum1 += (long) num;
        }
        
        for(int num : q2) {
            this.q2.add((long) num);
            sum2 += (long) num;
        }
    }
}