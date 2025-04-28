import java.util.*;

class Solution {
    
    String[] gems;
    int n;
    int m;
    int[] answer;
    int min;
    
    public int[] solution(String[] gems) {
        init(gems);
        System.out.println("m : " + m + ", n : " + n);
        solve();
        return answer;
    }
    
    public void solve() {
        HashMap<String, Integer> cntMap = new HashMap<>();
        int left = 0;
        int right = 0;
        cntMap.put(gems[0], 1);
        while(left <= right && right < n) {
            // System.out.println("left : " + left + ", right : " + right);
            // printMap(cntMap);
            check(cntMap, left, right);
            if(right - left + 1 == m && cntMap.keySet().size() == m) {
                break;
            } else if(cntMap.keySet().size() == m) {
                cntMap.put(gems[left], cntMap.get(gems[left]) - 1);
                if(cntMap.get(gems[left]) == 0) {
                    cntMap.remove(gems[left]);
                }
                left++;
            } else {
                if(right + 1 < n) {
                    right++;
                    cntMap.put(gems[right], cntMap.getOrDefault(gems[right], 0) + 1);   
                } else {
                    break;
                }
            }
            // if(left == right) {
            //     right++;
            //     cntMap.put(gems[right], cntMap.getOrDefault(gems[right], 0) + 1);
            // } else {
            //     if(right - left + 1 == m) {
            //         break;
            //     }
            //     else if(cntMap.keySet().size() == m) {
            //         cntMap.put(gems[left], cntMap.get(gems[left]) - 1);
            //         if(cntMap.get(gems[left]) == 0) {
            //             cntMap.remove(gems[left]);
            //         }
            //         left++;
            //     } else {
            //         right++;
            //         cntMap.put(gems[right], cntMap.getOrDefault(gems[right], 0) + 1);
            //     }
            // }
            
            
        }
    }
    
    private void printMap(HashMap<String, Integer> cntMap) {
        StringBuilder sb = new StringBuilder(); 
        for(String key : cntMap.keySet()) {
            sb.append(key).append(" : ").append(cntMap.get(key)).append("\n");
        }
        System.out.println(sb);
    }
    
    public void check(HashMap<String, Integer> cntMap, int left, int right) {
        if(cntMap.keySet().size() == m) {
            if(right - left + 1 < min) {
                answer[0] = left + 1;
                answer[1] = right + 1;
                min = right - left + 1;
            } 
        }
    }
    
    
    public void init(String[] gems) {
        HashSet<String> set = new HashSet<>();
        this.gems = gems;
        this.n = gems.length;
        for(String gem : gems) {
            set.add(gem);
        }
        this.m = set.size();
        this.answer = new int[2];
        this.min = Integer.MAX_VALUE;
    }
}