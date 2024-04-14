import java.util.*;

class Solution {
    
    String s;
    int len;
    
    public int solution(String s) {
        init(s);
        return solve();
        
    }
    
    public void init(String s) {
        this.s = s;
        this.len = s.length();
    }
    
    public int solve() {
        // System.out.println("TAG : " + getDigitCnt(1));
        // System.out.println("TAG : " + getDigitCnt(10));
        // System.out.println("TAG : " + getDigitCnt(15));
        // System.out.println("TAG : " + getDigitCnt(101));
        int answer = len;
        for(int i=1; i<=len/2; i++) {
            int compressedLen = getCompressedLen(i);
            // System.out.println("interval : " + i + " , len : " + compressedLen);
            answer = Math.min(answer, compressedLen);
        }
        // System.out.println("interval " + 1 + " : " + getCompressedLen(1));
        // System.out.println("interval " + 2 + " : " + getCompressedLen(2));
        // System.out.println("interval " + 3 + " : " + getCompressedLen(3));
        // System.out.println("interval " + 4 + " : " + getCompressedLen(4));
        // System.out.println("interval " + 6 + " : " + getCompressedLen(6));
        return answer;
    }
    
    public int getCompressedLen(int interval) {
        int compressedLen = len;
        boolean flag = false;
        int successCnt = 1;
        int idx = 0;
        String last = "";
        while(idx < len) {
            String sub = s.substring(idx, Math.min(idx+interval, len));
            if(last.equals(sub)) {
                if(!flag) {
                    // System.out.println("첫 연속");
                    compressedLen -= interval;
                    successCnt = 2;
                    flag = true;
                } else {
                    compressedLen -= interval;
                    successCnt++;
                    // System.out.println("진행중인 연속");
                }
            } else {
                flag = false;
                // System.out.println("successCnt : " + successCnt);
                if(successCnt > 1) compressedLen += getDigitCnt(successCnt);
                successCnt = 1;
            }
            last = sub;
            idx += interval;
        }
        if(successCnt > 1) compressedLen += getDigitCnt(successCnt);
        // System.out.println("=================");
        return compressedLen;
    }
    
    public int getDigitCnt(int num) {
        int cnt = 0;
        while(num > 0) {
            cnt++;
            num /= 10;
        }
        return cnt;
    }
}