import java.util.*;

class Solution {
    
    String str;
    int n;
    String answer;
    
    public String solution(String p) {
        init(p);
        solve();
        return answer;
    }
    
    public void solve() {
        answer = dfs(str);
    }
    
    public String dfs(String s) {
        if(isRight(s)) {
            return s;
        }
        int cnt1 = 0;
        int cnt2 = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '(') cnt1++;
            if(s.charAt(i) == ')') cnt2++;
            if(cnt1 == cnt2) {
                String u = s.substring(0, i+1);
                String v = s.substring(i+1);
                StringBuilder sb = new StringBuilder();
                if(!isRight(u)) {
                    sb.append('(').append(dfs(v)).append(')').append(getStripAndOpp(u));
                } else {
                    sb.append(u).append(dfs(v));
                }
                return sb.toString();
            }
        }
        return "";
    }
    
    public String getStripAndOpp(String s) {
        s = s.substring(1, s.length()-1);
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(c == '(') sb.append(')');
            if(c == ')') sb.append('(');
        }
        return sb.toString();
    }
    
    public boolean isRight(String s) {
        Stack<Character> stk = new Stack<>();
        for(char c : s.toCharArray()) {
            if(stk.isEmpty()) stk.push(c);
            else {
                if(c == ')' && stk.peek() == '(') {
                    stk.pop();
                } else {
                    stk.push(c);
                }
            }
        }
        if(stk.isEmpty()) return true;
        else return false;
    }
    
    public void init(String s) {
        this.str = s;
        this.n = s.length();
    }
}