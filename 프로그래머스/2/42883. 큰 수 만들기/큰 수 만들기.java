import java.util.*;

class Solution {
    
    String answer;
    int[] arr;
    int k;
    
    public String solution(String number, int k) {
        
        // 1 2 3 1 2 3 4
        // 3 1 2 3 4
        
        // 0 1 2 3 4 5 6 7 8 9 10
        // 4 1 7 7 2 1 5 2 8 4 1 -- n=11 , 가능 7
        // '7' 7 2 1 5 2 8 4 1 -- 가능 6
        // '77' 2 1 5 2 8 4 1 -- 가능 5
        // '775' 2 8 4 1 -- 가능 4
        
        // 7 7 5 2 1 8 4 1  -- 1
        init(number, k);
        solve();
        
        return answer;
    }
    
    public void solve() {
        StringBuilder sb = new StringBuilder();
        int cnt = arr.length - k;
        int availCnt = cnt;
        
        int pickIdx = -1;
        for(int i=0; i<cnt; i++) {
            int maxIdx = -1;
            int max = -1;
            for(int j=pickIdx+1; j<arr.length - (availCnt-1); j++) {
                if(arr[j] > max) {
                    maxIdx = j;
                    max = arr[j];
                }
            }
            sb.append(max);
            pickIdx = maxIdx;
            availCnt--;
        }
        
        answer = sb.toString();
    }
    // 11 7 6 5 4 3  k = 2, availCnt=5
    
    public void init(String number, int k) {
        this.arr = new int[number.length()];
        this.k = k;
        int i = 0;
        for(char c : number.toCharArray()) {
            arr[i++] = c - '0';
        }
    }
}