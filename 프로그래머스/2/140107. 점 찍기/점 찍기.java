import java.util.*;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for(int x=0; x<=d; x+=k) {
            long tg = (long)d*d - (long)x*x;
            // System.out.println(Math.sqrt(d*d - x*x));
            long maxX = bSearch(tg, d);
            answer += (long) (maxX / k) + 1;
        }
        
        return answer;
    }
    
    public long bSearch(long tg, int d) {
        long st = 0;
        long en = d;
        long res = 0;
        while(st <= en) {
            long mid = (st + en) / 2;
            if(tg < mid*mid) {
                en = mid -1;
            } else {
                st = mid + 1;
                res = Math.max(res, mid);
            }
        }
        return res;
    }
}