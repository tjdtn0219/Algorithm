import java.io.*;

public class Main {

    public static final int[] dx = {1, -1, 0, 0};
    public static final int[] dy = {0, 0, 1, -1};
    public static final int MAX_LEN = 30;
    public static boolean[][] vis = new boolean[MAX_LEN][MAX_LEN];

    public static int n;
    public static double[] percent;
    public static double ans = 0.0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        n = Integer.parseInt(strings[0]);

        percent = new double[4];
        for (int i = 0; i < 4; i++) percent[i] = Double.parseDouble(strings[i + 1])*0.01;

        vis[15][15] = true;
        btk(0, 15, 15, 1);

        System.out.println(ans);
    }

    public static void btk(int k, int x, int y, double result) {
        if (k == n) {
            ans += result;
//            System.out.println("result : " + result);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (vis[nx][ny]) continue;
            vis[nx][ny] = true;
            btk(k + 1, nx, ny, result * percent[dir]);
            vis[nx][ny] = false;

        }

    }
}