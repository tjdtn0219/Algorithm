import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 9;
    
    int n;
    int[][] scores;
    int[] comb;
    boolean[] vis;
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
            n = Integer.parseInt(br.readLine());
            scores = new int[n][2];
            comb = new int[MAX];
            vis = new boolean[MAX];
            vis[0] = true;
            comb[3] = 0;
            ans = 0;
            for(int i=0; i<n; i++) {
                scores[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {

        makeComb(0);
        System.out.println(ans);

        // comb = new int[]{4,5,6,0,1,2,3,7,8};
        // System.out.println(play());
    }

    public void makeComb(int k) {
        if(k == 3) {
            makeComb(k+1);
            return ;
        }

        if(k == MAX) {
            int score = play();
            ans = Math.max(ans, score);
            return ;
        }

        for(int i=0; i<MAX; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            comb[k] = i;
            makeComb(k+1);
            vis[i] = false;
        }
    }

    public int play() {
        int totalScore = 0;
        int idx = 0;
        for(int i=0; i<n; i++) {
            int[] loo = new int[4];
            while (true) {
                idx = idx % 9;
                int playerNum = comb[idx++];
                int res = scores[i][playerNum];
                int score = countScore(res, loo);
                // System.out.println((idx-1) + " : playNum : " + playerNum + ", res : " + res + ", score : " + score);
                if(score < 0) break;
                else totalScore += score;
            }
        }
        return totalScore;
    }

    public int countScore(int res, int[] loo) {
        int total = 0;
        if(res == 0) {
            //아웃
            loo[0]++;
            if(loo[0] >= 3) return -1;
        } else if(0<res && res<4) {
            //안타
            for(int i=3; i>0; i--) {
                if(loo[i] > 0 && i+res > 3) {
                    loo[i]--;
                    total++;
                } else if(loo[i] > 0 && i+res <= 3) {
                    loo[i]--;
                    loo[i+res]++;
                }
            }
            loo[res]++;

        } else {
            //홈런
            for(int i=1; i<=3; i++) {
                if(loo[i] > 0) {
                    total++;
                    loo[i] = 0;
                }
            }
            total++;
        }
        return total;
        
    }
}