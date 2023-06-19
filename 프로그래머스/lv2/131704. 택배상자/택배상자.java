import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        
        int max = 0;
        for(int i=0; i<order.length; i++) {
            if(max < order[i]) max = order[i];
        }
        
        for(int i=1; i<=max+1; i++) {
            queue.add(i);
        }
        
        int i = 0;
        while(true) {
            if(i==order.length) break;
            if(queue.peek() == order[i]) {
                queue.poll();
                i++;
            }
            else if(queue.peek() < order[i]) {
                stack.push(queue.poll());
            }
            else if(queue.peek() > order[i]) {
                if(stack.peek() == order[i]) {
                    stack.pop();
                    i++;
                } else {
                    break;
                }
            }
        }
        answer = i;
        return answer;
    }
}