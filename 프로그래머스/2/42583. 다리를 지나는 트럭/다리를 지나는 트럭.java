import java.util.*;

class Solution {
    
    int len;
    int maxW;
    Queue<Integer> readyQ;
    Queue<Integer> q;
    int n;
    int answer;
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        init(bridge_length, weight, truck_weights);
        solve();
        return answer;
    }
    
    public void solve() {
        int sumW = 0;
        int t = 0;
        while(!readyQ.isEmpty()) {
            // printQ(q);
            // printQ(readyQ);
            // System.out.println("t : " + t + " ----------------");
            int nxt = readyQ.peek();
            sumW -= q.poll();
            if(sumW + nxt <= maxW) {
                q.add(nxt);
                readyQ.poll();
                sumW += nxt;
            } else {
                q.add(0);
            }
            t++;
        }
        int idx = 0;
        int maxIdx = 0;
        while(!q.isEmpty()) {
            if(q.poll() > 0) {
                maxIdx = idx + 1;
            }
            idx++;
        }
        t += maxIdx;
        answer = t;
    }
    
    public void printQ(Queue<Integer> q) {
        StringBuilder sb = new StringBuilder();
        sb.append("q : ");
        for(int n : q) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
    
    public void init(int len, int maxW, int[] weights) {
        this.n = weights.length;
        this.len = len;
        this.maxW = maxW;
        this.readyQ = new LinkedList<>();
        for(int w : weights) {
            readyQ.add(w);
        }
        this.q = new LinkedList<>(); 
        for(int i=0; i<len; i++) {
            q.add(0);
        }
        // printQ(readyQ);
        // printQ(q);
        // System.out.println("--------");
    }
}