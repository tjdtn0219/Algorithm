import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] order) {
        
        int n = order.length+1;
        
        Stack<Integer> stk = new Stack<>();
        Queue<Integer> q = new LinkedList<>();
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