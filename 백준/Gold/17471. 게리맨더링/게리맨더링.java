import java.io.*;
import java.util.*;

public class Main{

    int N;
    int[] people;
    List<List<Integer>> graph;
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
            N = Integer.parseInt(br.readLine());
            people = new int[N+1];
            String[] s = br.readLine().split(" ");
            for(int i=1; i<=N; i++) {
                people[i] = Integer.parseInt(s[i-1]);
            }
            graph = new ArrayList<>();
            for(int i=0; i<=N; i++) {
                graph.add(new ArrayList<>());
            }
            for(int i=1; i<=N; i++) {
                int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int n = tmp[0];
                for(int j=1; j<=n; j++) {
                    graph.get(i).add(tmp[j]);
                }
            }
            ans = Integer.MAX_VALUE;
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        makeTeam();
    }

    public void makeTeam() {
        for(int i=1; i<=N/2; i++) {
            // int[] comb1 = new int[i];
            // int[] comb2 = new int[N-i];
            boolean[] teams = new boolean[N+1];
            dfs(teams, i, 0, 1);
        }
        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    public void dfs(boolean[] teams, int max, int k, int li) {
        if(k == max) {
            // printTeam(teams);
            int idx1 = 1;
            int idx2 = getIdxOtherTeam(teams, idx1);
            if (idx2 != idx1) { 
                // 모두가 같은 팀이 아닐 경우
                int teamCnt1 = getTeamCnt(teams, idx1)[0];
                int teamCnt2 = getTeamCnt(teams, idx2)[0];
                // System.out.println("teamCnt1 : " + teamCnt1 + ", teamCnt2 : " + teamCnt2);
                if (teamCnt1 + teamCnt2 == N)  {
                    // 팀끼리는 모두 연결되어 있는 경우
                    // printTeam(teams);
                    int peopleCnt1 = getTeamCnt(teams, idx1)[1];
                    int peopleCnt2 = getTeamCnt(teams, idx2)[1];
                    // System.out.println("peopleCnt1 : " + peopleCnt1 + ", peopleCnt2 : " + peopleCnt2);
                    ans = Math.min(ans, Math.abs(peopleCnt1 - peopleCnt2));
                }
            }
            return ;
        }

        for(int i=li; i<=N; i++) {
            teams[i] = true;
            dfs(teams, max, k+1, i+1);
            teams[i] = false;
        }
    }

    // public boolean isAllInOneTeam(boolean[] teams) {
    //     boolean flag = teams[1];
    //     int cnt = 0;
    //     for (int i=1; i<=N; i++) {
    //         if(teams[i] == flag) cnt++;
    //     }
    //     if (cnt == 0 || cnt == N) return true;
    //     else return false;
    // }

    public int getIdxOtherTeam(boolean[] teams, int idx) {
        boolean flag = teams[idx];
        for (int i=1; i<=N; i++) {
            if(flag != teams[i]) return i;
        }
        return idx;
    }

    public int[] getTeamCnt(boolean[] teams, int idx) {
        boolean[] vis = new boolean[N+1];
        boolean flag = teams[idx];
        Queue<Integer> q = new LinkedList<>();
        q.add(idx);
        vis[idx] = true;

        int cnt = 1;
        int peopleCnt = people[idx];

        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int nxt : graph.get(cur)) {
                if(teams[nxt] != flag || vis[nxt]) continue;
                vis[nxt] = true;
                q.add(nxt);
                cnt++;
                peopleCnt += people[nxt];
            }
        }

        int[] res = new int[2];
        res[0] = cnt;
        res[1] = peopleCnt;

        return res;
    }

    public void dfs(int[] comb, int max, int k, int li) {
        if(k == max) {
            // printComb(comb);
            return ;
        }

        for(int i=li; i<=N; i++) {
            comb[k] = i;
            dfs(comb, max, k+1, i+1);
        }
    }

    public void printComb(int[] comb) {
        StringBuilder sb = new StringBuilder();
        for(int num : comb) {
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }

    public void printTeam(boolean[] teams) {
        StringBuilder sb = new StringBuilder();
        for(boolean bool : teams) {
            if(bool) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.println(sb);
    }

}