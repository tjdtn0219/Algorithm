import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {0, 0, -1, 1};
    static final int[] DY = {1, -1, 0, 0};

    int n, m;
    int cx, cy;
    int k;
    int[][] map;
    int[] moves;
    int[] dice1;
    int[] dice2;

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
            m = Integer.parseInt(tmp[1]);
            cx = Integer.parseInt(tmp[2]);
            cy = Integer.parseInt(tmp[3]);
            k = Integer.parseInt(tmp[4]);
            map = new int[n][m];
            for(int i=0; i<n; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            moves = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dice1 = new int[4];
            dice2 = new int[4];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        for (int dir : moves) {
            int nx = cx + DX[dir-1];
            int ny = cy + DY[dir-1];
            // System.out.println("nx , ny : " + nx + ", " + ny);
            if(!isInner(nx, ny)) continue;
            cx = nx;
            cy = ny;
            move(dir);
            if(map[cx][cy] == 0) {
                map[cx][cy] = dice1[0];
            } else {
                dice1[0] = map[cx][cy];
                dice2[3] = map[cx][cy];
                map[cx][cy] = 0;
            }
            System.out.println(dice1[2]);
        }

    }

    public void move(int dir) {
        if(dir == 1 || dir == 2) {
            moveRightOrLeft(dir);
        } else {
            moveUpOrDown(dir);
        }
    }

    public void moveRightOrLeft(int dir) {
        int[] tmp = new int[4];
        if(dir == 1) {  //동쪽
            // System.out.println("move Right");
            for(int i=0; i<4; i++) {
                tmp[(i+1)%4] = dice1[i];
            }
            dice2[1] = dice1[1];
            dice2[3] = dice1[3];
            dice1 = tmp;
        } else if(dir==2) {    //서쪽
            // System.out.println("move Left");
            for(int i=0; i<4; i++) {
                tmp[(3+i)%4] = dice1[i];
            }
            dice2[1] = dice1[3];
            dice2[3] = dice1[1];
            dice1 = tmp;
        }
    }

    public void moveUpOrDown(int dir) {
        int[] tmp = new int[4];
        if(dir == 3) {  //북쪽
            // System.out.println("move Up");
            for(int i=0; i<4; i++) {
                tmp[(i+1)%4] = dice2[i];
            }
            dice1[0] = dice2[2];
            dice1[2] = dice2[0];
            dice2 = tmp;
        } else if(dir == 4) {   //남쪽
            // System.out.println("move Down");
            for(int i=0; i<4; i++) {
                tmp[(i+3)%4] = dice2[i];
            }
            dice1[0] = dice2[0];
            dice1[2] = dice2[2];
            dice2 = tmp;
        }
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<n && y<m;
    }
}
