import java.io.*;
import java.util.*;

public class Main {


    int n, l;
    int[][] map;

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
            map = new int[n][n];
            for(int i=0; i<n; i++) {
                tmp = br.readLine().split(" ");
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(tmp[j]);
                }
            }
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        int ans = 0;
        for(int i=0; i<n; i++) {
            int[] road = new int[n];
            boolean[] isHill = new boolean[n];
            for(int j=0; j<n; j++) {
                road[j] = map[i][j];
            }
            if(isRoad(road, isHill)) {
                ans++;
            }
        }

        for(int j=0; j<n; j++) {
            int[] road = new int[n];
            boolean[] isHill = new boolean[n];
            for(int i=0; i<n; i++) {
                road[i] = map[i][j];
            }
            if(isRoad(road, isHill)) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    public boolean isRoad(int[] road, boolean[] isHill) {
        int cur = road[0];
        for(int i=1; i<road.length; i++) {
            int nxt = road[i];
            if(cur == nxt) continue;
            else if(nxt < cur-1 || cur+1 < nxt) {
                return false;
            } else {
                if(cur < nxt) {
                    if(!makeUpHill(road, isHill, i)) return false;
                } else {
                    if(!makeDownHill(road, isHill, i-1)) return false;
                }
                cur = nxt;
            }
        }
        return true;
    }

    public boolean makeUpHill(int[] road, boolean[] isHill, int stdIdx) {
        int floor = road[stdIdx] - 1;
        if(!isInner(stdIdx - l)) {
            return false;
        }

        for(int i=stdIdx-l; i<stdIdx; i++) {
            if(isHill[i]) return false;
            if(road[i] != floor) return false;
        }
        for(int i=stdIdx-l; i<stdIdx; i++) {
            isHill[i] = true;
        }
        return true;
    }

    public boolean makeDownHill(int[] road, boolean[] isHill, int stdIdx) {
        int floor = road[stdIdx] - 1;
        if(!isInner(stdIdx + l)) {
            return false;
        }

        for(int i=stdIdx+1; i<=stdIdx+l; i++) {
            if(isHill[i]) return false;
            if(road[i] != floor) return false;
        }
        for(int i=stdIdx+1; i<=stdIdx+l; i++) {
            isHill[i] = true;
        }
        return true;
    }

    public boolean isInner(int x) {
        return 0<=x && x<n;
    }

}