import java.util.*;

class Solution {
    static final int MAX = 100_000;
    
    String s;
    
    public int[] solution(String s) {
        int[] answer = {};
        init(s);
        return solve();
    }
    
    public int[] solve() {
        return parseStr();
    }
    
    public int[] parseStr() {
        String[] splits = s.substring(2, s.length() - 2).split("\\{");
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        int len = 0;
        for(String tmp : splits) {
            // System.out.println("tmp : " + tmp);
            HashSet<Integer> set = getSet(tmp);
            map.put(set.size(), set);
            len++;
        }
        
        boolean[] vis = new boolean[MAX+1];
        List<Integer> ansList = new ArrayList<>();
        for(int i=1; i<=len; i++) {
            for(int num : map.get(i)) {
                // System.out.println("i : " + i + ", num : " + num);
                if(vis[num]) continue;
                vis[num] = true;
                ansList.add(num);
            }
        }
        return ansList.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public HashSet<Integer> getSet(String str) {
        HashSet<Integer> set = new HashSet<>();
        String[] splits = str.split(",|\\}");
        for(String s : splits) {
            // System.out.println("tmp2 : " + s);
            set.add(Integer.parseInt(s));
        }
        return set;
    }
    
    public void init(String s) {
        this.s = s;
    }
}