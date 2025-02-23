import java.util.*;

class Solution {
    
    static final int[] DX = {0,1,0,-1}; //동 남 서 북
    static final int[] DY = {1,0,-1,0};
    static final int N = 5;
    
    int[][] board;
    boolean[][] vis;
    List<Point> people;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[N];
        for(int i=0; i<places.length; i++) {
            answer[i] = solve(places[i]);
        }
        return answer;
    }
    
    public int solve(String[] place) {
        init(place);
        return isKeepDistance();
    }
    
    public void init(String[] place) {
        board = new int[N][N];
        people = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(place[i].charAt(j) == 'P') {
                    board[i][j] = 1;
                    people.add(new Point(i, j));
                }
                else if(place[i].charAt(j) == 'X') {
                    board[i][j] = -1;
                }
            }
        }
    }
    
    public int isKeepDistance() {
        for(Point person : people) {
            if(isExistSurround(person)) {
                return 0;
            }
        }
        return 1;
    }
    
    public boolean isExistSurround(Point person) {
        vis = new boolean[N][N];
        Queue<Point> q = new LinkedList<>();
        q.add(person);
        vis[person.x][person.y] = true;
        int depth = 0;
        while(!q.isEmpty() && depth<2) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Point cur = q.poll();
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + DX[dir];
                    int ny = cur.y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    if(vis[nx][ny]) continue;
                    if(board[nx][ny] == -1) continue;
                    if(board[nx][ny] == 1) return true;
                    q.add(new Point(nx, ny));
                    vis[nx][ny] = true;
                }
            }
            depth++;
        }
        return false;
    }
    
    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<N && y<N;
    }
    
}

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}