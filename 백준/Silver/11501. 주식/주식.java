import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        for(int t=0; t<T; t++) {
            int n = Integer.parseInt(bf.readLine());
            String[] strings = bf.readLine().split(" ");
            int[] arr = new int[n];

            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(strings[i]);
            }

            int max_val = arr[n-1];
            long ans = 0;
            for(int i=n-2; i>=0; i--) {
                if(arr[i] > max_val) max_val = arr[i];
                else ans += max_val - arr[i];
            }
            System.out.println(ans);
        }
    }
}
