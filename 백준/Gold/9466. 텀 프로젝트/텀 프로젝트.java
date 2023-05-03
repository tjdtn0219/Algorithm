import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[] arr;
    public static int n;
    public static int[] state;

    public static final int NOT_VISITED = 0;
    public static final int VISITED = 1;
    public static final int CYCLE_IN = 2;
    public static final int NOT_CYCLE_IN = 3;


    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());
        int[] ans = new int[T];

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(bf.readLine());
            arr = new int[n+1];
            state = new int[n+1];
            String[] inputs = bf.readLine().split(" ");
            for(int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(inputs[i - 1]);
            }

            for(int i=1; i<=n; i++) {
                if(state[i] == NOT_VISITED) run(i);
            }
            int cnt = 0;
            for(int i=1; i<=n; i++) {
                if(state[i] == NOT_CYCLE_IN) cnt++;
            }
            System.out.println(cnt);
        }
    }

    public static void run(int x) {
        int cur = x;
        while(true) {
            state[cur] = VISITED;
            cur = arr[cur];

            // 1번 케이스
            if(state[cur] == CYCLE_IN || state[cur] == NOT_CYCLE_IN) {
                cur = x;
                while(state[cur] == VISITED) {
                    state[cur] = NOT_CYCLE_IN;
                    cur = arr[cur];
                }
                return;
            }

            // 2번 케이스
            if(state[cur] == VISITED && cur != x) {
                while(state[cur]==VISITED) {
                    state[cur] = CYCLE_IN;
                    cur = arr[cur];
                }
                cur = x;
                while(state[cur] != CYCLE_IN) {
                    state[cur] = NOT_CYCLE_IN;
                    cur = arr[cur];
                }
                return;
            }

            // 3번 케이스
            if(state[cur] == VISITED && cur == x) {
                while(state[cur]==VISITED) {
                    state[cur] = CYCLE_IN;
                    cur = arr[cur];
                }
                return;
            }

        }
    }
}