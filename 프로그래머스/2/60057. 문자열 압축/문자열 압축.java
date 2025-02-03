import java.util.*;

class Solution {
    
    String s;
    int n;
    
    public int solution(String s) {
        init(s);
        return solve();
    }
    
    public int solve() {
        if(n == 1) return 1;
        int ans = Integer.MAX_VALUE;
        for(int len=1; len<=n/2; len++) {
            ans = Math.min(ans, getCompressedLen(len));
        }
        return ans;
    }
    
    public int getCompressedLen(int len) {
        int st = 0;
        String last = "";
        int cnt = 1;
        int res = n;
        boolean flag = false;
        while(st < n) {
            String sub = s.substring(st, Math.min(st + len, n));
            if(last.equals(sub)) {
                if(!flag) {
                    // 첫 연속
                    res -= len;
                    cnt = 2;
                    flag = true;
                } else {
                    res -= len;
                    cnt++;
                }
            } else {
                if(cnt > 1) {
                    res += getDigitNum(cnt);
                }
                cnt = 1;
                last = sub;
                flag = false;
            }
            st += len;
        }
        if(cnt > 1) {
            res += getDigitNum(cnt);
        }
        return res;
    }    
    
    public int getDigitNum(int num) {
        int cnt = 0;
        while(num > 0) {
            num /= 10;
            cnt++;
        }
        return cnt;
    }
    
    public void init(String s) {
        this.n = s.length();
        this.s = s;
    }
    
}