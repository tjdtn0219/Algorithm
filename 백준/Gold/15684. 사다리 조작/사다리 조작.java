import java.io.*;
import java.util.*;

public class Main {

    int N, M, H;
    List<Line> lines;
    boolean[][] board;
    int[] comb;
    int ans;

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
            H = Integer.parseInt(tmp[2]);
            lines = new ArrayList<>();
            for(int i=0; i<M; i++) {
                tmp = br.readLine().split(" ");
                int idx = Integer.parseInt(tmp[0]);
                int n = Integer.parseInt(tmp[1]);
                lines.add(new Line(idx, n));
            }
            comb = new int[3];
            ans = 4;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        initBoard();
//        doGame();
        dfs(0, N*H, 0, 0);
        if(ans == 4) ans = -1;
        System.out.println(ans);
    }

    public void initBoard() {
        board = new boolean[H+3][N+2];
        for(Line line : lines) {
            board[line.h][line.n] = true;
        }
    }

    public void dfs(int k, int max, int cnt, int last) {
        if(k >= 3) {
//            printComb();
            if(doGame()) ans = Math.min(ans, cnt);
            return ;
        }

        for(int i=last; i<max; i++) {
            if(i == 0) {
                comb[k] = i;
                dfs(k+1, max, cnt, i);
            }
            Line line = numToLine(i);
            if(line == null) continue;
            if(board[line.h][line.n]) continue;
            comb[k] = i;
            board[line.h][line.n] = true;
            dfs(k+1, max, cnt+1, i);
            board[line.h][line.n] = false;
        }
    }

    public Line numToLine(int num) {
        int h = num / N + 1;
        int n = num % N;
        if(n == 0) return null;
        return new Line(h, n);
    }

    public boolean doGame() {
        for(int i=1; i<=N; i++) {
            if(!climbLadder(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean climbLadder(int i) {
        int tmp = i;
//        System.out.print(i + " -> ");
        for(int h=1; h<=H; h++) {
            if(board[h][i-1]) {
                i--;
            } else if(board[h][i]) {
                i++;
            }
        }
//        System.out.println(i);
        if(tmp == i) return true;
        return false;
    }

    private void printComb() {
        StringBuilder sb = new StringBuilder();
        for(int num : comb) {
            sb.append(num + " ");
        }
        System.out.println(sb);
    }

}

class Line {
    int h;
    int n;
    public Line(int h, int n) {
        this.h = h;
        this.n = n;
    }
}