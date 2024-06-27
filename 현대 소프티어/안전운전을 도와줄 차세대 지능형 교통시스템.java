//문제 링크 : https://softeer.ai/practice/6274
import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {0,-1,0,1};     //동, 북, 서, 남
    static final int[] DY = {1,0,-1,0};

    int N, T;
    int[][][] trafficArr;
    HashMap<Integer, int[]> sigMap;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        init();
        input();
        solve();
    }

    public void init() {
        sigMap = new HashMap<>();
        for(int sig=1; sig<13; sig++) {
            int[] dirs = signalToDirs(sig);
            sigMap.put(sig, dirs);
        }
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] tmp = br.readLine().split(" ");
            N = Integer.parseInt(tmp[0]);
            T = Integer.parseInt(tmp[1]);
            trafficArr = new int[N][N][4];
            for(int i=0; i<N*N; i++) {
                int r = i / N;
                int c = i % N;
                tmp = br.readLine().split(" ");
                for(int k=0; k<4; k++) {
                    trafficArr[r][c][k] = Integer.parseInt(tmp[k]);
                }
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int curT = 0;
        Queue<Car> q = new LinkedList<>();
        q.add(new Car(0, 0, 1));

        boolean[][] vis = new boolean[N][N];
        vis[0][0] = true;
        //동 북 서 남
        while(!q.isEmpty() && curT <= T) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Car cur = q.poll();
                int x = cur.x;
                int y = cur.y;
                int curDir = cur.dir;
                vis[x][y] = true;
                int t = curT % 4;
                int sig = trafficArr[x][y][t];
                int[] dirs = sigMap.getOrDefault(sig, new int[0]);
                if(dirs[0] != curDir) continue;
                for(int dir : dirs) {
                    int nx = x + DX[dir];
                    int ny = y + DY[dir];
                    if(!isInner(nx, ny)) continue;
                    q.add(new Car(nx, ny, dir));
                }
            }
            curT++;
        }

        System.out.println(getVisCnt(vis));

    }

    public int getVisCnt(boolean[][] vis) {
        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(vis[i][j]) cnt++;
            }
        }
        return cnt;
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<N && y<N;
    }

    public int[] signalToDirs(int sig) {
        List<Integer> dirs = new ArrayList<>();
        if(1<= sig && sig<=4) {
            int dir = sig - 1;
            dirs.add(dir);
            dirs.add(getLeftDir(dir));
            dirs.add(getRightDir(dir));
        } else if(5<=sig && sig<=8) {
            int dir = sig - 5;
            dirs.add(dir);
            dirs.add(getLeftDir(dir));
        } else {    //9~12
            int dir = sig - 9;
            dirs.add(dir);
            dirs.add(getRightDir(dir));
        }
        return dirs.stream().mapToInt(Integer::intValue).toArray();
    }

    public int getLeftDir(int dir) {
        //동 북 서 남
        return (dir+1) % 4;
    }

    public int getRightDir(int dir) {
        return (dir+3) % 4;
    }

}

class Car {
    int x;
    int y;
    int dir;
    public Car(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
