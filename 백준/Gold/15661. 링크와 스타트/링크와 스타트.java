import java.io.*;
import java.util.*;

public class Main {

    int n;
    int[][] stats;
    boolean[] vis;
    int[] comb;
    int answer;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
        System.out.println(answer);
    }

    public void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            n = Integer.parseInt(br.readLine());
            stats = new int[n][n];
            for(int i=0; i<n; i++) {
                String[] tmp = br.readLine().split(" ");
                for(int j=0; j<n; j++) {
                    stats[i][j] = Integer.parseInt(tmp[j]);
                }
            }
            vis = new boolean[n];
            comb = new int[2];
            answer = Integer.MAX_VALUE;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }
    
    public void solve() {
        for(int i=1; i<=n/2; i++) {
            Arrays.fill(vis, false);
            dfs(i, 0, 0);
//            System.out.println("i : " + i +" answer : " + answer);
        }
    }

    public void dfs(int limit, int k, int lastIdx) {
        //vis[i] = true : 스타트 팀, false = 링크 팀
        if(k == limit) {
            answer = Math.min(answer, getTeamScoreDiff());
            return ;
        }
        for(int i=lastIdx; i<n; i++) {
            vis[i] = true;
            dfs(limit, k+1, i+1);
            vis[i] = false;
        }
    }

    public int getTeamScoreDiff() {
        List<Integer> startTeam = new ArrayList<>();
        List<Integer> linkTeam = new ArrayList<>();
        for(int i=0; i<n; i++) {
            if(vis[i]) startTeam.add(i);
            else linkTeam.add(i);
        }
        int startScore = getTeamScore(startTeam, 0, 0);
        int linkScore = getTeamScore(linkTeam, 0, 0);
//        System.out.println("startScore : " + startScore + " linkScore : " + linkScore);
        return Math.abs(startScore - linkScore);
    }

    public int getTeamScore(List<Integer> team, int k, int lastIdx) {
        if(k == 2) {
            int p1 = comb[0];
            int p2 = comb[1];
            return stats[p1][p2] + stats[p2][p1];
        }
        int sum = 0;
        for(int i=lastIdx; i<team.size(); i++) {
            comb[k] = team.get(i);
            sum += getTeamScore(team, k+1, i+1);
        }
        return sum;
    }


}