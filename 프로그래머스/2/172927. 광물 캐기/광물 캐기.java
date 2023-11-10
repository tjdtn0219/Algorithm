import java.util.*;

class Solution {

    public static String[] use;
    public static int ans = Integer.MAX_VALUE;
    public static HashMap<String, Integer> hmap = new HashMap<>();
    public static String[] weapons = {"diamond", "iron", "stone"};
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        hmap.put("diamond", picks[0]);
        hmap.put("iron", picks[1]);
        hmap.put("stone", picks[2]);
        
        int sum = 0;
        for(int n : picks) {
            sum += n;
        }
        
        int N = 0;
        if(sum*5 > minerals.length) {
            if(minerals.length%5==0) N=minerals.length/5;
            else N = minerals.length/5 + 1;
        } else {
            N = sum;
        }
        
        use = new String[N];
        
        bTrack(N, 0, minerals);
        
        return answer = ans;
    }
    
    public void bTrack(int N, int k, String[] minerals) {
        if(k==N) {
            int sum = 0;
            int len = minerals.length;
            int idx = 0;
            int i;
            for(String w : use) {
                for(i=idx; i<idx+5&&i<len; i++) {
                    // System.out.println("idx : " + i + " , " + minerals[i] + " , " + w);
                    sum += getEnergy(minerals[i], w);
                }
                idx += 5;
            }
            ans = Math.min(ans, sum);
            return ;
        }
        
        for(String weapon : weapons) {
            if(hmap.get(weapon) <= 0) continue;
            hmap.put(weapon, hmap.get(weapon)-1);
            use[k] = weapon;
            bTrack(N, k+1, minerals);
            hmap.put(weapon, hmap.get(weapon)+1);
        }
    }
    
    public int getEnergy(String mineral, String use) {
        if(use.equals("diamond")) return 1;
        else if(use.equals("iron")) {
            if(mineral.equals("diamond")) return 5;
            else return 1;
        }
        else {
            if(mineral.equals("diamond")) return 25;
            else if(mineral.equals("iron")) return 5;
            else return 1;
        }
    }
}