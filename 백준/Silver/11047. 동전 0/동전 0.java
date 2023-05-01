import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = bf.readLine().split(" ");
        int ans = 0;

        int N = Integer.parseInt(strings[0]);
        int K = Integer.parseInt(strings[1]);

        int[] coins = new int[N];

        int i;
        for(i=0; i<N; i++) {
            int coin = Integer.parseInt(bf.readLine());
            coins[i] = coin;
        }


        while(K > 0) {
            int max_coin = 0;
            for(int j=0; j<N; j++) {
                if(coins[j] > K) {
                    max_coin = coins[j-1];
                    break;
                }
                max_coin = coins[j];
            }

            int cnt = K / max_coin;
            ans += cnt;
            K -= max_coin*cnt;
        }

        System.out.println(ans);

    }
}