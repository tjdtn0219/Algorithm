import java.util.*;

class Solution {
    
    int[][] dungeons;
    int n;
    int energy;
    int[] comb;
    boolean[] vis;
    int answer;
    
    public int solution(int k, int[][] dungeons) {
        
        init(k, dungeons);
        solve();
        
        return answer;
    }
    
    public void init(int k, int[][] dungeons) {
        this.dungeons = dungeons;
        this.n = dungeons.length;
        this.energy = k;
        this.comb = new int[n];
        this.vis = new boolean[n];
        this.answer = -1;
    }
    
    public void solve() {
        makeComb(0);
    }
    
    public void makeComb(int k) {
        if(k == n) {
            // printComb();
            answer = Math.max(answer, getClearedDungeonCnt());
            return ;
        }
        
        for(int i=0; i<n; i++) {
            if(vis[i]) continue;
            comb[k] = i;
            vis[i] = true;
            makeComb(k+1);
            vis[i] = false;
        }
        
    }
    
    public int getClearedDungeonCnt() {
        int cnt = 0;
        int curEnergy = energy;
        for(int idx : comb) {
            if(curEnergy < dungeons[idx][0]) break;
            curEnergy -= dungeons[idx][1];
            cnt++;
        }
        return cnt;
    }
    
    public void printComb() {
        StringBuilder sb = new StringBuilder();
        for(int num : comb) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    
}