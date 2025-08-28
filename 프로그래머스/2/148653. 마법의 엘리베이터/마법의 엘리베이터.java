import java.util.*;

class Solution {
    
    int answer;
    int cur;
    int digitCnt;
    int[] comb;
    int[] mod;
    
    public int solution(int storey) {
        init(storey);
        solve();
        return answer;
    }
    
    // 2555
    // 2560 + 5
    // 2600 + 4 -> + 26 = 35
    
    // 2555
    // 2550 +5
    // 2500 +5
    
    // 909
    // 910 + 1
    // 900 + 1
    
    // 4
    public void solve() {
        int storey = cur;
        while(storey > 0) {
            int remainder = storey % 10;
            if(remainder == 5) {
                answer += 5;
                if((storey / 10) % 10 >= 5) {
                    //올림
                    storey /= 10;
                    storey++;
                } else {
                    //버림
                    storey /= 10;
                }
            } else if(remainder < 5) {
                answer += remainder;
                storey /= 10;
            } else {
                answer += 10 - remainder;
                storey /= 10;
                storey++;
            }
        }
        
    }
    
    public void init(int storey) {
        this.cur = storey;
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