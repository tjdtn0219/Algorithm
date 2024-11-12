import java.io.*;

public class Main {

    int n, k, s;
    int[][] arr;
    int[][] inArr;

    public static void main(String[] args) {
        new Main().solution();
    }

    public void solution() {
        input();
        solve();
    }

    public void solve() {
        for(int p=1; p<=n; p++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(arr[i][p]==1 && arr[p][j] == 1) {
                        arr[i][j] = 1;
                    }
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int[] in : inArr) {
            int a = in[0];
            int b = in[1];
            if(a < 1 || b < 1 || a > n || b > n) {
                sb.append("0\n");
                continue;
            }
            if(arr[a][b] == 1) {
                sb.append(-1).append("\n");
            } else if(arr[b][a] == 1) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb);
    }

    public void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] n_k = br.readLine().split(" ");
            n = Integer.parseInt(n_k[0]);
            k = Integer.parseInt(n_k[1]);
            arr = new int[n+5][n+5];
            for(int i=0; i<k; i++) {
                String[] a_b = br.readLine().split(" ");
                int a = Integer.parseInt(a_b[0]);
                int b = Integer.parseInt(a_b[1]);
                arr[a][b] = 1;
            }
            s = Integer.parseInt(br.readLine());
            inArr = new int[s][2];
            for(int i=0; i<s; i++) {
                String[] a_b = br.readLine().split(" ");
                int a = Integer.parseInt(a_b[0]);
                int b = Integer.parseInt(a_b[1]);
                inArr[i][0] = a;
                inArr[i][1] = b;
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!");
            throw new RuntimeException(e);
        }
    }

}