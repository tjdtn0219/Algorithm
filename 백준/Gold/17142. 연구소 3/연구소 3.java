import java.io.*;
import java.util.*;

public class Main{

    static final int[] DX = {1,0,-1,0};
    static final int[] DY = {0,1,0,-1};

    int N;
    int M;
    int[][] map;
    List<Point> virusPoints;
    int[] comb;
    int ans;
    int emptyCnt;

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
            String[] N_M = br.readLine().split(" ");
            N = Integer.parseInt(N_M[0]);
            M = Integer.parseInt(N_M[1]);
            map = new int[N][N];
            emptyCnt = 0;
            virusPoints = new ArrayList<>();
            for (int i=0; i<N; i++) {
                String[] tmp = br.readLine().split(" ");
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(tmp[j]);
                    if(map[i][j] == 2) {
                        virusPoints.add(new Point(i, j));
                    }
                    if(map[i][j] == 0) {
                        emptyCnt++;
                    }
                }
            }
            comb = new int[M];
            ans = Integer.MAX_VALUE;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        makeComb(0, virusPoints.size(), 0);
        if(emptyCnt == 0) {
            System.out.println(0);
        } else {
            System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
        }
    }

    private void makeComb(int k, int len, int lastIdx) {
        if(k == M) {
            spreadVirus(emptyCnt);
            return ;
        }

        for(int i=lastIdx; i<len; i++) {
            comb[k] = i;
            makeComb(k+1, len, i+1);
        }
    }

    private void spreadVirus(int emptyCnt) {
        Queue<Virus> q = new LinkedList<>();
        boolean[][] infected = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            Point virusPoint = virusPoints.get(comb[i]);
            Virus virus = new Virus(virusPoint.x, virusPoint.y, 0);
            infected[virus.x][virus.y] = true;
            q.add(virus);
        }

        while (!q.isEmpty()) {
            Virus virus = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = virus.x + DX[dir];
                int ny = virus.y + DY[dir];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (infected[nx][ny] || map[nx][ny] == 1) continue;

                if (map[nx][ny] == 0) {
                    emptyCnt--;
                }

                if (emptyCnt == 0) {
                    ans = Math.min(ans, virus.time + 1);
                    return;
                }

                infected[nx][ny] = true;
                q.add(new Virus(nx, ny, virus.time + 1));
            }
        }
    }

}

class Point {
    int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Virus {
    int x, y, time;

    Virus(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}