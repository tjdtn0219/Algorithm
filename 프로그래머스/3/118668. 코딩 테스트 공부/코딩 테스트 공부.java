import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        
        int goalAl = 0;
        int goalCo = 0;
        for(int[] problem : problems) {
            goalAl = Math.max(goalAl, problem[0]);
            goalCo = Math.max(goalCo, problem[1]);
        }
        
        if(goalAl <= alp && goalCo <= cop) {
            return 0;
        }
        if(alp>=goalAl){
            alp=goalAl;
        }
        if(cop>=goalCo){
            cop=goalCo;
        }
        
        int[][] dp = new int[goalAl+2][goalCo+2];
        for(int i=alp;i<=goalAl;i++){
            for(int j=cop;j<=goalCo;j++){
                dp[i][j]=Integer.MAX_VALUE;
          }
        }
        
        dp[alp][cop] = 0;
        
        for(int i=alp; i<=goalAl; i++) {
            for(int j=cop; j<=goalCo; j++) {
                dp[i+1][j] = Math.min(dp[i][j] + 1, dp[i+1][j]);
                dp[i][j+1] = Math.min(dp[i][j] + 1, dp[i][j+1]);
                
                for(int[] p : problems) {
                    if(i < p[0] || j < p[1]) continue;
                    if(i + p[2] > goalAl && j + p[3] > goalCo) {
                        dp[goalAl][goalCo] = Math.min(dp[goalAl][goalCo], dp[i][j] + p[4]);
                    } else if(i + p[2] > goalAl) {
                        dp[goalAl][j + p[3]] = Math.min(dp[goalAl][j+p[3]], dp[i][j] + p[4]);
                    } else if(j + p[3] > goalCo) {
                        dp[i + p[2]][goalCo] = Math.min(dp[i + p[2]][goalCo], dp[i][j] + p[4]);
                    } else if(i + p[2] <= goalAl && j + p[3] <= goalCo) {
                        dp[i+p[2]][j+p[3]]=Math.min(dp[i+p[2]][j+p[3]], dp[i][j] + p[4]);
                    }
                }
            }
        }
        
        return dp[goalAl][goalCo];
    }
}