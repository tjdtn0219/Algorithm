import java.util.*;

class Solution {    
    int bTime, recoveryPerSec, recoveryBonus;
    HashMap<Integer, Integer> attackMap;
    int maxHealth;
    int maxTime;
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        init(bandage, attacks, health);
        
        int curHealth = health;
        int cnt = 0;
        for(int curT = 1; curT <= maxTime; curT++) {
            // System.out.println("curT : " + curT + ", curHealth : " + curHealth);
            if(attackMap.keySet().contains(curT)) {
                //공격 턴
                cnt = 0;
                curHealth -= attackMap.get(curT);
                if(curHealth <= 0) return -1;
            } else {
                //치료 턴
                ++cnt;
                curHealth += recoveryPerSec;
                if(cnt >= bTime) {
                    curHealth += recoveryBonus;
                    cnt = 0;
                }
                if(curHealth > maxHealth) curHealth = maxHealth;   
            }
        }
        // System.out.println("curHealth : " + curHealth);
        
        return curHealth;
    }
    
    public void init(int[] bandage, int[][] attacks, int health) {
        bTime = bandage[0];
        recoveryPerSec = bandage[1];
        recoveryBonus = bandage[2];
        attackMap = new HashMap<>();
        maxTime = 0;
        for(int[] attack : attacks) {
            maxTime = Math.max(maxTime, attack[0]);
            attackMap.put(attack[0], attack[1]);
        }
        maxHealth = health;
    }
}