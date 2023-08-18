import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);

        strings = br.readLine().split(" ");
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(strings[i]);

        int[] sum = new int[n+1];
        sum[1] = arr[1];
        for(int i=1; i<n+1; i++) sum[i] = sum[i-1] + arr[i-1];

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++) {
            strings = br.readLine().split(" ");
            int st = Integer.parseInt(strings[0]);
            int en = Integer.parseInt(strings[1]);
            int ans = sum[en] - sum[st-1];
            sb.append(ans + "\n");
        }

        System.out.println(sb);

    }
}