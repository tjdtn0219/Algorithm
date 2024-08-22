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

    public void printVirusPoints() {
        for(Point point : virusPoints) {
            System.out.println("point : " + point.x + ", " + point.y);
        }
        System.out.println("========================");
    }

    public void solve() {
        // printVirusPoints();
        makeComb(0, virusPoints.size(), 0);
        // if(ans == Integer.MAX_VALUE) {
        //     System.out.println("-1");
        // } else {
        //     System.out.println(ans);
        // }
        if(emptyCnt == 0) {
            System.out.println(0);
        } else {
            // printComb();
            System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
        }
    }

    private void printComb() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            sb.append(comb[i]).append(" ");
        }
        System.out.println(sb);
    }

    private void makeComb(int k, int len, int lastIdx) {
        if(k == M) {
            // printComb();
            // int dis = spreadVirus();
            // System.out.println("dis : " + dis);
            // ans = Math.min(ans, dis);
            spreadVirus2(emptyCnt);
            return ;
        }

        for(int i=lastIdx; i<len; i++) {
            comb[k] = i;
            makeComb(k+1, len, i+1);
        }
    }

    private void spreadVirus2(int emptyCnt) {
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

    private int spreadVirus() {
        int[][] tmpMap = new int[N][N];
        int[][] disMap = new int[N][N];
        for(int i=0; i<N; i++) {
            tmpMap[i] = map[i].clone();
            Arrays.fill(disMap[i], -1);
        }
        Queue<Node> q = new LinkedList<>();
        for(int i=0; i<M; i++) {
            Point virusPoint = virusPoints.get(comb[i]);
            // System.out.println("virusPoint : " + virusPoint.x + ", " + virusPoint.y);
            q.add(new Node(virusPoint, 0));
            disMap[virusPoint.x][virusPoint.y] = 0;
        }

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Node cur = q.poll();
                int x = cur.point.x;
                int y = cur.point.y;
                int dis = cur.dis;
                for(int dir=0; dir<4; dir++) {
                    int nx = x + DX[dir];
                    int ny = y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    if(tmpMap[nx][ny] == 1 || disMap[nx][ny] > -1) continue;
                    if(tmpMap[nx][ny] == 2) {
                        disMap[nx][ny] = dis;
                        q.add(new Node(new Point(nx, ny), dis));
                    } else {
                        disMap[nx][ny] = dis + 1;
                        q.add(new Node(new Point(nx, ny), dis + 1));
                    }
                }
            }
        }
        // printArr(disMap);
        return getMinDis(tmpMap, disMap);
    }

    private int getMinDis(int[][] tmpMap, int[][] disMap) {
        int max = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(disMap[i][j] == -1 && tmpMap[i][j] == 0) {
                    return Integer.MAX_VALUE;
                }
                if(disMap[i][j] > -1) {
                    max = Math.max(max, disMap[i][j]);
                }
            }
        }
        return max;
    }

    private void printArr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<N && y<N;
    }

}

class Node {
    Point point;
    int dis;
    public Node(Point point, int dis) {
        this.point = point;
        this.dis = dis;
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

class Virus {
    int x, y, time;

    Virus(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}