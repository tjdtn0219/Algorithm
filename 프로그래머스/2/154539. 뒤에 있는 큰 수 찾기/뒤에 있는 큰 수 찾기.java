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
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        Stack<Node> stk = new Stack<>();
        
        for(int i=0; i<n; i++) {
            if(stk.isEmpty()) {
                stk.push(new Node(i, numbers[i]));
            } else {
                while(!stk.isEmpty() && stk.peek().val < numbers[i]) {
                    answer[stk.peek().idx] = numbers[i];
                    stk.pop();
                }
                stk.push(new Node(i, numbers[i]));
            }
        }
        
        while(!stk.isEmpty()) {
            Node popped = stk.pop();
            answer[popped.idx] = -1;
        }
        
        return answer;
    }
}