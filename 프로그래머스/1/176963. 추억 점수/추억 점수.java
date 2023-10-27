import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photos) {
        int[] answer = new int[photos.length];
        
        HashMap<String, Integer> hmap = new HashMap<>();
        
        for(int i=0; i<name.length; i++) {
            hmap.put(name[i], yearning[i]);
        }
        
        for(int i=0; i<photos.length; i++) {
            for(String person : photos[i]) {
                answer[i] += hmap.getOrDefault(person, 0);
            }
        }
        
        return answer;
    }
}