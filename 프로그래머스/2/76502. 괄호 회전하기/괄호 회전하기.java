import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        String tmp = s;
        
        for(int i=0; i<s.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(tmp.substring(i)).append(tmp.substring(0,i));
            // System.out.println(sb);
            if(isCorrect(sb.toString())) answer++;
        }
        
        return answer;
    }
    
    public boolean isCorrect(String s) {
        Stack<Character> stk = new Stack<>();
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(stk.isEmpty()) {
                stk.add(c);
                continue;
            }
            if(c=='{' || c=='[' || c=='(') {
                stk.add(c); continue;
            }
            if((stk.peek()=='[' && c==']') || (stk.peek()=='(' && c==')') || (stk.peek()=='{' && c=='}')) {
                stk.pop();
            } else {
                // System.out.println("TAG : " + stk.peek() + " , " + c);
                return false;
            }
        }
        
        if(stk.isEmpty()) return true;
        else return false;
    }
}