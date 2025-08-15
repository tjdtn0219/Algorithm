import java.util.*;

class Solution {
    
    String s;
    
    int answer;
    
    public int solution(String s) {
        init(s);
        // System.out.println("dCnt : " + getDigitCnt(1));
        solve();
        return answer;
    }
    
    public void solve() {
        int min = Integer.MAX_VALUE;
        if(s.length() == 1) {
            answer = 1;
            return ;
        }
        for(int len=1; len<=s.length()/2; len++) {
            min = Math.min(min, compress(len));
        }
        answer = min;
    }
    
    public int compress(int len) {
        String pre = "";
        int i = 0;
        int succeed = 1;
        int cnt = s.length();
        while(i < s.length() - len + 1) {
            String tmp = s.substring(i, i+len);
            if(pre.equals(tmp)) {
                succeed++;
            } else {
                if(succeed > 1) {
                    cnt -= len * (succeed - 1);
                    cnt += getDigitCnt(succeed);
                }
                pre = tmp;
                succeed = 1;
            }
            // System.out.println("i : " + i + " | len : " + len + " | tmp : " + tmp + " | succeed : " + succeed);
            i += len;
        }
        if(succeed > 1) {
            cnt -= len * (succeed - 1);
            cnt += getDigitCnt(succeed);
        }
        // System.out.println("cnt : " + cnt);
        return cnt;
    }
    
    public int getDigitCnt(int n) {
        int cnt = 0;
        while(n > 0) {
            n /= 10;
            cnt++;
        }
        return cnt;
    }
    
    public void init(String s) {
        this.s = s;
    }
}