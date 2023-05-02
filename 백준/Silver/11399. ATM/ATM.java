import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        String[] strings = bf.readLine().split(" ");

        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }

        Arrays.sort(arr);

        int[] sum = new int[n];
        sum[0] = arr[0];
        for(int i=1; i<n; i++) {
            sum[i] = sum[i-1] + arr[i];
        }

        int ans = 0;
        for(int num : sum) {
            ans += num;
        }

        System.out.println(ans);

    }
}