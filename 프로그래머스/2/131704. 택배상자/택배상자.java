import java.util.*;
import java.io.*;

class Solution {
    
    int n;
    int[] order;
    Stack<Integer> stk;
    Queue<Integer> q;
    
    public int solution(int[] order) {
        
        init(order);
        return solve();
        
    }
    
    public void init(int[] order) {
        n = order.length+1;
        this.order = order;
        stk = new Stack<>();
        q = new LinkedList<>();
    }
    
    public int solve() {
        for(int i=1; i<=n; i++) {
            q.add(i);
        }
        
        int idx = 0;
        while(!q.isEmpty()) {
            if(idx >= order.length) break;
            if(order[idx] == q.peek()) {
                idx++;
                q.poll();
            } else {
                if(!stk.isEmpty() && stk.peek()==order[idx]) {
                    idx++;
                    stk.pop();
                    // q.poll();
                } else {
                    stk.add(q.poll());
                    // System.out.println("curIdx : " + idx + " , q.peek() : " + q.peek());
                }
            }
        }
        int i = idx;
        // System.out.println();
        while(!stk.isEmpty() && i<n) {
            System.out.println(order[i] + " , " + stk.peek());
            if(order[i]!=stk.peek()) break;
            idx++; i++;
            stk.pop();
        }
        return idx;
    }
}