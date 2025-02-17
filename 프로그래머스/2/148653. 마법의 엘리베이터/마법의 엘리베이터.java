import java.util.*;

class Solution {
    
    public int solution(int storey) {
        int ans = 0;
        
        while(storey > 0) {
            int remainder = storey % 10;
            if(remainder == 5) {
                ans += 5;
                if((storey / 10) % 10 >= 5) {
                    //올림
                    storey /= 10;
                    storey++;
                } else {
                    //버림
                    storey /= 10;
                }
            } else if(remainder < 5) {
                ans += remainder;
                storey /= 10;
            } else {
                ans += 10 - remainder;
                storey /= 10;
                storey++;
            }
        }
        
        return ans;
    }
}
    