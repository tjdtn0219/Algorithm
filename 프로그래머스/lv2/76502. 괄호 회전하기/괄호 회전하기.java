import java.util.*;

class Solution {
    
    Deque<Character> dq = new LinkedList<>();
    
    public int solution(String s) {
        
        for(int i=0; i<s.length(); i++) {
            dq.add(s.charAt(i));
        }
        
        int ans = 0;
        
        for(int i=0; i<s.length(); i++) {
            if(is_Right()) ans++;
            rotate();
        }
        
        System.out.println(ans);
        
        return ans;
    }
    
    public void rotate() {
        dq.addLast(dq.pollFirst());
    }
    
    public boolean is_Right() {
        Stack<Character> stk = new Stack<>();
        
        for(char c : dq) {
            if(c==')' || c==']' || c=='}') {
                if(stk.isEmpty()) return false;
                else {
                    if(!is_match(stk.peek(), c)) return false;
                    else stk.pop();
                }
            } else {    //c=='(' || c=='[' || c=='{'
                stk.add(c);
            }
        }
        if(!stk.isEmpty()) return false;
        else return true;
    }
    
    public boolean is_match(char c1, char c2) {
        if(c1 == '(' && c2 == ')') return true;
        if(c1 == '[' && c2 == ']') return true;
        if(c1 == '{' && c2 == '}') return true;
        return false;
    }
    
}