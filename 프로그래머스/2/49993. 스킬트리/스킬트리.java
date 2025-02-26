import java.util.*;

class Solution {
    
    Queue<Character> skill;
    String[] skillTrees;
    int ans;
    
    public int solution(String skill, String[] skill_trees) {
        init(skill, skill_trees);
        solve();
        return ans;
    }
    
    public void solve() {
        // Queue<Character> q = new LinkedList<>(skill);
        // q.poll();
        // System.out.println("q.size() : " + q.size());
        // System.out.println("skill.size() : " + skill.size());
        for(String skillTree : skillTrees) {
            if(isPossible(skillTree)) {
                System.out.println("okay : " + skillTree);
                ans++;
            }
        }
    }
    
    public boolean isPossible(String skillTree) {
        Queue<Character> q = new LinkedList<>(skill);
        HashSet<Character> set = new HashSet<>(skill);
        // System.out.println("q.size() : " + q.size());
        // System.out.println("set.size() : " + set.size());
        int cnt = 0;
        for(char c : skillTree.toCharArray()) {
            if(q.isEmpty()) break;
            if(set.contains(c)) {
                cnt++;
            }
            if(c == q.peek()) {
                q.poll();
            }
        }
        if(cnt + q.size() == skill.size()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void init(String skill, String[] skill_trees) {
        this.skill = new LinkedList<>();
        for(char c : skill.toCharArray()) {
            this.skill.add(c);
        }
        this.skillTrees = skill_trees;
    }
}