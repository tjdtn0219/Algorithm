import java.util.*;

class Solution {
    
    int answer;
    int cur;
    int[][] dungeons;
    int n;
    int[] comb;
    boolean[] vis;
    
    public int solution(int k, int[][] dungeons) {

        init(k, dungeons);
        solve();
        return answer;
    }
    
    public void solve() {
        makeComb(0);
    }
    
    public void makeComb(int k) {
        if(k == n) {
            // printComb();
            play();
            return ;
        }
        
        for(int i=0; i<n; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            comb[k] = i;
            makeComb(k+1);
            vis[i] = false;
        }
    }
    
    public void play() {
        int cur = this.cur;
        int cnt = 0;
        for(int idx : comb) {
            // System.out.println("dungeon : " + dungeon[0] + ", cur : " + cur + ", cnt : " + cnt);
            if(cur >= dungeons[idx][0]) {
                // System.out.println("enter");
                cnt++;
                cur -= dungeons[idx][1];
            }
        }
        // System.out.println("cnt : " + cnt);
        answer = Math.max(answer, cnt);   
    }
    
    private void printComb() {
        StringBuilder sb = new StringBuilder();
        for(int num : comb) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    public void init(int k, int[][] dungeons) {
        this.cur = k;
        this.dungeons = dungeons;
        this.n = dungeons.length;
        this.comb = new int[n];
        this.vis = new boolean[n];
    }
}