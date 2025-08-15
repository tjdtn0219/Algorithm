import java.util.*;

class Solution {
    
    int answer;
    HashMap<Character, Integer> skillMap;
    String[] skillTrees;
    
    public int solution(String skill, String[] skill_trees) {
        init(skill, skill_trees);
        solve();
        return answer;
    }
    
    public void solve() {
        for(String skillTree : skillTrees) {
            if(isOkay(skillTree)) {
                // System.out.println(skillTree);
                answer++;
            }
        }
    }
    
    public boolean isOkay(String s) {
        int pre = -1;
        for(char c : s.toCharArray()) {
            Integer idx = skillMap.get(c);
            if(idx == null) continue;
            if(idx == pre+1) {
                pre = idx;
            } else {
                return false;
            }
        }
        return true;
    }
    
    public void init(String skill, String[] skillTrees) {
        this.skillMap = new HashMap<>();
        this.skillTrees = skillTrees;
        for(int i=0; i<skill.length(); i++) {
            skillMap.put(skill.charAt(i), i);
        }
    }
}