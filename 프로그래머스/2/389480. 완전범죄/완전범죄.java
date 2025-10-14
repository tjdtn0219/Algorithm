import java.util.*;

class Solution {
    
    static final int INF = 100_000;
    
    int answer;
    int[][] infos;
    int[][] dp;
    int n, m;
    
    public int solution(int[][] info, int n, int m) {
        init(info, n, m);
        solve();
        return answer;
    }
    
    public void solve() {
        //dp[x][y] : 총 훔친 개수가 x개일 때, B도둑의 흔적개수가 y일때 A 도둑의 최소 흔적 개수
        dp[0][0] = 0;
        for(int i=1; i<dp.length; i++) {
            int a = infos[i-1][0];
            int b = infos[i-1][1];
            for(int j=0; j<m; j++) {
                // a 선택
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
                // b 선택
                if(j+b < m)
                dp[i][j+b] = Math.min(dp[i][j+b], dp[i-1][j]);
            }
        }
        int min = INF;
        for(int j = 0; j < m; j++){
            min = Math.min(dp[dp.length-1][j], min);
        }
        answer = min >= n ? -1 : min;
    }
    
    public void init(int[][] info, int n, int m) {
        this.infos = info;
        this.n = n;
        this.m = m;
        this.dp = new int[infos.length+1][m];
        for(int i=0; i<dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }
    }
}