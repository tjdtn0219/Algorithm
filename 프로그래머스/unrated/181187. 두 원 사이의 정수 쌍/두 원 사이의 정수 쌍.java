class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for(int i = 1; i <= r2; i++) {
            // long r1_2 = r1*r1;
            // long r2_2 = r2*r2;
            // double y2 = Math.sqrt(r2_2 - (long)(x*x));
            // double y1 = Math.sqrt(r1_2 - (long)(x*x));
            // if(x >= r1) answer += (long)y2 + 1;
            // else answer += (long)y2 - (long)y1;
            long minJ = (int) Math.ceil(Math.sqrt(1.0*r1*r1 - 1.0*i*i));
            long maxJ = (int) Math.floor(Math.sqrt(1.0*r2*r2 - 1.0*i*i));
 
            answer += (maxJ - minJ + 1);
        }
        answer *= 4;
        return answer;
    }
}