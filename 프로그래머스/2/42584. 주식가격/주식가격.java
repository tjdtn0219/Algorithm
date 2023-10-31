import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        
        int[] answer = new int[n];
        for(int i=0; i<n; i++) {
            answer[i] = n-1-i;
        }
        
        Stack<Pair> stk = new Stack<>();
        
        stk.add(new Pair(0, prices[0]));
        for(int i=1; i<n; i++) {
            while(!stk.isEmpty() && stk.peek().val > prices[i]) {
                answer[stk.peek().idx] = i - stk.peek().idx;
                stk.pop();
            }
            stk.add(new Pair(i, prices[i]));
        }
        
        return answer;
    }
}

class Pair {
    int idx;
    int val;
    public Pair(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
}