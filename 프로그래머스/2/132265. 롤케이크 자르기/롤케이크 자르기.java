import java.util.*;

class Solution {
    
    int[] topping;
    int ans;
    HashMap<Integer, Integer> cntMap1;
    HashMap<Integer, Integer> cntMap2;
    int n;
    
    public int solution(int[] topping) {
        init(topping);
        solve();
        return ans;
    }
    
    public void solve() {
        for(int i=0; i<n-1; i++) {
            int num = topping[i];
            cntMap1.put(num, cntMap1.getOrDefault(num, 0) + 1);
            if(cntMap2.get(num) > 1) {
                cntMap2.put(num, cntMap2.get(num) - 1);
            } else {
                cntMap2.remove(num);
            }
            if(cntMap1.keySet().size() == cntMap2.keySet().size()) {
                ans++;
            }
        }
    }
    
    public void init(int[] topping) {
        this.topping = topping;
        this.ans = 0;
        this.n = topping.length;
        this.cntMap1 = new HashMap<>();
        this.cntMap2 = new HashMap<>();
        for(int num : topping) {
            cntMap2.put(num, cntMap2.getOrDefault(num, 0) + 1);
        }
    }
}