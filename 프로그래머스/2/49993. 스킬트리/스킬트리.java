import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = -1;
        
        HashMap<Character, Integer> hmap = new HashMap<>();
        for(int i=0; i<skill.length(); i++) {
            char c = skill.charAt(i);
            hmap.put(c, i);
        }
       
        int ans = 0;
        for(String str : skill_trees) {
            boolean flag = true;
            int idx = 0;
            for(int i=0; i<str.length(); i++) {
                char c = str.charAt(i);
                // System.out.println("c : " + c + " , idx : " + idx);
                if(hmap.containsKey(c)) {
                    if(hmap.get(c)!=idx) {
                        flag=false;
                        break;
                    } else {
                        idx++;
                    }
                }
            }
            if(flag) {
                ans++;
                // System.out.println(str);
            }
            // System.out.println("============");
        }
        
        answer = ans;
        return answer;
        
    }
}