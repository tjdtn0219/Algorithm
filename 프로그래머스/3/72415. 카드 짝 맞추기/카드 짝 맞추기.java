import java.util.*;

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Node {
    Point p;
    int dis;
    public Node(Point p, int dis) {
        this.p = p;
        this.dis = dis;
    }
}

class Solution {
    
    static final int N = 4;
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,-1,0,1};
    
    int[][] board;
    Point cur;
    int answer;
    int pairNum;
    int[] comb;
    
    public int solution(int[][] board, int r, int c) {
        init(board, r, c);
        solve();
        return answer;
    }
    
    private void printComb() {
        StringBuilder sb = new StringBuilder();
        for(int num : comb) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    
    public void solve() {
        dfs(0, new boolean[pairNum+1]);
        
    }
    
    public void dfs(int k, boolean[] vis) {
        if(k == pairNum) {
            // printComb();
            int[][] tmp = cloneBoard();
            answer = Math.min(answer, doComb());
            board = tmp;
            return ;
        }
        
        for(int i=1; i<=pairNum; i++) {
            if(vis[i]) continue;
            comb[k] = i;
            vis[i] = true;
            dfs(k+1, vis);
            vis[i] = false;
        }
    }
    
    public int[][] cloneBoard() {
        int[][] tmp = new int[N][N];
        for(int i=0; i<N; i++) {
            tmp[i] = board[i].clone();
        }
        return tmp;
    }
    
    public int doComb() {
        int x = cur.x;
        int y = cur.y;
        int cnt = 0;
        for(int tg : comb) {
            Node nd1 = getMinDisToTg(x, y, tg);
            //첫번째로 이동
            cnt += nd1.dis;
            // System.out.println("첫번째 이동하기까지 : " + cnt);
            int x1 = nd1.p.x;
            int y1 = nd1.p.y;
            //첫번째 오픈
            cnt += 1;
            // System.out.println("첫번째 오픈하기까지 : " + cnt);
            
            //두번째로 이동
            Node nd2 = getMinDisToPair(x1, y1);
            cnt += nd2.dis;
            // System.out.println("두번째 이동하기까지 : " + cnt);
            int x2 = nd2.p.x;
            int y2 = nd2.p.y;
            //두번째 오픈
            cnt += 1;
            board[x1][y1] = 0;
            board[x2][y2] = 0;
            // System.out.println("두번째 오픈하기까지 : " + cnt);
            
            x = x2;
            y = y2;
        }
        return cnt;
    }
    
    public Node getMinDisToTg(int x, int y, int tg) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                if(board[cur.x][cur.y] == tg) {
                    return new Node(cur, cnt);
                }
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + DX[dir];
                    int ny = cur.y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    q.add(new Point(nx, ny));
                }

                for(int dir=0; dir<4; dir++) {
                    Point np = getCtrlMove(x, y, dir);
                    if(np.x == x && np.y == y) continue;
                    q.add(new Point(np.x, np.y));
                }
            }
            cnt++;
        }
        
        return null;
    }
    
    public Node getMinDisToPair(int x, int y) {
        int num = board[x][y];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        boolean[][] vis = new boolean[N][N];
        vis[x][y] = true;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                // System.out.println("cnt : " + cnt + ", cur : " + cur.x + ", " + cur.y);
                if(cnt > 0 && board[cur.x][cur.y] == num) {
                    return new Node(cur, cnt);
                }
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + DX[dir];
                    int ny = cur.y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    if(vis[nx][ny]) continue;
                    q.add(new Point(nx, ny));
                    vis[nx][ny] = true;
                }

                for(int dir=0; dir<4; dir++) {
                    Point np = getCtrlMove(cur.x, cur.y, dir);
                    if(np.x == x && np.y == y) continue;
                    if(vis[np.x][np.y]) continue;
                    q.add(new Point(np.x, np.y));
                    vis[np.x][np.y] = true;
                }
            }
            cnt++;
        }
        
        return null;
    }
    
//     [0, 0, 0, 1]
//     [0, 0, 0, 0]
//     [0, 1, 0, 0]
//     [0, 0, 0, 0]

//     0, 3
    
    public Point getCtrlMove(int x, int y, int dir) {
        for(int i=0; i<N; i++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) break;
            if(board[nx][ny] > 0) {
                return new Point(nx, ny);
            }
            x = nx;
            y = ny;
        }
        return new Point(x, y);
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<N && y<N;
    }
    
    public void init(int[][] board, int x, int y) {
        this.board = board;
        this.cur = new Point(x, y);
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(board[i][j] > 0) {
                    pairNum++;
                }
            }
        }
        pairNum /= 2;
        comb = new int[pairNum];
        answer = Integer.MAX_VALUE;
    }
}