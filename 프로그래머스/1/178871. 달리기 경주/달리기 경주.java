import java.util.*;


class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        
        HashMap<Integer, String> hmap1 = new HashMap<>();
        HashMap<String, Integer> hmap2 = new HashMap<>();
        for(int i=1; i<=players.length; i++) {
            hmap1.put(i, players[i-1]);
            hmap2.put(players[i-1], i);
        }
        
        for(String call : callings) {
            String p2 = call;
            int p2Rank = hmap2.get(call);
            
            String p1 = hmap1.get(p2Rank-1);
            int p1Rank = p2Rank-1;
            
            p1Rank++;
            p2Rank--;
            
            hmap1.put(p1Rank, p1);
            hmap1.put(p2Rank, p2);
            hmap2.put(p1, p1Rank);
            hmap2.put(p2, p2Rank);
        }
        
        for(int i=1; i<=hmap1.size(); i++) {
            answer[i-1] = hmap1.get(i);
        }
        
        return answer;
    }
}