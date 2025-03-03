import java.util.*;

class Node {
    int idx;
    int val;
    public Node(int idx, int val) {
        this.idx = idx;
        this.val = val;
    }
}

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        
        Stack<Node> stk = new Stack<>();
        // stk.add(new Node(0, prices[0]));
        
        for(int i=0; i<n; i++) {
            while(!stk.isEmpty()) {
                if(prices[i] < stk.peek().val) {
                    answer[stk.peek().idx] = i - stk.peek().idx;
                    stk.pop();
                } else {
                    break;
                }
            }
            stk.push(new Node(i, prices[i]));
        }
        
        while(!stk.isEmpty()) {
            answer[stk.peek().idx] = (n-1) - stk.peek().idx;
            stk.pop();
        }
        
        return answer;
    }
}