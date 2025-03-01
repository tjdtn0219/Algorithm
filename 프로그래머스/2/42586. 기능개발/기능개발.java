import java.util.*;

class Solution {
    
    int n;
    int[] restDays;
    
    public int[] solution(int[] progresses, int[] speeds) {
        init(progresses, speeds);
        return solve();
    }
    
    public int[] solve() {
        List<Integer> ansList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int cnt = 1;
            int j = i+1;
            for(j=i+1; j<n; j++) {
                if(restDays[i] >= restDays[j]) {
                    cnt++;
                } else {
                    break;
                }
            }
            i = j-1;
            ansList.add(cnt);
        }
        return ansList.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public void init(int[] progresses, int[] speeds) {
        this.n = progresses.length;
        restDays = new int[n];
        for(int i=0; i<n; i++) {
            int day = (100 - progresses[i]) / speeds[i];
            if((100 - progresses[i]) % speeds[i] != 0) day++;
            restDays[i] = day;
        }
    }
}