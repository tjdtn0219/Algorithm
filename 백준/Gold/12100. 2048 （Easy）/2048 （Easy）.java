import java.io.*;

public class Main {

    static final int MAX_CNT = 5;
    static final int[] DX = {1,0,-1,0}; //남 동 북 서
    static final int[] DY = {0,1,0,-1};

    int n;
    int[][] originBoard;
    int[][] board;
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
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];
            originBoard = new int[n][n];
            comb = new int[MAX_CNT];
            answer = -1;
            for(int i=0; i<n; i++) {
                String[] tmp = br.readLine().split(" ");
                for(int j=0; j<n; j++) {
                    originBoard[i][j] = Integer.parseInt(tmp[j]);
                }
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!");
            throw new RuntimeException(e);
        }

    }

    public void solve() {
        //남 동 북 서
        dfs(0);
        System.out.println(answer);
//        printBoard(board);
//        System.out.println("=============");
//        printBoard(originBoard);
    }

    public void printBoard(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void dfs(int k) {
        if(k == MAX_CNT) {
            cloneArrDeeply(board);
            move();
            answer = Math.max(answer, getMaxInBoard());
            return ;
        }

        for(int dir=0; dir<4; dir++) {
            comb[k] = dir;
            dfs(k+1);
        }
    }

    public void cloneArrDeeply(int[][] board) {
        for(int i=0; i<n; i++) {
            board[i] = originBoard[i].clone();
        }
    }

    public int getMaxInBoard() {
        int max = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                max = Math.max(max, board[i][j]);
            }
        }
        return max;
    }

    public void move() {
        for(int dir : comb) {
            moveAllBlocks(dir);
        }
    }

    public void moveAllBlocks(int dir) {
        boolean[][] isMerged = new boolean[n][n];
        if(dir==0 || dir==1) {
            //남 동
            for (int i = n - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    moveOneBlock(dir, i, j, isMerged);
                }
            }
        } else {
            //북 서
            for (int i = 0; i <n; i++) {
                for (int j = 0; j < n; j++) {
                    moveOneBlock(dir, i, j, isMerged);
                }
            }
        }
    }

    public void moveOneBlock(int dir, int x, int y, boolean[][] isMerged) {
        int tmpX = x;
        int tmpY = y;
        boolean flag = false;
        while(true) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            if(!isInner(nx, ny)) break;
            if(board[nx][ny] != 0) {
                if(!isMerged[nx][ny] && board[nx][ny] == board[tmpX][tmpY]) {
                    flag = true;
                    x = nx; y = ny;
                }
                break;
            }
            x = nx;
            y = ny;
        }
        if(flag) {
            board[x][y] += board[tmpX][tmpY];
            isMerged[x][y] = true;
            board[tmpX][tmpY] = 0;
        } else {
            int tmp = board[tmpX][tmpY];
            board[tmpX][tmpY] = 0;
            board[x][y] = tmp;
        }
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
    }
}
