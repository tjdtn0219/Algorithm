import java.util.*;

class Solution {
    
    int[] a;
    int n;
    int answer;
    
    public int solution(int[] a) {
        init(a);
        solve();
        return answer;
    }
    
    public void solve() {
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        for(int num : a) {
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
        }
        
        for(int key : cntMap.keySet()) {
            //key: 교집합의 원소
            if(cntMap.get(key) <= answer) continue;
            int cnt = 0;
            
            for(int i=0; i<n-1; i++) {
                if(a[i] != key && a[i+1] != key) continue;
                if(a[i] == a[i+1]) continue;
                cnt++;
                i++;
            }
            answer = Math.max(answer, cnt);
        }
        answer *= 2;
    }
    
    public void init(int[] a) {
        this.a = a;
        this.n = a.length;
    }
}