import java.util.*;

class Solution {
    
    int answer;
    int n;
    int[][] floyd;
    int[][] results;
    
    public int solution(int n, int[][] results) {
        init(n, results);
        solve();
        return answer;
    }
    
    public void printArr() {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                sb.append(floyd[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public void solve() {        
        for(int i = 0; i < results.length; i++){
            int A = results[i][0];
            int B = results[i][1];
            floyd[A][B] = 1; 
            floyd[B][A] = -1; 
        }
        
        // printArr();
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                for(int k = 1; k <= n; k++){
                    if(floyd[i][k] == 1 && floyd[k][j] == 1){
                        floyd[i][j] = 1;
                        floyd[j][i] = -1;
                    }
                    if(floyd[i][k] == -1 && floyd[k][j] == -1){
                        floyd[i][j] = -1;
                        floyd[j][i] = 1;
                    }
                }
            }
        }
        
        // printArr();
        
        for(int i = 1; i <= n; i++){
            int cnt = 0; 
            for(int j = 1; j <= n; j++){
                if(floyd[i][j] != 0) cnt++;
            }
            if(cnt == n-1) answer++;
        }
        
    }
    
    public void init(int n, int[][] results) {
        this.n = n;
        this.results = results;
        floyd = new int[n+1][n+1];
    }
}