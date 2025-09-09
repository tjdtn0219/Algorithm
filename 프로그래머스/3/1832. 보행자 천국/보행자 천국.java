class Solution {
    int MOD = 20170805;
    
    int answer;
    int n, m;
    int[][] map;
    int[][][] dp;
    
    public int solution(int m, int n, int[][] cityMap) {
        init(m, n, cityMap);
        solve();
        return answer;
    }
    //[0, 0, 0]
    //[0, 0, 0]
    //[0, 0, 0]
    
    //[0, 2, 0, 0, 0, 2]
    //[0, 0, 2, 0, 1, 0]
    //[1, 0, 0, 2, 2, 0]
    
    public void solve() {
        // printArr(map);
        
        // dp[i][j][0]: 아래, dp[i][j][1]: 오른쪽
        for(int i=1; i<n; i++) {
            // 아래
            if(map[i][0] == 1) break;
            if(map[i][0] == 2) {
                dp[i][0][0] = 1;
            } else {
                dp[i][0][0] = 1;
                dp[i][0][1] = 1;
            }
        }
        for(int j=1; j<m; j++) {
            // 오른쪽
            if(map[0][j] == 1) break;
            if(map[0][j] == 2) {
                dp[0][j][1] = 1;
            } else {
                dp[0][j][0] = 1;
                dp[0][j][1] = 1;
            }
        }
        
        // printArr(dp, 0);
        // printArr(dp, 1);
        // System.out.println("============");
        
        // dp[i][j][0]: 아래, dp[i][j][1]: 오른쪽
        for(int i=1; i<n; i++) {
            for(int j=1; j<m; j++) {
                if(map[i][j] == 0) {
                    // 모두 이동 가능
                    
                    dp[i][j][0] += dp[i-1][j][0];
                    dp[i][j][1] += dp[i-1][j][0];
                    // MOD
                    dp[i][j][0] %= MOD;
                    dp[i][j][1] %= MOD;
                    
                    dp[i][j][0] += dp[i][j-1][1];
                    dp[i][j][1] += dp[i][j-1][1];
                    // MOD
                    dp[i][j][0] %= MOD;
                    dp[i][j][1] %= MOD;
                } else if(map[i][j] == 2) {
                    // 자회전 불가
                    // 아래
                    dp[i][j][0] += dp[i-1][j][0];
                    // 오른쪽
                    dp[i][j][1] += dp[i][j-1][1];
                    
                    //MOD
                    dp[i][j][0] %= MOD;
                    dp[i][j][1] %= MOD;
                }
            }
        }
        
        // printArr(dp, 0);
        // printArr(dp, 1);
        // System.out.println("============");
        
        answer = Math.max(dp[n-1][m-1][0], dp[n-1][m-1][1]);
    }
    
    private void printArr(int[][][] arr, int k) {
        StringBuilder sb = new StringBuilder();
        if(k==0) {
            sb.append("아래\n");
        } else {
            sb.append("오른쪽\n");
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(arr[i][j][k]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    private void printArr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public void init(int m, int n, int[][] cityMap) {
        this.n = m;
        this.m = n;
        this.map = cityMap;
        this.dp = new int[m][n][2];
    }
}