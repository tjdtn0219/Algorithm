import java.util.*;

class Solution {
    
    static final int[] DX = {1,0,-1,0,0};
    static final int[] DY = {0,1,0,-1,0};
    
    int n;  // n<=8
    int[][] origin;
    int answer;
    int[] comb;
    
    public int solution(int[][] clockHands) {
        init(clockHands);
        solve();
        return answer;
    }
    
    public void solve() {
        //첫번째 행의 회전수의 경우의 수(8^4)만 구하면됨
        makeComb(0);
    }
    
    private void printComb() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            sb.append(comb[i]).append(" ");
        }
        System.out.println(sb);
    }
    
    private void printBoard(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public void makeComb(int k) {
        //첫 번째 행의 모든 회전의 경우의수를 구함
        if(k == n) {
            // printComb();
            int totalCnt = 0;
            int[][] tmp = getCloneArr(origin);
            totalCnt += rotateFirstRow(tmp);
            // System.out.println("totalCnt_1 : " + totalCnt);
            
            totalCnt += rotateClocks(tmp);
            // System.out.println("totalCnt_2 : " + totalCnt);
            if(isOkay(tmp)) {
                answer = Math.min(answer, totalCnt);   
            }
            // System.out.println("====================");
            return ;
        }
        
        for(int i=0; i<4; i++) {
            comb[k] = i;
            makeComb(k+1);
        }
    }
    
    public boolean isOkay(int[][] board) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] != 0) return false;
            }
        }
        return true;
    }
    
    public int rotateFirstRow(int[][] board) {
        int sum = 0;
        for(int j=0; j<n; j++) {
            int cnt = comb[j];
            rotateOne(board, 0, j, cnt);
            sum += cnt;
        }
        return sum;
    }
    
    public int rotateClocks(int[][] board) {
        //2번째 행부터 구함
        int sum = 0;
        for(int i=1; i<n; i++) {
            for(int j=0; j<n; j++) {
                int cnt = (4 - board[i-1][j]) % 4;
                rotateOne(board, i, j, cnt);
                sum += cnt;
            }
        }
        return sum;
    }
    
    public void rotateOne(int[][] board, int x, int y, int cnt) {
        for(int dir=0; dir<5; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) continue;
            board[nx][ny] = (board[nx][ny] + cnt) % 4;
        }
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }
    
    public int[][] getCloneArr(int[][] board) {
        int[][] tmp = new int[n][n];
        for(int i=0; i<n; i++) {
            tmp[i] = board[i].clone();
        }
        return tmp;
    }
    
    public void init(int[][] board) {
        this.n = board.length;
        this.origin = board;
        this.comb = new int[n];
        this.answer = Integer.MAX_VALUE;
    }
}
