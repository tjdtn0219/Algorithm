class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        int max2 = -1, max1 = -1;
        for(int i = 1; i <= r2; i++) {
            int y;
            
            long y1 = (int)Math.ceil(Math.sqrt(1.0*r1*r1 - 1.0*i*i));
            long y2 = (int)Math.sqrt(1.0*r2*r2 - 1.0*i*i);

            if(i >= r1) answer += y2 + 1;
            else answer += (long)y2 - (long)y1 + 1;
        }
        answer *= 4;
        return answer;
    }
}