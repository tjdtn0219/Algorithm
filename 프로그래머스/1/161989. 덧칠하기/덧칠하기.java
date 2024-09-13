class Solution {
    
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        
        int cur = 0;
        
        for(int idx : section) {
            if(cur >= idx) continue;
            cur = idx + m - 1;
            answer++;
        }
        
        return answer;
    }
}