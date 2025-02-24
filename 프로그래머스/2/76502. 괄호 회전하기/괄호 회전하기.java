import java.util.*;

class Solution {
    
    String s;
    int n;
    int ans;
    
    public int solution(String s) {
        init(s);
        solve();
        return ans;
    }
    
    public void solve() {
        for(int i=0; i<n; i++) {
            // System.out.println("s : " + s);
            if(isRight(s)) {
                ans++;
            }
            s = s.substring(1) + s.substring(0,1);
        }
    }
    
    public boolean isRight(String s) {
        Stack<Character> stk = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c == '[' || c == '(' || c == '{') {
                stk.add(c);
            } else {
                if(stk.isEmpty()) return false;
                if(c == ']' && stk.peek() != '[') return false;
                if(c == ')' && stk.peek() != '(') return false;
                if(c == '}' && stk.peek() != '{') return false;
                stk.pop();
            }
        }
        if(!stk.isEmpty()) return false;
        return true;
    }
    
    public void init(String s) {
        this.s = s;
        this.n = s.length();
    }
}