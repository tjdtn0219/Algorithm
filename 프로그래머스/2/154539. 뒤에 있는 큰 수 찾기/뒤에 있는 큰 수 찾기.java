import java.util.*;

class Solution {
    
    int n;
    Stack<Pair> stk;
    int[] answer;
    
    public int[] solution(int[] numbers) {
        init(numbers);
        solve(numbers);
        // printAns();
        return answer;
    }
    
    public void init(int[] numbers) {
        n = numbers.length;
        stk = new Stack<>();
        answer = new int[n];
    }
    
    public void solve(int[] numbers) {
        for(int i=0; i<n; i++) {
            int num = numbers[i];
            if(stk.isEmpty()) {
                stk.add(new Pair(num, i));
            } else {
                while(!stk.isEmpty()) {
                    if(stk.peek().num < num) {
                        Pair popped = stk.pop();
                        answer[popped.idx] = num;
                    } else {
                        break;
                    }
                }
                stk.add(new Pair(num, i));
            }
        }
        while(!stk.isEmpty()) {
            answer[stk.pop().idx] = -1;
        }
    }
    
    public void printAns() {
        for(int i=0; i<n; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}

class Pair {
    int num;
    int idx;
    public Pair(int num, int idx) {
        this.num = num;
        this.idx = idx;
    }
}