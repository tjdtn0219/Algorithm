import java.util.*;

class Solution {
    
    HashMap<Integer, HashSet<Integer>> tupleMap;
    int[] answer;
    
    public int[] solution(String s) {
        init(s);
        solve();
        return answer;
    }
    
    public void solve() {
        HashSet<Integer> set = new HashSet<>();
        for(int i=1; i<=tupleMap.size(); i++) {
            for(int prev : tupleMap.get(i)) {
                if(!set.contains(prev)) {
                    answer[i-1] = prev;
                    set.add(prev);
                }
            }
        }
    }
    
    public void init(String s) {
        this.tupleMap = new HashMap<>();
        String[] tmp = parseStr(s.substring(1, s.length()-2));
        for(String str : tmp) {
            HashSet<Integer> set = toSet(str);
            tupleMap.put(set.size(), set);
        }
        this.answer = new int[tmp.length];
    }
    
    public HashSet<Integer> toSet(String s) {
        String[] splits = s.split(",");
        HashSet<Integer> set = new HashSet<>();
        for(String str : splits) {
            set.add(Integer.parseInt(str));
        }
        return set;
    }
    
    public String[] parseStr(String s) {
        String[] splits = s.split("},");
        String[] result = new String[splits.length];
        for(int i=0; i<splits.length; i++) {
            result[i] = splits[i].substring(1);
            // System.out.println(result[i]);
        }   
        return result;
    }
}