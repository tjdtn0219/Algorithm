import java.util.*;

class Solution {
    
    HashMap<Integer, HashSet<Integer>> hMap;
    int n;
    
    public int[] solution(String s) {
        init(s);
        return solve();
    }
    
    public void init(String s) {
        hMap = new HashMap<>();
        fillArrMap(s);
    }
    
    public void fillArrMap(String s) {
        s = s.substring(2, s.length()-2);
        String[] tmp = s.split("\\{");
        n = tmp.length;
        for(String str : tmp) {
            saveIntArrInArrMap(str);
        }
    }
    
    public void saveIntArrInArrMap(String s) {
        String[] tmp = s.split(",");
        HashSet<Integer> hSet = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for(String str : tmp) {
            if(str.charAt(str.length() - 1) == '}') str = str.substring(0, str.length()-1);
            list.add(Integer.parseInt(str));
            hSet.add(Integer.parseInt(str));
        }
        int n = list.size();
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
        hMap.put(n, hSet);
    }
    
    public int[] solve() {
        // System.out.println(" n ::: " + n);
        int[] answer = new int[n];
        for(int i=1; i<=n; i++) {
            HashSet<Integer> hset = hMap.get(i);
            for(int num : answer) {
                hset.remove(num);
            }
            for(int num : hset) {
                answer[i-1] = num;
            }
            // System.out.println("남은 Set 개수 : " + hset.size());
        }
        return answer;
    }
}