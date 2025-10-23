import java.awt.Point;
import java.util.*;

class Node {
    Point p;
    int diff;
    public Node(Point p, int diff) {
        this.p = p;
        this.diff = diff;
    }
    @Override
    public String toString() {
        return p + ", diff : " + diff;
    }
}

class Solution {
    
    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,-1,0,1};
    
    int answer;
    int n;
    int[][] board;
    int limit;
    
    public int solution(int[][] land, int height) {
        init(land, height);
        System.out.println("limit : " + limit);
        solve();
        return answer;
    }
    
    public void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.diff - o2.diff);
        pq.add(new Node(new Point(0, 0), 0));
        boolean[][] vis = new boolean[n][n];
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            // System.out.println("cur : " + cur);
            Point p = cur.p;
            if(vis[p.x][p.y]) continue;
            answer += cur.diff;
            vis[p.x][p.y] = true;
            for(int dir=0; dir<4; dir++) {
                int nx = p.x + DX[dir];
                int ny = p.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                int diff = Math.abs(board[nx][ny] - board[p.x][p.y]);
                // System.out.println("diff : " + diff);
                if(diff <= limit) {
                    pq.add(new Node(new Point(nx, ny), 0));
                }
                else {
                    pq.add(new Node(new Point(nx, ny), diff));
                }
            }
        }
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }
    
    public void init(int[][] land, int height) {
        this.board = land;
        this.limit = height;
        this.n = land.length;
    }
    
}