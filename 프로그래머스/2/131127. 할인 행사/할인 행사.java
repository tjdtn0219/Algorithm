import java.util.*;

class Solution {
    
    HashMap<String, Integer> wantMap;
    String[] discount;
    int ans;
    
    public int solution(String[] want, int[] number, String[] discount) {
        init(want, number, discount);
        solve();
        return ans;
    }
    
    public void solve() {
        
        HashMap<String, Integer> buyMap = new HashMap<>();
        for(int i=0; i<10; i++) {
            String item = discount[i];
            buyMap.put(item, buyMap.getOrDefault(item, 0) + 1);
        }
        int left = 0;
        int right = 10;
        for(int i=0; i<discount.length-10; i++) {
            // System.out.println("curDay : " + i + ", start_item : " + discount[i]);
            if(isJoin(buyMap)) {
                // System.out.println("TAG : isJoin = TRUE : " + i);
                ans++;
            }
            // System.out.println("left : " + left + ", right : " + right);
            String out = discount[left++];
            String in = discount[right++];
            // System.out.println(" out : " + out + ", in : " + in);
            buyMap.put(out, buyMap.getOrDefault(out, 0) - 1);
            buyMap.put(in, buyMap.getOrDefault(in, 0) + 1);
        }
        if(isJoin(buyMap)) {
            ans++;
        }
    }
    
    public boolean isJoin(HashMap<String, Integer> buyMap) {
        for(String want : wantMap.keySet()) {
            // System.out.println("item : " + want + ", buyMap.get : " + buyMap.getOrDefault(want, 0));
            if(buyMap.getOrDefault(want, 0) < wantMap.get(want)) {
                return false;
            }
        }
        return true;
    }
    
    public void init(String[] want, int[] number, String[] discount) {
        this.wantMap = new HashMap<>();
        for(int i=0; i<want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        // for(String key : wantMap.keySet()) {
        //     System.out.println("key : " + key + " , cnt : " + wantMap.get(key));
        // }
        // System.out.println("================================");
        this.discount = discount;
    }
}