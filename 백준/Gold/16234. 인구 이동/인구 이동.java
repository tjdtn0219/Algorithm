import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {0,1,0,-1}; //동 남 서 북
    static final int[] DY = {1,0,-1,0};

    int n;
    int l;
    int r;
    int[][] map;
    int[][] team;
    boolean isMoveFlag;

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
            n = Integer.parseInt(tmp[0]);
            l = Integer.parseInt(tmp[1]);
            r = Integer.parseInt(tmp[2]);
            map = new int[n][n];
            for(int i=0; i<n; i++) {
                tmp = br.readLine().split(" ");
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            team = new int[n][n];
            isMoveFlag = false;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int answer = 0;
        while(true) {
            team = new int[n][n];
            isMoveFlag = false;
            openLine();
            if(!isMoveFlag) break;
            answer++;
//            printTeam();
        }
        System.out.println(answer);

    }

    public void printTeamMap(HashMap<Integer, Integer> teamMap) {
        for(int key : teamMap.keySet()) {
            System.out.println("key : " + key + ", val : " + teamMap.get(key));
        }
    }

    public void printTeam() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sb.append(team[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void openLine() {
        boolean[][] vis = new boolean[n][n];
        HashMap<Integer, Integer> teamMap = new HashMap<>();
        int teamNum = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(vis[i][j]) continue;
                int avg = bfs(i, j, teamNum, vis);
//                System.out.println("Avg : " + avg);
                teamMap.put(teamNum++, avg);
            }
        }
        distributePopulation(teamMap);
//        printTeamMap(teamMap);
    }

    public void distributePopulation(HashMap<Integer, Integer> teamMap) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int teamNum = team[i][j];
                map[i][j] = teamMap.get(teamNum);
            }
        }
    }


    public int bfs(int x, int y, int teamNum, boolean[][] vis) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        team[x][y] = teamNum;
        vis[x][y] = true;
        int sum = map[x][y];
        int cnt = 1;
        while(!q.isEmpty()) {
            Point cur = q.poll();
            for(int dir=0; dir<4; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(!isInner(nx, ny)) continue;
                if(vis[nx][ny]) continue;
                if(!isOpen(cur.x, cur.y, nx, ny)) continue;
                q.add(new Point(nx, ny));
                team[nx][ny] = teamNum;
                vis[nx][ny] = true;
                isMoveFlag = true;
                sum += map[nx][ny];
                cnt++;
            }
        }
        int avg = sum / cnt;
        return avg;
    }

    public boolean isOpen(int x1, int y1, int x2, int y2) {
        int diff = Math.abs(map[x1][y1] - map[x2][y2]);
        if(l <= diff && diff <= r) {
            return true;
        }
        return false;
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<n;
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

class TeamInfo {
    int sum;
    int cnt;
    public TeamInfo(int sum, int cnt) {
        this.sum = sum;
        this.cnt = cnt;
    }
}