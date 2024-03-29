import java.util.*;

class Solution {
    
    Deque<Character> dq;
    
    public int solution(String s) {
        init(s);
        // init("()[{}]");
        return solve();
    }
    
    public void init(String s) {
        dq = new LinkedList<>();        
        for(int i=0; i<s.length(); i++) {
            // System.out.println(s.charAt(i));
            dq.addLast(s.charAt(i));
        }
    }
    
    public void printDq() {
        StringBuilder sb = new StringBuilder();
        for(Character c : dq) {
            sb.append(c);
        }
        System.out.println(sb);
    }
    
    public int solve() {
        int cnt = 0;
        // System.out.println("isRight : " + isRight());
        
        // System.out.println("size : " + dq.size());
        for(int i=0; i<dq.size(); i++) {
            // printDq();
            if(isRight()) cnt++;
            rotate();
        }
        return cnt;
    }
    
    public boolean isRight() {
        Stack<Character> stk = new Stack();
        for(Character c : dq) {
            if(stk.isEmpty()) {
                stk.add(c);
            } else{
                if(isPair(stk.peek(), c)) {
                    stk.pop();
                } else {
                    stk.add(c);
                }
            }
        }
        if(!stk.isEmpty()) return false;
        return true;
    }
    
    public boolean isPair(char c1, char c2) {
        if(c1 == '[' && c2 == ']') return true;
        if(c1 == '{' && c2 == '}') return true;
        if(c1 == '(' && c2 == ')') return true;
        return false;
    }
    
    public void rotate() {
        dq.addLast(dq.removeFirst());
    }
}