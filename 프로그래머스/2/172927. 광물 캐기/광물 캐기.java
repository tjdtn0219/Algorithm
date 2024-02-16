import java.util.*;

class Solution {
    
    String[] use;
    int answer;
    HashMap<String, Integer> hmap = new HashMap<>();
    String[] weapons;
    int pickCnt;  //광물을 캔 곡괭이 수
    String[] weaponComb;
    
    
    public int solution(int[] picks, String[] minerals) {
        
        init(picks, minerals);
        // System.out.println("pickCnt : " + pickCnt);
        makeComb(0, minerals);
        return answer;
    }
    
    public void init(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        hmap = new HashMap<>();
        hmap.put("diamond", picks[0]);
        hmap.put("iron", picks[1]);
        hmap.put("stone", picks[2]);
        weapons = new String[]{"diamond", "iron", "stone"};
        pickCnt = getPickCnt(picks, minerals);
        weaponComb = new String[pickCnt];
    }
    
    public int getPickCnt(int[] picks, String[] minerals) {
        int n = 0;
        int pickSum = 0;
        for(int pick : picks) {
            pickSum += pick;
        }
        int mineralCnt = minerals.length;
        if(pickSum*5 <= mineralCnt) {
            n = pickSum;
        } else {
            n = mineralCnt/5;
            if(mineralCnt%5 != 0) n++;
        }
        return n;
    }
    
    public void makeComb(int k, String[] minerals) {
        if(k == pickCnt) {
            work(minerals);
            return ;
        }
        
        for(String weapon : weapons) {
            if(hmap.get(weapon) == 0) continue;
            weaponComb[k] = weapon;
            int cnt = hmap.get(weapon);
            hmap.put(weapon, cnt-1);
            makeComb(k+1, minerals);
            hmap.put(weapon, cnt);
        }
    }
    
    public void work(String[] minerals) {
        List<String> weaponList = new ArrayList<>();
        for(String weapon : weaponComb) {
            for(int i=0; i<5; i++) {
                weaponList.add(weapon);
            }
        }
        
        int sum = 0;
        int minLen = Math.min(minerals.length, weaponList.size());
        for(int i=0; i<minLen; i++) {
            sum += getEnergy(minerals[i], weaponList.get(i));
        }
        answer = Math.min(answer, sum);
    }
    
    public int getEnergy(String mineral, String weapon) {
        if(weapon.equals("diamond")) return 1;
        else if(weapon.equals("iron")) {
            if(mineral.equals("diamond")) return 5;
            else return 1;
        }
        else {  //stone
            if(mineral.equals("diamond")) return 25;
            else if(mineral.equals("iron")) return 5;
            else return 1;
        }
    }
    
}