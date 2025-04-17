import java.util.*;

class Solution {
    
    String[] enrolls;
    String[] sellers;
    int[] amounts;
    int n, m;
    HashMap<String, Integer> idxMap;
    int[] parents;
    HashMap<Integer, Integer> profitMap; 
    int[] answer;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        init(enroll, referral, seller, amount);
        solve();
        return answer;
    }
    
    public void solve() {
        for(int i=0; i<m; i++) {
            int sellerIdx = idxMap.get(sellers[i]);
            calculate(sellerIdx, amounts[i]);
        }
        
        // System.out.println("=======");
        
        int idx = 0;
        for(String name : enrolls) {
            int nameIdx = idxMap.get(name);
            // System.out.println("name : " + name + ", profit : " + profitMap.get(nameIdx));
            answer[idx++] = profitMap.getOrDefault(nameIdx, 0);
        }
        
    }
    
    public void calculate(int cur, int amount) {
        int fee = (int) (amount * 0.1);
        int own = amount - fee;
        profitMap.put(cur, profitMap.getOrDefault(cur, 0) + own);
        int parent = parents[cur];
        if(parent > -1 && fee > 0) {
            calculate(parent, fee);
        }
    }
    
    public void init(String[] enroll, String[] referral, String[] seller, int[] amount) {
        this.enrolls = enroll;
        answer = new int[n];
        n = enroll.length;
        int idx = 0;
        idxMap = new HashMap<>();
        for(String name : enroll) {
            idxMap.put(name, idx++);
        }
        
        parents = new int[n];
        for(int i=0; i<n; i++) {
            String child = enroll[i];
            String parent = referral[i];
            int childIdx = idxMap.get(child);
            if(parent.equals("-")) {
                parents[childIdx] = -1;
            } else {
                int parentIdx = idxMap.get(parent);
                parents[childIdx] = parentIdx;
            }
        }
        
        this.m = seller.length;
        this.sellers = seller;
        this.amounts = new int[m];
        for(int i=0; i<m; i++) {
            amounts[i] = amount[i] * 100;
        }
        
        answer = new int[n];
        profitMap = new HashMap<>();
        
    }
}