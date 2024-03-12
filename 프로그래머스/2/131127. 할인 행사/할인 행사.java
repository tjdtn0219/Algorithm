import java.util.*;

class Solution {
    
    static final int DAYS = 10;
    
    HashMap<String, Integer> wantMap;
    String[] discount;
    
    public int solution(String[] want, int[] number, String[] discount) {
        
        init(want, number, discount);
        return solve();
        // int answer = 0;
        // return answer;
    }
    
    public void init(String[] want, int[] number, String[] discount) {
        wantMap = new HashMap<>();
        for(int i=0; i<want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        this.discount = discount;
    }
    
    public int solve() {
        int answer = 0;
        int discountDays = discount.length;
        for(int i=0; i+DAYS-1<discountDays; i++) {
            // System.out.println("i : " + isEnableBuy(i, i+DAYS-1));
            if(isEnableBuy(i, i+DAYS-1)) {
                answer++;
            }
        }
        return answer;
    }
    
    public boolean isEnableBuy(int st, int en) {
        HashMap<String, Integer> buyMap = new HashMap<>();
        for(int i=st; i<=en; i++) {
            String item = discount[i];
            buyMap.put(item, buyMap.getOrDefault(item, 0)+1);
        }
        for(String item : wantMap.keySet()) {
            if(wantMap.getOrDefault(item, 0) > buyMap.getOrDefault(item, 0)) {
                return false;
            }
        }
        return true;
    }
}