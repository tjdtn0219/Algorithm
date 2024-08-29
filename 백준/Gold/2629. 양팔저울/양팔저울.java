import java.io.*;
import java.util.Arrays;

public class Main{

    static final int MAX = 40_005;

    int n;
    int[] weights;
    int m;
    int[] inputs;
    boolean[][] vis;

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
            weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            m = Integer.parseInt(br.readLine());
            inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            vis = new boolean[n+1][MAX];
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        dfs(0, 0);
        StringBuilder sb = new StringBuilder();
        for(int input : inputs) {
            if(vis[n][input]) sb.append("Y").append(" ");
            else sb.append("N").append(" ");
        }
        System.out.println(sb);
    }

    public void dfs(int idx, int weight) {
        if(vis[idx][weight]) return ;
        vis[idx][weight] = true;
        if(idx == n) return ;

        dfs(idx+1, weight);  //다음 추 올려놓기
        dfs(idx+1, weight + weights[idx]);
        dfs(idx+1, Math.abs(weight - weights[idx]));

    }

}