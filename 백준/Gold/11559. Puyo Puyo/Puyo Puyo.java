import java.io.*;
import java.util.*;

public class Main {

    public static final int N = 12;
    public static final int M = 6;
    public static final int[] dx = {1,0,-1,0};
    public static final int[] dy = {0,1,0,-1};

    char[][] board;
    int answer;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();

//        bomb();
//        makePuyoFall();
        solve();
//        print();
        System.out.println(answer);
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[N][M];
        answer = 0;
        try {
            for(int i=0; i<N; i++) {
                String str = br.readLine();
                for(int j=0; j<M; j++) {
                    board[i][j] = str.charAt(j);
                }
            }
        } catch(IOException e) {
            System.out.println("INPUT ERROR!");
        }
    }

    public void solve() {
        while(true) {
            if(bomb()) answer++;
            else break;
            makePuyoFall();
        }
    }

    public boolean bomb() {
        boolean flag = false;
        boolean[][] vis = new boolean[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(board[i][j] == '.') continue;
                if(vis[i][j]) continue;
                int cnt = getCnt(vis, i, j, board[i][j]);
                if(cnt >= 4) {
                    makeBomb(i, j, board[i][j]);
                    flag = true;
                }
            }
        }
        return flag;
    }

    public int getCnt(boolean[][] vis, int i, int j, char color) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        int cnt = 1;
        vis[i][j] = true;
        while(!q.isEmpty()) {
            Pair polled = q.poll();
            int x = polled.x;
            int y = polled.y;
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if(board[nx][ny] != color || vis[nx][ny]) continue;
//                board[i][j] = '.';
                q.add(new Pair(nx, ny));
                vis[nx][ny] = true;
                cnt++;
            }
        }
        return cnt;
    }

    public void makeBomb(int i, int j, char color) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        board[i][j] = '.';
        while(!q.isEmpty()) {
            Pair polled = q.poll();
            int x = polled.x;
            int y = polled.y;
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if(board[nx][ny] != color) continue;
                q.add(new Pair(nx, ny));
                board[nx][ny] = '.';
            }
        }
    }

    public void makePuyoFall() {
        for(int i=N-2; i>=0; i--) {
            for(int j=0; j<M; j++) {
                if(board[i][j] == '.') continue;
                fall(i, j);
            }
        }
    }

    public void fall(int i, int j) {
        int x = i+1;
        while(x<N) {
            if(board[x][j] != '.') break;
            x++;
        }
        swap(i, j, x-1, j);
    }

    public void swap(int x1, int y1, int x2, int y2) {
        char tmp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = tmp;
    }

    public void print() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}

class Pair {
    int x;
    int y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}