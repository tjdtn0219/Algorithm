import java.util.*;

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};
    
    int n;
    HashMap<Integer, int[][]> blankMap;
    HashMap<Integer, int[][]> pieceMap;
    int answer;
    public int solution(int[][] game_board, int[][] table) {
        init(game_board, table);
        solve();
        return answer;
    }
    
    public void solve() {
        for(int key1 : blankMap.keySet()) {
            int[][] blank = blankMap.get(key1);
            // System.out.println("===blank===");
            // printArr(blank);
            for(int key2 : pieceMap.keySet()) {
                int[][] piece = pieceMap.get(key2);
                // System.out.println("===blank===");
                // printArr(blank);
                boolean flag = false;
                int[][] tmp = getOpposite(piece);
                for(int i=0; i<4; i++) {
                    tmp = rotate(tmp);
                    // printArr(tmp);
                    int cnt = isSame(blank, tmp);
                    if(cnt > 0) {
                        // System.out.println("blank : ");
                        // printArr(blank);
                        // System.out.println("piece : ");
                        // printArr(tmp);
                        // System.out.println("+" + cnt);
                        // System.out.println("=====================");
                        answer += cnt;
                        flag = true;
                        break;
                    }
                }
                if(flag) {
                    pieceMap.remove(key2);
                    break;
                }
            }
        }
    }
    
    public int isSame(int[][] arr1, int[][] arr2) {
        int cnt = 0;
        if(arr1.length == arr2.length && arr1[0].length == arr2[0].length) {
            int n = arr1.length;
            int m = arr1[0].length;
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    if(arr1[i][j] == arr2[i][j]) {
                        if(arr1[i][j] == 0) {
                            cnt++;
                        }
                    } else {
                        return -1;
                    }
                }
            }
        } else {
            return -1;
        }
        return cnt;
    }
    
    public int[][] getOpposite(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] tmp = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                tmp[i][j] = (arr[i][j] + 1) % 2;
            }
        }
        return tmp;
    }
    
    public int[][] rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] tmp = new int[m][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                tmp[j][n-i-1] = arr[i][j];
            }
        }
        // System.out.println("tmp : ");
        // printArr(tmp);
        return tmp;
    }
    
    private void printArr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
    public void init(int[][] board, int[][] table) {
        int idx = 1;
        this.n = board.length;
        boolean[][] vis = new boolean[n][n];
        blankMap = new HashMap<>();
        pieceMap = new HashMap<>();
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] == 1 || vis[i][j]) continue;
                int[][] piece = bfs(i, j, board, vis, 0);
                // printArr(piece);
                blankMap.put(idx++, piece);
            }
        }
        
        vis = new boolean[n][n];
        idx = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(table[i][j] == 0 || vis[i][j]) continue;
                int[][] piece = bfs(i, j, table, vis, 1);
                // printArr(piece);
                pieceMap.put(idx++, piece);
            }
        }
        
    }
    
    public int[][] bfs(int x, int y, int[][] board, boolean[][] vis, int flag) {
        int minX = n;
        int minY = n;
        int maxX = -1;
        int maxY = -1;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        vis[x][y] = true;
        
        while(!q.isEmpty()) {
            Point cur = q.poll();
            minX = Math.min(cur.x, minX);
            minY = Math.min(cur.y, minY);
            maxX = Math.max(cur.x, maxX);
            maxY = Math.max(cur.y, maxY);
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(board[nx][ny] == flag && !vis[nx][ny]) {
                    q.add(new Point(nx, ny));
                    vis[nx][ny] = true;
                }
            }
        }
        
        int[][] arr = new int[maxX - minX + 1][maxY - minY + 1];
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[0].length; j++) {
                arr[i][j] = board[i + minX][j + minY];
            }
        }
        return arr;
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }
    
    
}