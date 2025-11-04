import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int VIS = 1;
    static final int NOT_CYCLE = 2;
    static final int CYCLE = 3;

    static int T, N;
    static int[] arr;
    static int[] state;

    public static void main(String[] args) throws Exception {
        input();
//        solve();
    }

    public static void solve() {
        state = new int[N+1];
        for(int i=1; i<=N; i++) {
            if(state[i] == 0) {
                bfs(i);
            }
        }

        int ans = 0;
        for(int i=1; i<=N; i++) {
            if(state[i] == NOT_CYCLE) ans++;
        }
        System.out.println(ans);
    }

    public static void bfs(int x) {
        int cur = x;

        while(true) {
            state[cur] = VIS;
            cur = arr[cur];

            if(state[cur] == CYCLE || state[cur] == NOT_CYCLE) {
                cur = x;
                while(state[cur] == VIS) {
                    state[cur] = NOT_CYCLE;
                    cur = arr[cur];
                }
                return ;
            }

            if(state[cur] == VIS && cur != x) {
                while(state[cur] == VIS) {
                    state[cur] = CYCLE;
                    cur = arr[cur];
                }
                cur = x;
                while(state[cur] == VIS) {
                    state[cur] = NOT_CYCLE;
                    cur = arr[cur];
                }
                return ;
            }
            if(state[cur] == VIS && cur==x) {
                while(state[cur] == VIS) {
                    state[cur] = CYCLE;
                    cur = arr[cur];
                }

                return;
            }
        }
    }

    public static void input() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            T = Integer.parseInt(br.readLine());
            for(int t=0; t<T; t++) {
                N = Integer.parseInt(br.readLine());
                arr = new int[N+1];
                String[] tmp = br.readLine().split(" ");
                for(int i=0; i<N; i++) {
                    arr[i+1] = Integer.parseInt(tmp[i]);
                }
                solve();
            }
        } catch (Exception e) {
            System.out.println("INPUT ERROR!!!!!!");
            throw new RuntimeException(e);
        }
    }

}