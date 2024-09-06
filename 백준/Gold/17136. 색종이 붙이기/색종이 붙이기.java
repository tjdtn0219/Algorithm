import java.io.*;
import java.util.*;

public class Main {

    static final int LEN = 10;

    int[][] map;
    int ans;
    int[] cnt;

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
            map = new int[LEN][LEN];
            for(int i=0; i<LEN; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            ans = Integer.MAX_VALUE;
            cnt = new int[6];
            Arrays.fill(cnt, 5);
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        dfs(0, 0, 0);
        if(ans == Integer.MAX_VALUE) {
            ans = -1;
        }

        System.out.println(ans);
    }

    public void dfs(int x, int y, int k) {
        if(x>=9 && y>9) {
            ans = Math.min(ans, k);
            return ;
        }

        if(ans <= k) {
            return ;
        }

        if(!isInner(x, y)) {
            //아래줄로 이동
            dfs(x+1, 0, k);
            return ;
        }

        if(map[x][y] == 1) {
            for(int len=5; len>=1; len--) {
                if(cnt[len] > 0 && isPaste(x, y, len)) {
                    paste(x, y, len, 0);    //색종이 붙임
                    cnt[len]--;
                    dfs(x, y+len, k+1);
                    paste(x, y, len, 1);    //색종이 다시 뗌
                    cnt[len]++;
                }
            }
        } else {
            dfs(x, y+1, k);
        }


    }

    public void paste(int x, int y, int len, int state) {
        for(int i=x; i<x+len; i++) {
            for(int j=y; j<y+len; j++) {
                map[i][j] = state;
            }
        }
    }

    public boolean isPaste(int x, int y, int len) {
        if(!isInner(x+len-1, y+len-1)) return false;
        for(int i=x; i<x+len; i++) {
            for(int j=y; j<y+len; j++) {
                if(map[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean isInner(int x, int y) {
        return 0<=x && 0<=y && x<LEN && y<LEN;
    }

}