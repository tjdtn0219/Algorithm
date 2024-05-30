import java.io.*;
import java.util.*;

public class Main {

    static final int[] DX = {-1, 0, 1};
    int T;
    int[][] inputs;

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
            T = Integer.parseInt(br.readLine());
            inputs = new int[T][2];
            for(int t=0; t<T; t++) {
                String[] tmp = br.readLine().split(" ");
                int x = Integer.parseInt(tmp[0]);
                int y = Integer.parseInt(tmp[1]);
                inputs[t][0] = x;
                inputs[t][1] = y;
            }
        } catch(Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

    public void solve() {
        for(int t=0; t<T; t++) {
            System.out.println(getDistance(inputs[t][0], inputs[t][1]));
        }
    }

    public int getDistance(int x, int y) {
        int distance = y - x;
        int max = (int) Math.sqrt(distance);

        if(max == Math.sqrt(distance)) {
            return (2 * max - 1);
        }

        if(distance <= max * max + max) {
            return 2 * max;
        }

        return 2*max + 1;
    }

}
