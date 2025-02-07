import java.util.*;

class Node {
    int price;
    int idx;
    public Node(int price, int idx) {
        this.price = price;
        this.idx = idx;
    }
}

class Solution {
    
    int n;
    int[] prices;
    
    public int[] solution(int[] prices) {
        int[] answer = {};
        init(prices);
        return solve();
    }
    
    public int[] solve() {
        int[] answer = new int[n];
        Stack<Node> stk = new Stack<>();
        for(int i=0; i<n; i++) {
            int price = prices[i];
            if(stk.isEmpty()) {
                stk.push(new Node(price, i));
            } else {
                while(!stk.isEmpty() && stk.peek().price > price) {
                    Node popped = stk.pop();
                    answer[popped.idx] = i - popped.idx;
                }
                stk.push(new Node(price, i));
            }
        }
        
        while(!stk.isEmpty()) {
            answer[stk.peek().idx] = (n-1) - stk.peek().idx;
            stk.pop();
        }
        
        return answer;
    }
    
    public void init(int[] prices) {
        this.n = prices.length;
        this.prices = prices;
    }
}