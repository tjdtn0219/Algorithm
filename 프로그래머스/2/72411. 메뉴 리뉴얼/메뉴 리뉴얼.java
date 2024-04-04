import java.util.*;

class Solution {
    
    static final int ALPHA_LEN = 26;
    
    HashMap<Integer, HashSet<Character>> orderMap;
    int[] course;
    boolean[] isUsed;
    char[] comb;
    List<String> result;
    HashMap<Integer, List<String>> cntMap;
    int maxCnt;
    
    public String[] solution(String[] orders, int[] course) {
        init(orders, course);
        return solve();
    
    }
    
    public void init(String[] orders, int[] course) {
        isUsed = new boolean[ALPHA_LEN];
        orderMap = new HashMap<>();
        for(int i=0; i<orders.length; i++) {
            String order = orders[i];
            HashSet<Character> hSet = new HashSet<>();
            for(int j=0; j<order.length(); j++) {
                char c = order.charAt(j);
                hSet.add(c);
                isUsed[c - 'A'] = true;
            }
            orderMap.put(i, hSet);
        }
        this.course = course;
        result = new ArrayList<>();
    }
    
    public String[] solve() {
        for(int n : course) {
            comb = new char[n];
            cntMap = new HashMap<>();
            maxCnt = 0;
            dfs(0, n, 'A');
            if(maxCnt < 2) continue;
            for(String str : cntMap.get(maxCnt)) {
                result.add(str);
            }
        }
        Collections.sort(result);
        return result.toArray(new String[0]);
    }
    
    public void dfs(int k, int n, char last) {
        if(k == n) {
            // printComb();
            int usedCnt = getUsedCnt();
            maxCnt = Math.max(usedCnt, maxCnt);
            List<String> list = cntMap.getOrDefault(usedCnt, new ArrayList<>());
            list.add(combToString());
            cntMap.put(usedCnt, list);
            return ;
        }
        
        for(char c=last; c<='Z'; c++) {
            if(!isUsed[c - 'A']) continue;
            comb[k] = c;
            dfs(k+1, n, (char) ((int) c+1));
        }
    }
    
    public int getUsedCnt() {
        int cnt = 0;
        for(int idx : orderMap.keySet()) {
            boolean flag = true;
            HashSet<Character> orderSet = orderMap.get(idx);
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
    
    public String combToString() {
        StringBuilder sb = new StringBuilder();
        for(char c : comb) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    public void printComb() {
        StringBuilder sb = new StringBuilder();
        for(char c : comb) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}