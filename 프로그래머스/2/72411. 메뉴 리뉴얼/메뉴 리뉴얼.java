import java.util.*;

class Solution {
    
    public List<String> ans = new ArrayList<>();
    public List<Character> alphaList;
    public List<HashSet<Character>> orderList = new ArrayList<>();
    public char[] comb;
    // public int[] comb;
    public HashMap<String, Integer> hmap = new HashMap<>();
    public int maxCnt = 0;
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        for(int i=0; i<orders.length; i++) {
            orderList.add(new HashSet<>());
        }
        
        HashSet<Character> alphaSet = new HashSet<>();
        for(int i=0; i<orders.length; i++) {
            String order = orders[i];
            for(int j=0; j<order.length(); j++) {
                char c = order.charAt(j);
                alphaSet.add(c);
                orderList.get(i).add(c);
            }
        }
        alphaList = new ArrayList<>(alphaSet);
        
        
        for(int len : course) {
            comb = new char[len];
            btk(0, len, 0);
            for(String key : hmap.keySet()) {
                if(hmap.get(key) == maxCnt) ans.add(key);
            }
            maxCnt = 0;
            hmap.clear();
        }
        // btk(0, 2, 0);
        // for(String key : hmap.keySet()) {
        //     if(hmap.get(key) == maxCnt) ans.add(key);
        // }
        
        Collections.sort(ans);
        
        return ans.toArray(new String[ans.size()]);
    }
    
    public void btk(int k, int len, int li) {
        if(len == k) {
            int cnt = getContainCnt(comb);
            if(cnt >= 2) {
                StringBuilder sb = new StringBuilder();
                for(char c : comb) {
                    sb.append(c);
                }
                maxCnt = Math.max(maxCnt, cnt);
                hmap.put(sb.toString(), cnt);
                // ans.add(sb.toString());
            }
            return ;
        }
        
        for(int i=li; i<alphaList.size(); i++) {
            comb[k] = alphaList.get(i);
            btk(k+1, len, i+1);
        }
    }
    
    public int getContainCnt(char[] comb) {
        int cnt = 0;
        for(HashSet orderSet : orderList) {
            boolean flag = true;
            for(char c : comb) {
                if(!orderSet.contains(c)) {
                    flag = false;
                    break;
                }
            }
            if(flag) cnt++;
        }
        
        return cnt;
    }
    
}