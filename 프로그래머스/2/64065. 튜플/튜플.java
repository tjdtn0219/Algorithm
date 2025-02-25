import java.util.*;

class Solution {
    
    static final int MAX = 100_000;
    
    public int[] solution(String s) {
        s.substring(2, s.length() - 2);
        String[] splits = s.split("\\{");
        HashMap<Integer, HashSet<Integer>> setMap = new HashMap<>();
        
        int max = 0;
        List<Integer> ansList = new ArrayList<>();
        for(String str : splits) {
            if(str.length() == 0) continue;
            HashSet<Integer> set = getSet(str.substring(0, str.length()-2));
            setMap.put(set.size(), set);
            max++;
        }
        
        boolean[] vis = new boolean[MAX+1];
        for(int i=1; i<=max; i++) {
            HashSet<Integer> set = setMap.get(i);
            for(int num : set) {
                if(vis[num]) continue;
                ansList.add(num);
                vis[num] = true;
            }
        }
        
        return ansList.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public HashSet<Integer> getSet(String s) {
        // System.out.println("s : " + s);
        HashSet<Integer> set = new HashSet<>();
        String[] splits = s.split(",");
        for(String num : splits) {
            set.add(Integer.parseInt(num));
        }
        return set;
    }
}