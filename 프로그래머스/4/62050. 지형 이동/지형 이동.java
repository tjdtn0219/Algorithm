import java.awt.Point;
import java.util.*;

class Node {
    Point p;
    int gap;
    public Node(Point p, int gap) {
        this.p = p;
        this.gap = gap;
    }
}

class Solution {
    
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,-1,0,1};
    
    int[][] board;
    int n;
    int limit;
    int answer;
    
    public int solution(int[][] land, int height) {
        init(land, height);
        solve();
        return answer;
    }
    
    public void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.gap - o2.gap);
        boolean[][] vis = new boolean[n][n];
        pq.add(new Node(new Point(0, 0), 0));
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            Point p = cur.p;
            if(vis[p.x][p.y]) continue;
            answer += cur.gap;
            vis[p.x][p.y] = true;
            for(int dir=0; dir<4; dir++) {
                int nx = p.x + DX[dir];
                int ny = p.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                int gap = Math.abs(board[nx][ny] - board[p.x][p.y]);
                if(gap <= limit) {
                    pq.add(new Node(new Point(nx, ny), 0));
                } else {
                    pq.add(new Node(new Point(nx, ny), gap));
                }
            }
        }
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }
    
    public void init(int[][] land, int height) {
        this.n = land.length;
        this.board = land;
        this.limit = height;
    }
    
}