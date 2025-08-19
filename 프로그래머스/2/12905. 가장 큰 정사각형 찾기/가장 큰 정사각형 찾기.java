

class Solution
{
    int n, m;
    int[][] board;
    int answer;
    
    public int solution(int [][]board)
    {
        init(board);
        solve();
        return answer;
    }
    
    public void solve() {
        int[][] dp = new int[n][m];
        for(int i=0; i<n; i++) {
            dp[i] = board[i].clone();
        }
        for(int i=1; i<n; i++) {
            for(int j=1; j<m; j++) {
                if(board[i][j] == 0) continue;
                if(dp[i-1][j] > 0 && dp[i][j-1] > 0 && dp[i-1][j-1] > 0) {
                    int min = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                    dp[i][j] = min + 1;
                }
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }
        answer = answer * answer;
        // printArr(dp);
    }
    
    private void printArr(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append("=====================");
        System.out.println(sb);
    }
    
    public void init(int[][] board) {
        this.n = board.length;
        this.m = board[0].length;
        this.board = board;
    }
    
}