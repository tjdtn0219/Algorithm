import java.util.*;

class Solution {
    
    int n, m;
    int[][] beginning;
    int[][] board;
    int[][] target;
    boolean[] isPick;
    int cnt;
    int answer;
    
    public int solution(int[][] beginning, int[][] target) {
        init(beginning, target);
        solve();
        return answer;
    }
    
    public void solve() {
        // System.out.println("n, m : " + n + ", " + m);
        dfs(0);
        if(answer == Integer.MAX_VALUE) answer = -1;
    }
    
    public void dfs(int k) {
        if(k == n) {
            // System.out.println("cnt1 : " + cnt);
            flipRows();
            // System.out.println("cnt2 : " + cnt);
            boolean isTarget = flipCols();
            // System.out.println("isTarget : " + isTarget + ", cnt3 : " + cnt);
            if(isTarget) {
                answer = Math.min(answer, cnt);
            }
            
            //init
            copyArr(board, beginning);  //initBoard
            cnt = 0;
            return ;
        }
        
        isPick[k] = true;
        dfs(k+1);
        isPick[k] = false;
        dfs(k+1);
    }
    
    public boolean flipCols() {
        for(int j=0; j<m; j++) {
            int isSame = getIsSame(j);
            if(isSame == 2) {
                cnt++;
            } else if(isSame == 0) {
                return false;
            }
        }
        return true;
    }
    
    public int getIsSame(int j) {
        int check = 0;
        for(int i=0; i<n; i++) {
            if(board[i][j] == target[i][j]) {
                check++;
            }
        }
        if(check == n) {
            return 1;   //같음
        } else if(check == 0) {
            return 2;   //반대
        } else {
            return 0;   //다름
        }
    }
    
    public void flipRows() {
        for(int i=0; i<n; i++) {
            if(isPick[i]) {
                cnt++;
                for(int j=0; j<m; j++) {
                    board[i][j] = (board[i][j] + 1) % 2;
                }
            }
        }
    }
    
    public void init(int[][] beginning, int[][] target) {
        this.n = beginning.length;
        this.m = beginning[0].length;
        this.beginning = beginning;
        this.board = new int[n][m];
        copyArr(board, beginning);
        this.target = target;
        this.isPick = new boolean[n];
        this.answer = Integer.MAX_VALUE;
    }
    
    public void copyArr(int[][] arr1, int[][] arr2) {
        for(int i=0; i<arr1.length; i++) {
            arr1[i] = arr2[i].clone();
        }
    }
}