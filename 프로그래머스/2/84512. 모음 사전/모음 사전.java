import java.util.*;

class Solution {
    
    static final char[] CHARS = {'A', 'E', 'I', 'O', 'U'};
    
    String word;
    int answer;
    int cnt;
    HashSet<String> strSet;
    
    public int solution(String word) {
        init(word);
        solve();
        return answer;
    }
    
    public void solve() {
        dfs("", 0);
        List<String> strList = new ArrayList<>(strSet);
        Collections.sort(strList);
        HashMap<String, Integer> idxMap = new HashMap<>();
        for(int i=0; i<strList.size(); i++) {
            idxMap.put(strList.get(i), i+1);
        }
        answer = idxMap.get(word);
    }
    
    public void dfs(String cur, int k) {
        if(k == 5) {
            cnt++;
            // System.out.println("cur : " + cur);
            strSet.add(cur);
            return ;
        }
        
        if(k > 0) {
            dfs(cur, k+1);
        }
        for(char c : CHARS) {
            dfs(cur + c, k+1);
        }
    }
    
    public void init(String word) {
        this.word = word;
        strSet = new HashSet<>();
    }
    
}