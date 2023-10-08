import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (t1, t2)-> t1[1] - t2[1]);
        
        int temp = -1;
        
        for(int[] target : targets) {
            int st = target[0];
            int en = target[1];
            
            if(st >= temp) {
                answer++;
                temp = en;
            }
        }
        
        return answer;
    }
}