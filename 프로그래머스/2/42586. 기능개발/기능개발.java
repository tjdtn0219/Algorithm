import java.util.*;

class Solution {
    
    int n;
    int[] days;
    List<Integer> ansList;
    
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        init(progresses, speeds);
        // for(int day : days) {
        //     System.out.println(day);
        // }
        solve();
        return ansList.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public void solve() {
        int cur = 0;
        ansList = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
            int num = days[i];
            int cnt = 1;
            for(int j=i+1; j<n; j++) {
                if(days[j] > num) {
                    break;
                }
                cnt++;
            }
            ansList.add(cnt);
            i += cnt-1;
        }
        // for(int ans : ansList) {
        //     System.out.println("ans : " + ans);
        // }
        
    }
    
    public void init(int[] progress, int[] speeds) {
        this.n = progress.length;
        days = new int[n];
        for(int i=0; i<n; i++) {
            days[i] = (100 - progress[i]) / speeds[i];
            if((100 - progress[i]) % speeds[i] > 0) {
                days[i]++;
            }
        }
    }
    
    
}