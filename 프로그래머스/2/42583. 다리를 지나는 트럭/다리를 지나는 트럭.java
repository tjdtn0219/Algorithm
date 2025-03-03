import java.util.*;

class Solution {
    
    int n;
    int len;
    int maxW;
    int[] truckWeights;
    int ans;
    Queue<Integer> crossQ;
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        init(bridge_length, weight, truck_weights);
        solve();
        return ans;
    }
    
    private void printCrossQ() {
        StringBuilder sb = new StringBuilder();
        for(int num : crossQ) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    public void solve() {
        int idx = 0;
        int successCnt = 0;
        int sumW = 0;
        int t = 0;
        while(idx < n) {
            int nxt = truckWeights[idx];
            // printCrossQ();
            // System.out.println("nxt : " + nxt);
            int polled = crossQ.poll();
            if(polled > 0) {
                successCnt++;
                sumW -= polled;
            }
            if(sumW + nxt <= maxW) {
                crossQ.add(nxt);
                sumW += nxt;
                idx++;
            } else {
                crossQ.add(0);
            }
            t++;
            
        }
        System.out.println("T1 : " + t);
        
        while(!crossQ.isEmpty() && successCnt < n) {
            if(crossQ.poll() > 0) {
                successCnt++;
            }
            t++;
        }
        System.out.println("T2 : " + t);
        ans = t;
    }
    
    public void init(int bridge_length, int weight, int[] truck_weights) {
        this.len = bridge_length;
        this.maxW = weight;
        this.truckWeights = truck_weights;
        this.n = truck_weights.length;
        crossQ = new LinkedList<>();
        for(int i=0; i<len; i++) {
            crossQ.add(0);
        }
    }
}