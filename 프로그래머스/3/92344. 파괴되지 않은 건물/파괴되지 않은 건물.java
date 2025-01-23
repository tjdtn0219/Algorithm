class Solution {
    
    int n, m;
    int[][] sum;
    int[][] board;
    int[][] skill;
    
    public int solution(int[][] board, int[][] skill) {
        init(board, skill);
        return solve();
    }
    
    public int solve() {
        setPing();
        operate();
        
        int ans = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] + sum[i][j] > 0) ans++;
            }
        }
        return ans;
    }
    
    public void operate() {
        // 상하
        for (int x = 1; x < n; x++) {
            for (int y = 0; y < m; y++) {
                sum[x][y] += sum[x - 1][y];
            }
        }
        // 좌우
        for (int y = 1; y < m; y++) {
            for (int x = 0; x < n; x++) {
                sum[x][y] += sum[x][y - 1];
            }
        }
    }
    
    public void setPing() {
        for(int[] s : skill) {
            int x1 = s[1];
            int y1 = s[2];
            int x2 = s[3];
            int y2 = s[4];
            int deg = s[5];
            if(s[0] == 1) {
                deg *= -1;
            }
            sum[x1][y1] += deg;
            sum[x1][y2+1] -= deg;
            sum[x2+1][y1] -= deg;
            sum[x2+1][y2+1] += deg;
        }
        // print(sum);
    }
    
    public void print(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
        
    public void init(int[][] board, int[][] skill) {
        this.n = board.length;
        this.m = board[0].length;
        this.sum = new int[n+1][m+1];
        this.board = board;
        this.skill = skill;

    }
}