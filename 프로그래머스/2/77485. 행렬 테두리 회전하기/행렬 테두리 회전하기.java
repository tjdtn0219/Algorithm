import java.util.*;

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    int n, m;
    int[][] queries;
    int[] answer;
    int[][] board;
    int idx;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        init(rows, columns, queries);
        // printBoard(board);
        solve();
        return answer;
    }
    
    public void solve() {
        for(int[] query : queries) {
            doQuery(query);
        }
    }
    
    public void doQuery(int[] query) {
        Point p1 = new Point(query[0]-1, query[1]-1);
        Point p2 = new Point(query[2]-1, query[3]-1);
        rotate(p1, p2);
    }
    
    public void rotate(Point p1, Point p2) {
        int n = p2.x - p1.x + 1;
        int m = p2.y - p1.y + 1;
        if(n==1 || m==1) return ;
        int[][] arr = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                arr[i][j] = board[i + p1.x][j + p1.y];
            }
        }
        // System.out.println("===BEFORE===");
        // printBoard(arr);
        
        //rotate
        int tmp = arr[0][0];
        int min = tmp;
        for(int i=1; i<n; i++) {
            arr[i-1][0] = arr[i][0];
            min = Math.min(min, arr[i-1][0]);
        }
        for(int j=1; j<m; j++) {
            arr[n-1][j-1] = arr[n-1][j];
            min = Math.min(min, arr[n-1][j-1]);
        }
        for(int i=n-2; i>=0; i--) {
            arr[i+1][m-1] = arr[i][m-1];
            min = Math.min(min, arr[i+1][m-1]);
        }
        for(int j=m-2; j>=1; j--) {
            arr[0][j+1] = arr[0][j];
            min = Math.min(min, arr[0][j+1]);
        }
        arr[0][1] = tmp;
        answer[idx++] = min;
        
        // System.out.println("===AFTER===");
        // printBoard(arr);
        
        //INIT BOARD
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                board[i+p1.x][j+p1.y] = arr[i][j];
            }
        }
    } 
    
    private void printBoard(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        int n = arr.length;
        int m = arr[0].length;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public void init(int n, int m, int[][] queries) {
        this.n = n;
        this.m = m;
        this.queries = queries;
        this.answer = new int[queries.length];
        this.board = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                board[i][j] = i*m + j + 1;
            }
        }
    }
    
    
}