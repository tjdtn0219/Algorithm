import java.util.*;

class Solution {
    
    int n;
    int len;
    int maxW;
    Queue<Integer> crossQ;
    Queue<Integer> readyQ;
    int ans;
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        init(bridge_length, weight, truck_weights);
        // return solve();
        solve();
        return ans;
    }
    
    private void printQ(Queue<Integer> q, int t) {
        StringBuilder sb = new StringBuilder();
        for(int n : q) {
            sb.append(n).append(" ");
        }
        System.out.println("T: " + t + ", Q: " + sb.toString());
    }
    
    public void solve() {
        int t = 0;
        int curW = 0;
        for(int i=0; i<len; i++) {
            crossQ.add(0);
        }
        
        while(!readyQ.isEmpty()) {
            curW -= crossQ.poll();
            
            if(curW + readyQ.peek() <= maxW) {
                int w = readyQ.poll();
                crossQ.add(w);
                curW += w;
            } else {
                crossQ.add(0);
            }
            t++;
            
        }
        System.out.println("T : " + t);
        ans = t + len;
    }
    
    public void init(int len, int maxW, int[] trucks) {
        this.n = trucks.length;
        this.len = len;
        this.maxW = maxW;
        crossQ = new LinkedList<>();
        readyQ = new LinkedList<>();
        for(int w : trucks) {
            readyQ.add(w);
        }
    }
}