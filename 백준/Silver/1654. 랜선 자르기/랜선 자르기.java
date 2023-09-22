import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");

        int k = Integer.parseInt(strings[0]);
        int n = Integer.parseInt(strings[1]);

        int[] arr = new int[k];
        int max = 0;
        for(int i=0; i<k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(arr[i], max);
        }

        long st = 1;
        long en = max;
        long ans = 0;
        while(st <= en) {
            long mid = (st + en)/2;
            int cnt = 0;
            for(int num : arr) {
                cnt += num / mid;
            }
            if(cnt>=n) {
                ans = Math.max(ans, mid);
                st = mid + 1;

            } else {
                en = mid-1;
            }
        }
        System.out.println(ans);

    }
}
