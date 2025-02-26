import java.util.*;

class Solution {
    
    int n;
    
    public int solution(String s) {
        n = s.length();
        int ans = n;
        for(int len=1; len<=n/2; len++) {
            String encoded = encode(s, len);
            ans = Math.min(ans, encoded.length());
            // System.out.println("len : " + len + ", encoded : " + encoded);
        }
        
        return ans;
    }
    
    public String encode(String s, int len) {
        int st = 0;
        int en = st + len - 1;
        StringBuilder sb = new StringBuilder();
        Stack<String> stk = new Stack<>();
        while(st < n) {
            String str = "";
            if(en < n) {
                str = s.substring(st, en+1);    
            } else {
                str = s.substring(st);
            }
            if(stk.isEmpty()) {
                stk.push(str);
            } else {
                if(stk.peek().equals(str)) {
                    stk.push(str);
                } else {
                    if(stk.size() > 1) {
                        sb.append(stk.size()).append(stk.pop());
                        while(!stk.isEmpty()) {
                            stk.pop();
                        }
                    } else {
                        sb.append(stk.pop());
                    }
                    stk.push(str);
                }
            }
            st += len;
            en += len;
        }
        
        if(stk.size() > 1) {
            sb.append(stk.size()).append(stk.pop());
            while(!stk.isEmpty()) {
                stk.pop();
            }
        } else {
            sb.append(stk.pop());
        }
        return sb.toString();
    }
    
}