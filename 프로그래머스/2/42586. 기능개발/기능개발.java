import java.util.*;

class Solution {
    
    int n;
    int[] days;
    List<Integer> ansList;
    
    public int[] solution(int[] progresses, int[] speeds) {
        init(progresses, speeds);
        solve();
        return ansList.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public void solve() {
        Stack<Integer> stk = new Stack<>();
        int cnt = 0;
        for(int i=0; i<n; i++) {
            if(stk.isEmpty()) {
                stk.push(days[i]);
                cnt = 1;
            } else {
                if(stk.peek() >= days[i]) {
                    cnt++;
                } else {
                    stk.pop();
                    stk.push(days[i]);
                    ansList.add(cnt);
                    cnt = 1;
                }
            }
        }
        if(!stk.isEmpty()) {
            ansList.add(cnt);
        }
        
    }
    // 7 3 9
    
    public void init(int[] progresses, int[] speeds) {
        this.n = speeds.length;
        days = new int[n];
        for(int i=0; i<n; i++) {
            if((100 - progresses[i]) % speeds[i] == 0) {
                days[i] = (100 - progresses[i]) / speeds[i];
            } else {
                days[i] = (100 - progresses[i]) / speeds[i] + 1;
            }
        }
        this.ansList = new ArrayList<>();
    }
}