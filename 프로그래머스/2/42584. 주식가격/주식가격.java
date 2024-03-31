import java.util.*;

class Solution {
    
    int n;
    int[] prices;
    int[] answer;
    
    public int[] solution(int[] prices) {
        init(prices);
        solve();
        return answer;
    }
    
    public void init(int[] prices) {
        this.n = prices.length;
        this.prices = prices;
        this.answer = new int[n];
    }
    
    public void solve() {
        Stack<Info> stk = new Stack<>();
        for(int i=0; i<n; i++) {
            int price = prices[i];
            if(stk.isEmpty()) {
                stk.add(new Info(i, price));
            } else {
                if(stk.peek().val <= price) {
                    stk.add(new Info(i, price));
                } else {
                    while(!stk.isEmpty() && stk.peek().val > price) {
                        Info popped = stk.pop();
                        answer[popped.idx] = i - popped.idx;    
                    }
                    stk.add(new Info(i, price));
                }
            }
        }
        for(Info info : stk) {
            // System.out.println("idx : " + info.idx + ", val : " + info.val);
            answer[info.idx] = (n-1) - info.idx;
        }
        
    }
}

class Info {
    int idx;
    int val;
    
    public Info(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
}