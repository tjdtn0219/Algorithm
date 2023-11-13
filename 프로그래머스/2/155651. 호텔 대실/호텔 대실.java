import java.util.*;

class Solution {
    public static int MAX_LEN = 62*60+10;
    public static int[] arr = new int[MAX_LEN];
    
    public int solution(String[][] book_time) {
        int answer = 0;
        
        for(int i=0; i<book_time.length; i++) {
            int st = TimeToAmount(book_time[i][0]);
            int en = TimeToAmount(book_time[i][1]);

            arr[st] += 1;
            arr[en+10] += -1;
        }
        
        for (int i = 1; i < MAX_LEN; i++) {
            arr[i] += arr[i-1];
            answer = Math.max(answer, arr[i]);
        }
        
        return answer;
    }
    
    
    public static int TimeToAmount(String time) {
        String[] strings = time.split(":");
        int H = Integer.parseInt(strings[0]);
        int M = Integer.parseInt(strings[1]);
        return 60*H + M;
    }
}