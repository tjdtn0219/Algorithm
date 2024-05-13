import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,-1,0,1};
    static final int WALL = -1;
    static final int MAX = 3_000;

    int N, M;
    int[][] originBoard;
    int[][] board;
    List<Integer> startList;
    int[] comb;
    int answer;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = br.readLine().split(" ");
            N = Integer.parseInt(tmp[0]);
            M = Integer.parseInt(tmp[1]);
            originBoard = new int[N][N];
            board = new int[N][N];
            startList = new ArrayList<>();
            comb = new int[M];
            for(int i=0; i<N; i++) {
                tmp = br.readLine().split(" ");
                for(int j=0; j<N; j++) {
                    int val = Integer.parseInt(tmp[j]);
                    if(val == 1) {
                        originBoard[i][j] = WALL;
                    } else if(val == 2) {
                        startList.add(pointToNum(i, j));
                        originBoard[i][j] = MAX;
                    } else {
                        originBoard[i][j] = MAX;
                    }
                }
            }
            answer = MAX;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }
    }

    public int pointToNum(int i, int j) {
        return i * N + j;
    }

    public void solve() {
        makeComb(0, 0);
        if(answer == MAX) answer = -1;
        System.out.println(answer);
    }

    public void makeComb(int k, int last) {
        if(k == M) {
            initBoard();
            spreadVirus();
            answer = Math.min(answer, findMaxVal());
            return ;
        }

        for(int i=last; i<startList.size(); i++) {
            int num = startList.get(i);
            comb[k] = num;
            makeComb(k+1, i+1);
        }
    }

    public void spreadVirus() {
        List<Point> startPoints = new ArrayList<>();
        for(int val : comb) {
            startPoints.add(valToPoint(val));
        }
        for(Point startPoint : startPoints) {
            bfs(startPoint);
        }
//        printInfo();
    }

    public int findMaxVal() {
        int max = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
        return max;
    }

    public Point valToPoint(int val) {
        return new Point(val / N, val % N);
    }

    public void bfs(Point start) {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        board[start.x][start.y] = 0;

        while(!q.isEmpty()) {
            for(int i=0; i<q.size(); i++) {
                Point cur = q.poll();
                for(int dir=0; dir<4; dir++) {
                    int nx = cur.x + DX[dir];
                    int ny = cur.y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    if(board[nx][ny] == WALL) continue;
                    if(board[cur.x][cur.y]+1 < board[nx][ny]) {
                        q.add(new Point(nx, ny));
                        board[nx][ny] = board[cur.x][cur.y] + 1;
                    }
                }
            }
        }
    }

    public void initBoard() {
        for(int i=0; i<N; i++) {
            board[i] = originBoard[i].clone();
        }
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<N && y<N;
    }

    private void printInfo() {
        for(int num : comb) {
            System.out.print(num + " ");
        }
        System.out.println("\n--------------------");
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=================\n");
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